package pasa.cbentley.swing.gif.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.interfaces.ICallBack;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.thread.ITechRunnable;
import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.gif.player.GifControl;
import pasa.cbentley.swing.gif.player.LoadGifFileWorkerRunnable;
import pasa.cbentley.swing.gif.player.PanelButtonsPlayerBasic;
import pasa.cbentley.swing.gif.utils.GifTransfer;
import pasa.cbentley.swing.images.anim.AnimOfImageFrames;
import pasa.cbentley.swing.images.anim.AnimationCoordinator;
import pasa.cbentley.swing.images.anim.ui.JComponentAnim;
import pasa.cbentley.swing.images.ctx.ImgCtx;
import pasa.cbentley.swing.images.interfaces.IRepaintable;
import pasa.cbentley.swing.imytab.IMyGui;
import pasa.cbentley.swing.interfaces.ICallBackSwing;
import pasa.cbentley.swing.window.CBentleyFrame;

/**
 * Use Controller to start and stop the player.
 * 
 * @author Charles Bentley
 *
 */
public class GifBasicPlayerPanel extends JPanel implements ICallBackSwing, ICallBack, IMyGui, IRepaintable {

   private PanelButtonsPlayerBasic controller;

   private CBentleyFrame           frame;

   private SwingGifCtx                  gifc;

   private String                  gifPath;

   private JComponentAnim          imgView;

   private File                    lastTriedFile;

   private SwingCtx                sc;

   private AnimationCoordinator    animDrawer;

   private GifControl              control;

   public GifBasicPlayerPanel(SwingGifCtx gifc) {
      this.gifc = gifc;
      this.sc = gifc.getSwingCtx();

      ImgCtx imgCtx = gifc.getImgCtx();

      imgView = new JComponentAnim(imgCtx);
      animDrawer = new AnimationCoordinator(imgCtx, this);
      //kind of a builder pattern
      controller = new PanelButtonsPlayerBasic(gifc, imgView);
      controller.setDisplayMemoryWidget(true);
      controller.populatePanelWithWidgets();

      control = new GifControl(gifc, this, controller);

      //ui setup
      this.setLayout(new BorderLayout());
      this.add(imgView, BorderLayout.CENTER);
      this.add(controller, BorderLayout.SOUTH);

      GifTransfer gifTransfer = new GifTransfer(sc, control); //as callback for files
      this.setTransferHandler(gifTransfer);
   }

   public PanelButtonsPlayerBasic getController() {
      return controller;
   }

   public boolean isPlaying() {
      return imgView.getState() == ITechRunnable.STATE_0_RUNNING;
   }

   public void playDefault() {
      if(gifPath != null) {
         control.cmdAnimPlayIfNew(gifPath);
      }
   }

   public String getGifPath() {
      return gifPath;
   }

   public void guiUpdate() {
      sc.guiUpdateOnChildren(this);
   }

   public void reload() {
      controller.cmdPlay();
   }

   public void setFrameOwner(CBentleyFrame frame) {
      this.frame = frame;
   }

   public void setGifPath(String gifPath) {
      this.gifPath = gifPath;
   }

   //#mdebug
   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "SimpleGifViewer");
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "SimpleGifViewer");
   }

   public UCtx toStringGetUCtx() {
      return sc.getUCtx();
   }
   //#enddebug

   public void unload() {
      imgView.setImage(null);
   }

   public void requestRepaintPlease() {
      // TODO Auto-generated method stub

   }

   public void callBack(Object o) {
      // TODO Auto-generated method stub

   }

   public void callBackInSwingThread(Object o) {
      // TODO Auto-generated method stub

   }

}
