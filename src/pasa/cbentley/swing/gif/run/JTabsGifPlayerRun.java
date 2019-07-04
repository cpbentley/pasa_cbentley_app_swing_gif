package pasa.cbentley.swing.gif.run;

import pasa.cbentley.swing.gif.ui.AbstractGifTab;
import pasa.cbentley.swing.gif.ui.GifBasicPlayerPanel;
import pasa.cbentley.swing.window.CBentleyFrame;

public class JTabsGifPlayerRun extends AbstractGifPlayerRunner {
   public static void main(final String[] args) {
      //create runner
      final JTabsGifPlayerRun demo = new JTabsGifPlayerRun();
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
      
      sc.guiRegister(tabs);

      frame.getContentPane().add(tabs);
      frame.positionFrame();
      frame.setExitable(this);

      sc.guiUpdate();
   }

}
