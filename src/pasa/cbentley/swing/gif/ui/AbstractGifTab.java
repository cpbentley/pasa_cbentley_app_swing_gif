package pasa.cbentley.swing.gif.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.imytab.AbstractMyTab;
import pasa.cbentley.swing.imytab.IMyTab;
import pasa.cbentley.swing.widgets.b.BLabel;

/**
 * A an {@link IMyTab} that displays an animating gif along with a player controller
 * 
 * <li> {@link AbstractGifTab#setTitleKey(String)} create a Label above
 * 
 * @author Charles Bentley
 *
 */
public class AbstractGifTab extends AbstractMyTab {

   /**
    * 
    */
   private static final long   serialVersionUID = 8194278974893842214L;

   private Color               backgroundColorGif;

   protected SwingGifCtx       gifc;

   private String              gifPath;

   private boolean             isStartAndStop;

   private BLabel              labTitle;

   private GifBasicPlayerPanel player;

   private String              titleKey;

   /**
    * @param gifc
    * @param id
    * @param gifPath
    * @param titleKey
    */
   public AbstractGifTab(SwingGifCtx gifc, String id, String gifPath, String titleKey) {
      super(gifc.getSwingCtx(), id);
      this.gifc = gifc;
      this.gifPath = gifPath;
      this.setTitleKey(titleKey);
      isStartAndStop = true;

   }

   public void disposeTab() {
      //#debug
      toDLog().pFlow("", this, AbstractGifTab.class, "disposeTab", ITechLvl.LVL_05_FINE, true);

      if (player != null) {
         if (isStartAndStop) {
            player.getController().cmdStop();
         }
      }
   }

   public String getTitleKey() {
      return titleKey;
   }

   public void initTab() {
      this.setLayout(new BorderLayout());

      JPanel north = new JPanel();

      labTitle = new BLabel(sc, getTitleKey());
      north.add(labTitle);

      //in center a GIF demo of the pascal root daemon
      player = new GifBasicPlayerPanel(gifc);
      player.setGifPath(gifPath);
      player.playDefault();
      player.getController().cmdPlay();
      player.setColorBackgroundGif(backgroundColorGif);

      this.add(north, BorderLayout.NORTH);
      this.add(player, BorderLayout.CENTER);
   }

   /**
    * The color to be used as background that will be fed during init to
    * {@link GifBasicPlayerPanel}
    * @param c
    */
   public void setColorBackgroundGif(Color c) {
      backgroundColorGif = c;
   }

   public void setStartAndStop(boolean isStartAndStop) {
      this.isStartAndStop = isStartAndStop;
   }

   public void setTitleKey(String titleKey) {
      this.titleKey = titleKey;
   }

   public void tabGainFocus() {
      if (player != null) {
         if (isStartAndStop) {
            //restart the GIF where it was ?
            player.getController().cmdPlay();
            //set button states?
         }
      }
   }

   public void tabLostFocus() {
      //#debug
      toDLog().pFlow("", this, AbstractGifTab.class, "tabLostFocus", LVL_05_FINE, true);
      if (player != null) {
         player.getController().cmdStop();
      }
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "AbstractGifTab");
      dc.appendWithSpace(getTabInternalID());
      toStringPrivate(dc);
      dc.appendVarWithSpace("titleKey", titleKey);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "AbstractGifTab");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("gifPath", gifPath);
      sc.toSD().d1(backgroundColorGif);
   }

   //#enddebug

}
