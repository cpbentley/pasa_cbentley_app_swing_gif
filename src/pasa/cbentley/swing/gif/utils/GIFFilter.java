package pasa.cbentley.swing.gif.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Accepts Directories and GIF files.
 * 
 * @author Charles Bentley
 *
 */
public class GIFFilter extends FileFilter implements java.io.FileFilter {

   @Override
   public boolean accept(File f) {
      if(f.isDirectory()) {
         return true;
      }
      String s = f.getName();
      if (s.endsWith(".gif")) {
         return true;
      }
      if (s.endsWith(".GIF")) {
         return true;
      }
      return false;
   }

   @Override
   public String getDescription() {
      return "Gifs";
   }

}