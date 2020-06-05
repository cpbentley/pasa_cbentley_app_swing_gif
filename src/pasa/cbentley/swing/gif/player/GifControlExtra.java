/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.swing.gif.player;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.gif.utils.GIFFilter;
import pasa.cbentley.swing.images.interfaces.IGifCommadable;
import pasa.cbentley.swing.images.interfaces.IRepaintable;

public class GifControlExtra extends GifControl {

   public GifControlExtra(SwingGifCtx gifc, IRepaintable rep, IGifCommadable controller) {
      super(gifc, rep, controller);
   }

   //#mdebug
   public void cmdDebugAnim() {
      controller.cmdDebugAnim();
   }
   //#enddebug
   
   public void cmdExit() {

   }

   public void cmdImageNext() {
      if (lastTriedFile != null) {
         File dir = lastTriedFile.getParentFile();
         File[] files = dir.listFiles(filter);
         for (int i = 0; i < files.length; i++) {
            if (files[i].getName().equals(lastTriedFile.getName())) {
               //get next file
               int nextFileIndex = (i + 1) % files.length;
               cmdAnimPlay(files[nextFileIndex]);
               break;
            }
         }
      }
   }

   public void cmdImagePrev() {
      if (lastTriedFile != null) {
         File dir = lastTriedFile.getParentFile();
         File[] files = dir.listFiles(filter);
         for (int i = 0; i < files.length; i++) {
            if (files[i].getName().equals(lastTriedFile.getName())) {
               //get next file
               int nextFileIndex = (i - 1);
               if (i < 0) {
                  nextFileIndex = files.length - 1;
               }
               cmdAnimPlay(files[nextFileIndex]);
               break;
            }
         }
      }
   }

   public void cmdOpenImageFolder(int id) {
      String preferenceID = "gifs" + id;
      File folder = sc.getFolder(preferenceID);
      Desktop desktop = Desktop.getDesktop();
      try {
         desktop.open(folder);
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public void cmdOpenImageInFolder(int id) {
      String preferenceID = "gifs" + id;
      //get folder from preferen
      JFileChooser chooser = new JFileChooser(sc.getFolder(preferenceID));
      chooser.setFileFilter(new GIFFilter());
      int returnVal = chooser.showOpenDialog(sc.getFrames().getFirstActive());
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         File f = chooser.getSelectedFile();
         sc.setFolder(preferenceID, f.getParentFile());
         cmdAnimPlay(f);

      }
   }

   public void cmdReload() {
      //stop current 
      controller.cmdStop();

      File lastFile = new File(sc.getPrefs().get("last_gif", ""));
      if (lastFile.exists() && !lastFile.isDirectory()) {
         cmdAnimPlay(lastFile);
      }

   }


   public void cmdShowDataToggle() {
      controller.cmdShowDataToggle();
   }

}
