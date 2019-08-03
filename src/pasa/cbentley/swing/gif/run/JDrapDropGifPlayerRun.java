package pasa.cbentley.swing.gif.run;

import pasa.cbentley.swing.actions.IExitable;
import pasa.cbentley.swing.gif.ui.GifBasicPlayerPanel;
import pasa.cbentley.swing.window.CBentleyFrame;

public class JDrapDropGifPlayerRun extends AbstractGifPlayerRunner implements IExitable {

   public static void main(final String[] args) {
      //create runner
      final JDrapDropGifPlayerRun demo = new JDrapDropGifPlayerRun();
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
      frame.setTitle("Image Animation GIF Demo");
      frame.setPrefID("anim_demo");
      
      gifplayer = new GifBasicPlayerPanel(gifc);
      gifplayer.setFrameOwner(frame);

      frame.getContentPane().add(gifplayer);

      return frame;
   }

}
