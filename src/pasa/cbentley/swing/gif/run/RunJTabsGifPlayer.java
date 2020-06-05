/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.swing.gif.run;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.BaseDLogger;
import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src4.logging.ITechTags;
import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.gif.ui.AbstractGifTab;
import pasa.cbentley.swing.images.anim.AnimRunnerProducer;
import pasa.cbentley.swing.images.anim.ui.JComponentAnim;
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

   //#mdebug
   protected void toStringSetupLogger(UCtx uc) {
      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
      IDLogConfig config = loggerFirst.getDefault().getConfig();
      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
      //config.setFlagPrint(ITechConfig.MASTER_FLAG_03_ONLY_POSITIVES, true);
      config.setFlagTag(ITechTags.FLAG_09_PRINT_FLOW, true);
      config.setClassNegative(AnimRunnerProducer.class, true);
      config.setClassNegative(JComponentAnim.class, true);
      config.setClassNegative(SwingCtx.class, true);
      
      sc.setResMissingLog(false);
   }
   //#enddebug

}
