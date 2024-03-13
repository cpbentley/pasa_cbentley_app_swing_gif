/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.swing.gif.events;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.images.anim.AnimationCoordinator;
import pasa.cbentley.swing.images.interfaces.IGifCommadable;
import pasa.cbentley.swing.images.interfaces.IGifRepaintable;

public class MouseEventsGIFPlayerDelegate extends AbstractEventDelegate implements IStringable, MouseWheelListener {

   public MouseEventsGIFPlayerDelegate(SwingGifCtx gifc, IGifCommadable controller, IGifRepaintable repaintable) {
      super(gifc, controller, repaintable);
   }

   public void mouseWheelMoved(MouseWheelEvent e) {
      //#debug
      toDLog().pFlow(sc.toSD().d1(e), this, AnimationCoordinator.class, "mouseWheelMoved", ITechLvl.LVL_04_FINER, true);

      if (e.getWheelRotation() > 0) {
         controller.cmdFrameNext();
      } else {
         controller.cmdFramePrev();
      }
      repaintable.requestRepaintPlease();
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "MouseSupportEvents");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "MouseSupportEvents");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return gifc.getUC();
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
