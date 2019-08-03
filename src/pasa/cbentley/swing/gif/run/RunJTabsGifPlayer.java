package pasa.cbentley.swing.gif.run;

import pasa.cbentley.swing.gif.ui.AbstractGifTab;
import pasa.cbentley.swing.window.CBentleyFrame;

public class RunJTabsGifPlayer extends RunAbstractGifPlayer {
   public static void main(final String[] args) {
      //create runner
      final RunJTabsGifPlayer demo = new RunJTabsGifPlayer();
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

      frame = new CBentleyFrame(sc);
      frame.setTitle("JTabs GIF Demo");
      frame.setPrefID("anim_tabs_demo");

      TabsGif tabs = new TabsGif(gifc);
      //initialize it
      tabs.initCheck();
      
      //manually select a tab for initialization
      AbstractGifTab first = tabs.getFirst();
      first.initCheck();
      tabs.setSelected(first);
      
      frame.getContentPane().add(tabs);
      
      return frame;
   }

}
