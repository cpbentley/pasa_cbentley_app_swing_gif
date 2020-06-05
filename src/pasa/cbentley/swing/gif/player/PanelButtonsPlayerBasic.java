/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.swing.gif.player;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.images.anim.AnimOfImageFrames;
import pasa.cbentley.swing.images.interfaces.IGifCommadable;
import pasa.cbentley.swing.panels.MemoryPanelProgressBar;
import pasa.cbentley.swing.widgets.b.BButton;

/**
 * Panel that displays Control buttons, those buttons events hook into the {@link IGifCommadable}
 * <br>
 * Once created, configure it with display options and call init.
 * @author Charles Bentley
 *
 */
public class PanelButtonsPlayerBasic extends JPanel implements ActionListener, IGifCommadable {

   protected BButton                butFaster;

   protected BButton                butPause;

   protected BButton                butPlay;

   protected BButton                butSkip;

   protected BButton                butSlower;

   protected BButton                butStop;

   protected SwingGifCtx                 gifc;

   protected IGifCommadable         gifCommandable;

   private boolean                  isDisplayMemoryWidget = false;

   protected MemoryPanelProgressBar memoryTool;

   protected final SwingCtx         sc;

   public PanelButtonsPlayerBasic(SwingGifCtx gifc, IGifCommadable gifCommandable) {
      this.gifc = gifc;
      this.sc = gifc.getSwingCtx();
      this.gifCommandable = gifCommandable;
   }

   public void actionPerformed(ActionEvent e) {
      Object src = e.getSource();
      if (src == butPlay) {
         cmdPlay();
      } else if (src == butPause) {
         cmdPause();
      } else if (src == butStop) {
         cmdStop();
      } else if (src == butSkip) {
         cmdSkip();
      } else if (src == butSlower) {
         cmdSlower();
      } else if (src == butFaster) {
         cmdFaster();
      }
   }

   public void cmdDebugAnim() {

   }

   public void cmdFaster() {
      gifCommandable.cmdFaster();
   }

   public void cmdForward() {
      gifCommandable.cmdForward();
   }

   public void cmdFrameNext() {
      gifCommandable.cmdFrameNext();
   }

   public void cmdFramePrev() {
      gifCommandable.cmdFramePrev();
   }

   public void cmdFrameTo(int val) {

   }

   public void cmdPause() {
      gifCommandable.cmdPause();
      butPause.setEnabled(false);
      butPlay.setEnabled(true);
   }

   public void cmdPlay() {
      gifCommandable.cmdPlay();
      butPlay.setEnabled(false);
      butPause.setEnabled(true);
      butSkip.setEnabled(true);
      butStop.setEnabled(true);
   }

   public void cmdShowDataToggle() {

   }

   /**
    * Skipping while paused? allows to
    */
   public void cmdSkip() {
      gifCommandable.cmdForward();
   }

   public void cmdSlower() {
      gifCommandable.cmdSlower();
   }

   /**
    * Reset Gif to first frame. not playing
    */
   public void cmdStop() {
      gifCommandable.cmdStop();
      butPlay.setEnabled(true);
      butStop.setEnabled(false);
      butSkip.setEnabled(false);
      butPause.setEnabled(false);
   }

   public void cmdTogglePlayPause() {
      gifCommandable.cmdTogglePlayPause();
   }

   private SwingCtx getSC() {
      return gifc.getSwingCtx();
   }

   /**
    * Return current state
    * @return
    */
   public int getState() {
      return gifCommandable.getState();
   }

   private void initButtons() {
      if (butPlay == null) {
         butPlay = new BButton(getSC(), this, "but.play");
         butPause = new BButton(getSC(), this, "but.pause");
         butStop = new BButton(getSC(), this, "but.stop");
         butSlower = new BButton(getSC(), this, "but.slower");
         butFaster = new BButton(getSC(), this, "but.faster");
         butSkip = new BButton(getSC(), this, "but.skip");
         memoryTool = new MemoryPanelProgressBar(gifc.getSwingCtx());
      }
      subInitButtons();
   }

   protected void populateDefaultButtonOrder(JPanel panel) {
      panel.add(butPlay);
      panel.add(butPause);
      panel.add(butStop);
      panel.add(butSlower);
      panel.add(butFaster);
      panel.add(butSkip);
      if (isDisplayMemoryWidget()) {
         panel.add(memoryTool);
      }
   }

   /**
    * Removes everything and populate
    */
   public void populatePanelWithWidgets() {
      this.removeAll();
      initButtons();
      this.setLayout(new FlowLayout());
      populateDefaultButtonOrder(this);
      subPopulatePanelWithWidgets();
   }

   public void setImage(AnimOfImageFrames ia) {
      gifCommandable.setImage(ia);
   }

   protected void subInitButtons() {

   }
   /**
    * Sub class add buttons to this panel here.
    */
   protected void subPopulatePanelWithWidgets() {

   }

   public void willStartLoadingNewGif(String name) {
      gifCommandable.willStartLoadingNewGif(name);
   }

   public boolean isDisplayMemoryWidget() {
      return isDisplayMemoryWidget;
   }

   public void setDisplayMemoryWidget(boolean isDisplayMemoryWidget) {
      this.isDisplayMemoryWidget = isDisplayMemoryWidget;
   }

}
