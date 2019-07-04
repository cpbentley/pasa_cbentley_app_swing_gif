package pasa.cbentley.swing.gif.player;

import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.images.interfaces.IGifCommadable;
import pasa.cbentley.swing.widgets.b.BButton;
import pasa.cbentley.swing.widgets.b.BButtonToggle;
import pasa.cbentley.swing.widgets.b.BCheckBox;

public class PanelButtonsPlayerFull extends PanelButtonsPlayerBasic {
   private JCheckBox     cbInverse;

   private JCheckBox     cbShowData;

   private JCheckBox     cbUpAndDown;

   private BButton       butFramePrev;

   private BButton       butFrameNext;

   private BButton       butRandom;

   private BButtonToggle butLoop;

   public PanelButtonsPlayerFull(SwingGifCtx gifc, IGifCommadable view) {
      super(gifc, view);
   }

   protected void subPopulatePanelWithWidgets() {
      super.subPopulatePanelWithWidgets();
      this.add(butFramePrev);
      this.add(butFrameNext);
      this.add(butRandom);
      this.add(cbInverse);
      this.add(cbUpAndDown);
      this.add(butLoop);
   }

   protected void subInitButtons() {
      if (butFramePrev == null) {
         butFramePrev = new BButton(sc, this, "<<frame");
         butFrameNext = new BButton(sc, this, "frame>>");
         butRandom = new BButton(sc, this, "Random");
         cbInverse = new BCheckBox(sc, this, "Inverse");
         cbUpAndDown = new BCheckBox(sc, this, "UpAndDown");
         butLoop = new BButtonToggle(sc, this);
      }
   }

   public void actionPerformed(ActionEvent e) {

      super.actionPerformed(e);
   }
}
