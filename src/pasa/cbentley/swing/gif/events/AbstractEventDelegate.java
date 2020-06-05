/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.swing.gif.events;

import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.images.interfaces.IGifCommadable;
import pasa.cbentley.swing.images.interfaces.IRepaintable;

public abstract class AbstractEventDelegate {

   protected final IGifCommadable controller;

   protected final SwingGifCtx         gifc;

   protected final SwingCtx       sc;

   protected final IRepaintable repaintable;

   public AbstractEventDelegate(SwingGifCtx gifc, IGifCommadable controller, IRepaintable repaintable) {
      this.gifc = gifc;
      this.controller = controller;
      this.repaintable = repaintable;
      this.sc = gifc.getSwingCtx();
   }
}
