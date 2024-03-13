/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.swing.gif.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.interfaces.ICallBack;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.gif.utils.GIFFilter;
import pasa.cbentley.swing.images.anim.AnimOfImageFrames;
import pasa.cbentley.swing.images.interfaces.IGifCommadable;
import pasa.cbentley.swing.images.interfaces.IGifRepaintable;
import pasa.cbentley.swing.interfaces.ICallBackSwing;

/**
 * Controls that manages the loading and starting of a Gif animation.
 * <br>
 * <br>
 * Resides in the UI Thread.
 * <br>
 * @author Charles Bentley
 *
 */
public class GifControl implements ICallBackSwing, ICallBack {

   protected IGifCommadable controller;

   protected GIFFilter      filter;

   protected final SwingGifCtx   gifc;

   protected String         gifName;

   protected File           lastTriedFile;

   protected IGifRepaintable   rep;

   protected final SwingCtx sc;

   public GifControl(SwingGifCtx gifc, IGifRepaintable rep, IGifCommadable controller) {
      this.gifc = gifc;
      this.sc = gifc.getSwingCtx();
      this.rep = rep;
      this.controller = controller;
      filter = new GIFFilter();

   }

   /**
    * Call back for playing.. look up the type and try to extract a GIF
    * 
    * <li> {@link AnimOfImageFrames}
    * <li> File
    * <li> List
    */
   public void callBack(Object o) {
      boolean res = false;
      if (o instanceof List) {
         List list = (List) o;
         if (list != null && !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
               Object oit = it.next();
               res = callBackPrivate(oit);
               if (res) {
                  //TODO possibly add other GIFs in a play list
                  break;
               }
            }

         }
      } else {
         res = callBackPrivate(o);
      }
      if (!res) {
         //print a dev warning.. should not happen
      }
   }

   public void callBackInSwingThread(Object o) {
      callBack(o);
   }

   private boolean callBackPrivate(Object o) {
      if (o instanceof AnimOfImageFrames) {
         AnimOfImageFrames ia = (AnimOfImageFrames) o;
         //stop current any
         controller.cmdStop();
         cmdAnimPlay(ia);
         return true;
      } else if (o instanceof File) {
         File file = (File) o;
         if (file.isDirectory()) {
            //play all gif in directory?
            return false;
         }
         if (file.getName().toLowerCase().endsWith(".gif")) {
            cmdAnimPlay(file);
            return true;
         }
      }
      return false;
   }

   public void cmdAnimPlay(AnimOfImageFrames ia) {
      controller.cmdStop();
      controller.setImage(ia);
      controller.cmdPlay(); //go through the controller to set the button ui state
   }

   /**
    * 
    * @param f
    */
   public void cmdAnimPlay(final File f) {
      lastTriedFile = f;
      try {
         cmdAnimPlay(new FileInputStream(f), f.getName());
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }

   /**
    * Load GIF frame data in a worker thread.
    * @param is
    * @param name
    */
   public void cmdAnimPlay(final InputStream is, final String name) {
      if (!name.endsWith(".gif"))
         throw new IllegalArgumentException();
      if (is == null) {
         throw new NullPointerException("InputStream is null for " + name);
      }
      //generate event tell we are loading. 
      controller.willStartLoadingNewGif(name);
      LoadGifFileWorkerRunnable loader = new LoadGifFileWorkerRunnable(gifc, is, name, this);
      gifc.getSwingCtx().getExecutorService().execute(loader);

   }

   public void cmdAnimPlay(String gifName) {
      this.gifName = gifName;
      InputStream is = this.getClass().getResourceAsStream(gifName);
      cmdAnimPlay(is, gifName);
   }

   public void cmdAnimPlayIfNew(String gifName) {
      if (gifName.equals(this.gifName)) {
         return;
      }
      this.gifName = gifName;
      InputStream is = this.getClass().getResourceAsStream(gifName);
      cmdAnimPlay(is, gifName);
   }

   public IGifCommadable getController() {
      return controller;
   }

   /**
    * "" if no gif is being played
    * @return
    */
   public String getGifNamePlaying() {
      if (gifName != null) {
         return gifName;
      }
      return "";
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "GifControl");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "GifControl");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return gifc.getUC();
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
