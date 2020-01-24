package pasa.cbentley.swing.gif.run;

import java.util.List;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.interfaces.IPrefs;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.swing.actions.IExitable;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.gif.ui.GifBasicPlayerPanel;
import pasa.cbentley.swing.images.ctx.ImgCtx;
import pasa.cbentley.swing.run.RunSwingAbstract;

public abstract class RunAbstractGifPlayer extends RunSwingAbstract implements IExitable, IStringable {

   protected final SwingGifCtx   gifc;

   protected GifBasicPlayerPanel gifplayer;

   protected final ImgCtx        imgc;

   public RunAbstractGifPlayer() {
      this.imgc = new ImgCtx(sc);
      this.gifc = new SwingGifCtx(sc, imgc);
   }

   protected void initOutsideUIForPrefs(IPrefs prefs) {
   }

   public void cmdExit() {
      //finish current thread
      if (gifplayer != null) {
         gifplayer.getController().cmdStop();
      }

      //#debug
      toDLog().pFlow("", this, RunAbstractGifPlayer.class, "cmdExit", LVL_05_FINE, true);

      System.exit(0);
   }

   protected void addI18n(List<String> list) {
      list.add("i18nGif");
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "AbstractGifPlayerRunner");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "AbstractGifPlayerRunner");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return uc;
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
