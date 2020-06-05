/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.swing.gif.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.images.interfaces.IGifCommadable;
import pasa.cbentley.swing.images.interfaces.IRepaintable;

public class KeyEventsGIFPlayerDelegate extends AbstractEventDelegate implements KeyListener {

   public KeyEventsGIFPlayerDelegate(SwingGifCtx gifc, IGifCommadable controller, IRepaintable repaintable) {
      super(gifc, controller, repaintable);
   }

   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
         controller.cmdTogglePlayPause();
      }
   }

   public void keyReleased(KeyEvent e) {

   }

   public void keyTyped(KeyEvent e) {

   }

}
