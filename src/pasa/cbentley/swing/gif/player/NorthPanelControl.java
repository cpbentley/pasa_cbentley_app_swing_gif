package pasa.cbentley.swing.gif.player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.images.interfaces.IGifCommadable;
import pasa.cbentley.swing.panels.MemoryPanelProgressBar;

public class NorthPanelControl extends JPanel implements ActionListener {

   private JButton          butDebugAnim;

   private JCheckBox        cbShowData;

   private JButton          butReload;

   private JButton          butOpenImg1;

   private JButton          butOpenImg2;

   private JButton          butImagePrev;

   private JButton          butImageNext;

   private JButton          butClose;

   private SwingGifCtx           gifc;

   private IGifCommadable   view;

   private GifControlExtra  control;

   protected final SwingCtx sc;

   public NorthPanelControl(SwingGifCtx gifc, GifControlExtra control, IGifCommadable view) {
      this.gifc = gifc;
      this.sc = gifc.getSwingCtx();
      this.control = control;
      this.view = view;
   }

   public void init() {
      //default is borderlayout

      butDebugAnim = new JButton("Debug");
      butDebugAnim.addActionListener(this);
      this.add(butDebugAnim);

      cbShowData = new JCheckBox("Show Debug");
      cbShowData.addActionListener(this);
      this.add(cbShowData);

      MemoryPanelProgressBar memPanel = new MemoryPanelProgressBar(sc);
      this.add(memPanel);

      butReload = new JButton("Reload");
      butReload.addActionListener(this);
      this.add(butReload);

      butOpenImg1 = new JButton("Open Folder 1");
      butOpenImg1.setToolTipText("Drag and Drop or Choose a File");
      butOpenImg1.addActionListener(this);
      this.add(butOpenImg1);

      butOpenImg2 = new JButton("Open Folder 2");
      butOpenImg2.setToolTipText("Drag and Drop from folder of current image");
      butOpenImg2.addActionListener(this);
      this.add(butOpenImg2);

      butImagePrev = new JButton("Previous");
      butImagePrev.addActionListener(this);
      this.add(butImagePrev);

      butImageNext = new JButton("Next");
      butImageNext.addActionListener(this);
      this.add(butImageNext);

      butClose = new JButton("Close");
      butClose.addActionListener(this);
      this.add(butClose);
   }

   public void actionPerformed(ActionEvent e) {
      Object src = e.getSource();
      if (src == butClose) {
         control.cmdExit();
      } else if (src == butOpenImg1) {
         //#debug
         sc.toDLog().pTest("modifiers=" + e.getModifiers(), null, NorthPanelControl.class, "actionPerformed", ITechLvl.LVL_05_FINE, true);
         control.cmdOpenImageInFolder(1);
      } else if (src == butOpenImg2) {
         //#debug
         sc.toDLog().pTest("modifiers=" + e.getModifiers(), null, NorthPanelControl.class, "actionPerformed", ITechLvl.LVL_05_FINE, true);
         control.cmdOpenImageInFolder(2);
      } else if (src == butReload) {
         control.cmdReload();
      } else if (src == cbShowData) {
         control.cmdShowDataToggle();
      } else if (src == butDebugAnim) {
         control.cmdDebugAnim();
      } else if (src == butImageNext) {
         control.cmdImageNext();
      } else if (src == butImagePrev) {
         control.cmdImagePrev();
      }
   }

}
