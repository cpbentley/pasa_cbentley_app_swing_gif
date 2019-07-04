package pasa.cbentley.swing.gif.run;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import pasa.cbentley.swing.actions.IExitable;
import pasa.cbentley.swing.gif.ui.NewGIFBackgroundPanel;

public class GifPanelBackgroundDemoRun extends AbstractGifPlayerRunner implements IExitable {

   public static void main(final String[] args) {
      //create runner
      final GifPanelBackgroundDemoRun demo = new GifPanelBackgroundDemoRun();
      //init prefs files etc.
      demo.initUIThreadOutside();
      //init UI stuff inside AWT thread.
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            demo.initUIThreadInside();
         }
      });
   }



   protected void initUIThreadInside() {
      JFrame f = new JFrame("Demo of a Gif as background of a JPanel");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLocationByPlatform(true);
      NewGIFBackgroundPanel imagePanel = new NewGIFBackgroundPanel(gifc);

      String gifName = "C:\\eclipse-workspace-2019-03\\pasa_cbentley_pascalcoin\\res\\gifs\\pasc_ice_black_clouds_1024.gif";
      File file = new File(gifName);
      if(!file.exists()) {
         
      }
      imagePanel.getControl().cmdAnimPlay(file);
      
      imagePanel.setLayout(new GridLayout(5, 10, 10, 10));
      imagePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
      for (int ii = 1; ii < 51; ii++) {
         imagePanel.add(new JButton("" + ii));
      }

      f.setContentPane(imagePanel);
      f.setPreferredSize(new Dimension(1024, 1024));
      f.pack();
      f.setVisible(true);
   }

}
