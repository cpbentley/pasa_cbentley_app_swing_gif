package pasa.cbentley.swing.gif.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.interfaces.ICallBack;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.gif.player.GifControl;
import pasa.cbentley.swing.gif.player.PanelButtonsPlayerBasic;
import pasa.cbentley.swing.images.anim.AnimOfImageFrames;
import pasa.cbentley.swing.images.anim.AnimationCoordinator;
import pasa.cbentley.swing.images.anim.ui.JComponentAnim;
import pasa.cbentley.swing.images.interfaces.IGifCommadable;
import pasa.cbentley.swing.images.interfaces.IRepaintable;

/**
 * Draws the Gif animation in the background of the JPanel.
 * <br>
 * <br>
 * {@link GifBasicPlayerPanel} uses a {@link JComponentAnim} and a button bar.
 * 
 * With {@link NewGIFBackgroundPanel} There is not button for control. Its all automatic
 * or hidden using mouse controls.
 * 
 * @author Charles Bentley
 *
 */
public class NewGIFBackgroundPanel extends JPanel implements IRepaintable, IStringable {

   private AnimationCoordinator animDrawer;

   private GifControl           control;

   private SwingGifCtx               gifc;

   private SwingCtx             sc;

   public NewGIFBackgroundPanel(SwingGifCtx gifc) {
      this.gifc = gifc;
      this.sc = gifc.getSwingCtx();
      aConstructAnimator();
   }

   private void aConstructAnimator() {
      if (animDrawer == null) {
         animDrawer = new AnimationCoordinator(gifc.getImgCtx(), this);
         control = new GifControl(gifc, this, animDrawer);
      }
   }


   /**
    * Call this when GIF anim is no more needed
    */
   public void disposeGIF() {
      animDrawer.cmdStop();
      animDrawer = null;
   }

   public GifControl getControl() {
      return control;
   }

   /**
    * Stop stop the Gif play
    * @return
    */
   public IGifCommadable getController() {
      return animDrawer;
   }



   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (animDrawer != null) {
         //paint center
         int w = animDrawer.getPreferredSize().width;
         int x = 0;
         if (this.getWidth() > w) {
            x = (this.getWidth() - w) / 2;
         }
         int h = animDrawer.getPreferredSize().height;
         int y = 0;
         if (this.getHeight() > h) {
            y = (this.getHeight() - h) / 2;
         }
         animDrawer.paintAnimFrame(g, x, y);
      }

   }

   public void requestRepaintPlease() {
      this.repaint();
   }

   //#mdebug
   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "GIFBackgroundPanel");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "GIFBackgroundPanel");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return sc.getUCtx();
   }
   //#enddebug

   private void toStringPrivate(Dctx dc) {

   }

}