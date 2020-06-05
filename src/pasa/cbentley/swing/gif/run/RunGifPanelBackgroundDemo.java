/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.swing.gif.run;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import pasa.cbentley.core.src4.interfaces.IPrefs;
import pasa.cbentley.swing.actions.IExitable;
import pasa.cbentley.swing.gif.ui.NewGIFBackgroundPanel;
import pasa.cbentley.swing.window.CBentleyFrame;

public class RunGifPanelBackgroundDemo extends RunAbstractGifPlayer implements IExitable {

   public static void main(final String[] args) {
      //create runner
      final RunGifPanelBackgroundDemo demo = new RunGifPanelBackgroundDemo();
      //init prefs files etc.
      demo.initUIThreadOutside();
      //init UI stuff inside AWT thread.
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            demo.initUIThreadInside();
         }
      });
   }

   protected CBentleyFrame initUIThreadInsideSwing() {
      CBentleyFrame f = new CBentleyFrame(sc, "Demo of a Gif as background of a JPanel");
      
      f.setLocationByPlatform(true);
      
      NewGIFBackgroundPanel imagePanel = new NewGIFBackgroundPanel(gifc);

      String gifName = "/gifs/pasc_ice_black_clouds_1024.gif";
      imagePanel.getControl().cmdAnimPlay(gifName);
      
      imagePanel.setLayout(new GridLayout(5, 10, 10, 10));
      imagePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
      for (int ii = 1; ii < 51; ii++) {
         imagePanel.add(new JButton("" + ii));
      }

      f.setContentPane(imagePanel);
      f.setPreferredSize(new Dimension(1024, 1024));

      return f;
   }

}
