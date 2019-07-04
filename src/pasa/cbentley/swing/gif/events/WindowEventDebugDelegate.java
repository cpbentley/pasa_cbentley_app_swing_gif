package pasa.cbentley.swing.gif.events;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.swing.ctx.SwingCtx;

public class WindowEventDebugDelegate implements WindowListener {

   private SwingCtx sc;

   private IStringable owner;
   
   public WindowEventDebugDelegate(SwingCtx sc,IStringable owner) {
      this.sc = sc;
      this.owner = owner;

   }

   public IDLog toDLog() {
      return sc.toDLog();
   }

   public void windowActivated(WindowEvent e) {
      //#debug
      toDLog().pEvent(sc.toSD().d1(e), owner, WindowEventDebugDelegate.class, "windowActivated", ITechLvl.LVL_04_FINER, true);
   }

   public void windowClosed(WindowEvent e) {
      //#debug
      toDLog().pEvent(sc.toSD().d1(e), owner, WindowEventDebugDelegate.class, "windowClosed", ITechLvl.LVL_04_FINER, true);
   }

   public void windowClosing(WindowEvent e) {
      //#debug
      toDLog().pEvent(sc.toSD().d1(e), owner, WindowEventDebugDelegate.class, "windowClosing", ITechLvl.LVL_04_FINER, true);
   }

   public void windowDeactivated(WindowEvent e) {
      //#debug
      toDLog().pEvent(sc.toSD().d1(e), owner, WindowEventDebugDelegate.class, "windowDeactivated", ITechLvl.LVL_04_FINER, true);
   }

   public void windowDeiconified(WindowEvent e) {
      //#debug
      toDLog().pEvent(sc.toSD().d1(e), owner, WindowEventDebugDelegate.class, "windowDeiconified", ITechLvl.LVL_04_FINER, true);
   }

   public void windowIconified(WindowEvent e) {
      //#debug
      toDLog().pEvent(sc.toSD().d1(e), owner, WindowEventDebugDelegate.class, "windowIconified", ITechLvl.LVL_04_FINER, true);
   }

   public void windowOpened(WindowEvent e) {
      //#debug
      toDLog().pEvent(sc.toSD().d1(e), owner, WindowEventDebugDelegate.class, "windowOpened", ITechLvl.LVL_04_FINER, true);
   }

}
