package pasa.cbentley.swing.gif.run;
//package pasa.cbentley.swing.gif.basicplayer.run;
//
//import java.awt.BorderLayout;
//import java.awt.Desktop;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.List;
//
//import javax.swing.AbstractButton;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JFileChooser;
//import javax.swing.JPanel;
//
//import pasa.cbentley.core.src4.ctx.UCtx;
//import pasa.cbentley.core.src4.interfaces.ICallBack;
//import pasa.cbentley.core.src4.logging.Dctx;
//import pasa.cbentley.core.src4.logging.IDLog;
//import pasa.cbentley.core.src4.logging.IStringable;
//import pasa.cbentley.swing.ctx.SwingCtx;
//import pasa.cbentley.swing.data.Listener;
//import pasa.cbentley.swing.gif.basicplayer.ctx.GifCtx;
//import pasa.cbentley.swing.gif.basicplayer.ui.JComponentGif;
//import pasa.cbentley.swing.gif.basicplayer.utils.GIFFilter;
//import pasa.cbentley.swing.gif.basicplayer.utils.GifTransfer;
//import pasa.cbentley.swing.images.anim.AnimOfImageFrames;
//import pasa.cbentley.swing.images.anim.GifEnginePlayOnly;
//import pasa.cbentley.swing.images.anim.ImageFrameProducerGifOptimized;
//import pasa.cbentley.swing.images.ctx.ImgCtx;
//import pasa.cbentley.swing.panels.MemoryPanelProgressBar;
//import pasa.cbentley.swing.widgets.BButtonToggle;
//import pasa.cbentley.swing.window.CBentleyFrame;
//
//public class JPsychoGifPlayerRun implements IStringable, WindowListener, ActionListener, Listener, ICallBack {
//
//   public static void main(final String[] args) {
//      UCtx uc = new UCtx();
//      SwingCtx sc = new SwingCtx(uc);
//      ImgCtx imgc = new ImgCtx(sc);
//      GifCtx gifc = new GifCtx(sc, imgc);
//      final JPsychoGifPlayerRun demo = new JPsychoGifPlayerRun(sc);
//      javax.swing.SwingUtilities.invokeLater(new Runnable() {
//         public void run() {
//            demo.createAndShowGUI();
//         }
//      });
//   }
//
//   private JButton                    butClose;
//
//   private JButton                    butDebugAnim;
//
//   private JButton                    butFaster;
//
//   private JButton                    butFrameNext;
//
//   private AbstractButton             butFramePrev;
//
//   private JButton                    butImageNext;
//
//   private JButton                    butImagePrev;
//
//   private BButtonToggle              butLoop;
//
//   private JButton                    butOpenImg1;
//
//   private JButton                    butOpenImg2;
//
//   private JButton                    butPause;
//
//   private JButton                    butPlay;
//
//   private AbstractButton             butRandom;
//
//   private JButton                    butReload;
//
//   private JButton                    butSkip;
//
//   private JButton                    butSlower;
//
//   private JButton                    butStop;
//
//   private JCheckBox                  cbInverse;
//
//   private JCheckBox                  cbShowData;
//
//   private JCheckBox                  cbUpAndDown;
//
//   private GIFFilter                  filter;
//
//   private CBentleyFrame              frame;
//
//   private GifCtx                     gifc;
//
//   private ImgCtx                     ic;
//
//   private JComponentGif imgView;
//
//   private File                       lastTriedFile;
//
//   private JPanel                     panelNorth;
//
//   private JPanel                     panelSouth;
//
//   private SwingCtx                   sc;
//
//   public JPsychoGifPlayerRun(GifCtx gifc) {
//      this.gifc = gifc;
//      this.sc = gifc.getSwingCtx();
//      this.ic = gifc.getImgCtx();
//      //we do not create GUI elements yet.
//      filter = new GIFFilter();
//   }
//
//   public void actionPerformed(ActionEvent e) {
//      Object src = e.getSource();
//      if (src == butClose) {
//         cmdExit();
//      } else if (src == butOpenImg1) {
//         //#debug
//         toDLog().pTest("modifiers=" + e.getModifiers(), null, JPsychoGifPlayerRun.class, "actionPerformed", IDLog.LVL_05_FINE, true);
//         cmdOpenImageInFolder(1);
//      } else if (src == butOpenImg2) {
//         //#debug
//         toDLog().pTest("modifiers=" + e.getModifiers(), null, JPsychoGifPlayerRun.class, "actionPerformed", IDLog.LVL_05_FINE, true);
//         cmdOpenImageInFolder(2);
//      } else if (src == butPlay) {
//         cmdPlay();
//      } else if (src == butPause) {
//         cmdPause();
//      } else if (src == butStop) {
//         cmdStop();
//      } else if (src == butLoop) {
//         cmdLoop();
//      } else if (src == butSkip) {
//         cmdSkip();
//      } else if (src == butReload) {
//         cmdReload();
//      } else if (src == butSlower) {
//         cmdSlower();
//      } else if (src == butFaster) {
//         cmdFaster();
//      } else if (src == butFrameNext) {
//         cmdFrameNext();
//      } else if (src == butFramePrev) {
//         cmdFramePrev();
//      } else if (src == butRandom) {
//         imgView.cmdRandom();
//      } else if (src == cbInverse) {
//         imgView.cmdIsInverse(cbInverse.isSelected());
//      } else if (src == cbUpAndDown) {
//         imgView.cmdIsUpAndDown(cbUpAndDown.isSelected());
//      } else if (src == cbShowData) {
//         imgView.cmdShowDataToggle();
//      } else if (src == butDebugAnim) {
//         cmdDebugAnim();
//      } else if (src == butImageNext) {
//         cmdImageNext();
//      } else if (src == butImagePrev) {
//         cmdImagePrev();
//      }
//   }
//
//   public void callBack(Object o) {
//      if (o instanceof List) {
//         List<File> list = (List<File>) o;
//         if (list != null && !list.isEmpty()) {
//            File first = list.get(0);
//            if (first.getName().toLowerCase().endsWith(".gif")) {
//               cmdAnimPlay(first);
//            }
//         }
//      } else if (o instanceof AnimOfImageFrames) {
//         AnimOfImageFrames ia = (AnimOfImageFrames) o;
//         cmdAnimPlay(ia);
//      }
//   }
//
//   private void cmdAnimPlay(AnimOfImageFrames ia) {
//      imgView.setImage(ia);
//      imgView.repaint();
//      sc.getPrefs().put("last_gif", ia.getSource());
//      cmdPlay();
//   }
//
//
//   private void cmdDebugAnim() {
//      imgView.cmdDebugAnim();
//   }
//
//   private void cmdExit() {
//      System.exit(0);
//   }
//
//   private void cmdFaster() {
//      imgView.cmdFaster();
//   }
//
//   private void cmdFrameNext() {
//      imgView.cmdFrameNext();
//   }
//
//   private void cmdFramePrev() {
//      imgView.cmdFramePrev();
//   }
//
//   private void cmdImageNext() {
//      if (lastTriedFile != null) {
//         File dir = lastTriedFile.getParentFile();
//         File[] files = dir.listFiles(filter);
//         for (int i = 0; i < files.length; i++) {
//            if (files[i].getName().equals(lastTriedFile.getName())) {
//               //get next file
//               int nextFileIndex = (i + 1) % files.length;
//               cmdAnimPlay(files[nextFileIndex]);
//               break;
//            }
//         }
//      }
//   }
//
//   private void cmdImagePrev() {
//      if (lastTriedFile != null) {
//         File dir = lastTriedFile.getParentFile();
//         File[] files = dir.listFiles(filter);
//         for (int i = 0; i < files.length; i++) {
//            if (files[i].getName().equals(lastTriedFile.getName())) {
//               //get next file
//               int nextFileIndex = (i - 1);
//               if (i < 0) {
//                  nextFileIndex = files.length - 1;
//               }
//               cmdAnimPlay(files[nextFileIndex]);
//               break;
//            }
//         }
//      }
//   }
//
//   private void cmdLoop() {
//      imgView.cmdLoop();
//   }
//
//   private void cmdOpenImageFolder(int id) {
//      String preferenceID = "gifs" + id;
//      File folder = sc.getFolder(preferenceID);
//      Desktop desktop = Desktop.getDesktop();
//      try {
//         desktop.open(folder);
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
//
//   }
//
//
//   private void cmdPause() {
//      imgView.cmdPause();
//      butPause.setEnabled(false);
//      butPlay.setEnabled(true);
//   }
//
//   private void cmdPlay() {
//      imgView.cmdPlay();
//      butPlay.setEnabled(false);
//      butPause.setEnabled(true);
//      butSkip.setEnabled(true);
//      butStop.setEnabled(true);
//   }
//
//   private void cmdReload() {
//      //stop current 
//      cmdStop();
//
//      File lastFile = new File(sc.getPrefs().get("last_gif", ""));
//      if (lastFile.exists() && !lastFile.isDirectory()) {
//         cmdAnimPlay(lastFile);
//      }
//
//   }
//
//   /**
//    * Skipping while paused? allows to
//    */
//   private void cmdSkip() {
//      imgView.cmdForward();
//   }
//
//   private void cmdSlower() {
//      imgView.cmdSlower();
//   }
//
//   /**
//    * Reset Gif to first frame. not playing
//    */
//   private void cmdStop() {
//      imgView.cmdStop();
//      butPlay.setEnabled(true);
//      butStop.setEnabled(false);
//      butSkip.setEnabled(false);
//      butPause.setEnabled(false);
//   }
//
//   protected void createAndShowGUI() {
//      frame = new CBentleyFrame(sc);
//      frame.setTitle("Image Animation GIF Demo");
//      frame.setPrefID("anim_demo");
//
//      //use our own exit procedure
//      frame.addWindowListener(this);
//
//      //default is borderlayout
//      panelNorth = new JPanel();
//
//      butDebugAnim = new JButton("Debug");
//      butDebugAnim.addActionListener(this);
//      panelNorth.add(butDebugAnim);
//
//      cbShowData = new JCheckBox("Show Debug");
//      cbShowData.addActionListener(this);
//      panelNorth.add(cbShowData);
//
//      MemoryPanelProgressBar memPanel = new MemoryPanelProgressBar(sc);
//      panelNorth.add(memPanel);
//
//      butReload = new JButton("Reload");
//      butReload.addActionListener(this);
//      panelNorth.add(butReload);
//
//      butOpenImg1 = new JButton("Open Folder 1");
//      butOpenImg1.setToolTipText("Drag and Drop or Choose a File");
//      butOpenImg1.addActionListener(this);
//      panelNorth.add(butOpenImg1);
//
//      butOpenImg2 = new JButton("Open Folder 2");
//      butOpenImg2.setToolTipText("Drag and Drop from folder of current image");
//      butOpenImg2.addActionListener(this);
//      panelNorth.add(butOpenImg2);
//
//      butImagePrev = new JButton("Previous");
//      butImagePrev.addActionListener(this);
//      panelNorth.add(butImagePrev);
//
//      butImageNext = new JButton("Next");
//      butImageNext.addActionListener(this);
//      panelNorth.add(butImageNext);
//
//      butClose = new JButton("Close");
//      butClose.addActionListener(this);
//      panelNorth.add(butClose);
//
//      panelSouth = new JPanel();
//
//      butPlay = new JButton("Play");
//      butPlay.addActionListener(this);
//      panelSouth.add(butPlay);
//
//      butPause = new JButton("Pause");
//      butPause.addActionListener(this);
//      panelSouth.add(butPause);
//
//      butStop = new JButton("Stop");
//      butStop.addActionListener(this);
//      panelSouth.add(butStop);
//
//      butSlower = new JButton("Slower");
//      butSlower.addActionListener(this);
//      panelSouth.add(butSlower);
//
//      butFaster = new JButton("Faster");
//      butFaster.addActionListener(this);
//      panelSouth.add(butFaster);
//
//      butSkip = new JButton("Skip");
//      butSkip.addActionListener(this);
//      panelSouth.add(butSkip);
//
//      butFramePrev = new JButton("<<frame");
//      butFramePrev.addActionListener(this);
//      panelSouth.add(butFramePrev);
//      butFrameNext = new JButton("frame>>");
//      butFrameNext.addActionListener(this);
//      panelSouth.add(butFrameNext);
//
//      butRandom = new JButton("Random");
//      butRandom.addActionListener(this);
//      panelSouth.add(butRandom);
//
//      cbInverse = new JCheckBox("Inverse");
//      cbInverse.addActionListener(this);
//      panelSouth.add(cbInverse);
//
//      cbUpAndDown = new JCheckBox("UpAndDown");
//      cbUpAndDown.addActionListener(this);
//      panelSouth.add(cbUpAndDown);
//
//      butLoop = new BButtonToggle(sc);
//      butLoop.setText("Loop Infinite");
//      butLoop.addActionListener(this);
//      //panelSouth.add(butLoop);
//
//      imgView = new JComponentGif(sc);
//
//      frame.getContentPane().add(panelNorth, BorderLayout.NORTH);
//      frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
//      frame.getContentPane().add(imgView, BorderLayout.CENTER);
//
//      frame.positionFrame(); //def prefs
//
//      //show the last gif file
//
//      File lastFile = new File(sc.getPrefs().get("last_gif", ""));
//      if (lastFile.exists() && !lastFile.isDirectory()) {
//         cmdAnimPlay(lastFile);
//      }
//
//      GifTransfer gifTransfer = new GifTransfer(sc, this);
//      //FileDrop fd = new FileDrop(frame, this);
//      imgView.setTransferHandler(gifTransfer);
//   }
//
//   public void filesDropped(File[] files) {
//      if (files != null && files.length > 0) {
//         cmdAnimPlay(files[0]);
//      }
//   }
//
//   public IDLog toDLog() {
//      return sc.toDLog();
//   }
//
//   //#mdebug
//   public String toString() {
//      return Dctx.toString(this);
//   }
//
//   public void toString(Dctx dc) {
//      dc.root(this, "ImageAnimGIFDemo");
//   }
//
//   public String toString1Line() {
//      return Dctx.toString1Line(this);
//   }
//
//   public void toString1Line(Dctx dc) {
//      dc.root1Line(this, "ImageAnimGIFDemo");
//   }
//
//   public UCtx toStringGetUCtx() {
//      return sc.getUCtx();
//   }
//   //#enddebug
//
//   public void windowActivated(WindowEvent e) {
//      //#debug
//      toDLog().pEvent(sc.toSD().d1(e), this, JPsychoGifPlayerRun.class, "windowActivated", IDLog.LVL_04_FINER, true);
//   }
//
//   public void windowClosed(WindowEvent e) {
//      //#debug
//      toDLog().pEvent(sc.toSD().d1(e), this, JPsychoGifPlayerRun.class, "windowClosed", IDLog.LVL_04_FINER, true);
//   }
//
//   public void windowClosing(WindowEvent e) {
//      //#debug
//      toDLog().pEvent(sc.toSD().d1(e), this, JPsychoGifPlayerRun.class, "windowClosing", IDLog.LVL_04_FINER, true);
//      cmdExit();
//   }
//
//   public void windowDeactivated(WindowEvent e) {
//      //#debug
//      toDLog().pEvent(sc.toSD().d1(e), this, JPsychoGifPlayerRun.class, "windowDeactivated", IDLog.LVL_04_FINER, true);
//   }
//
//   public void windowDeiconified(WindowEvent e) {
//      //#debug
//      toDLog().pEvent(sc.toSD().d1(e), this, JPsychoGifPlayerRun.class, "windowDeiconified", IDLog.LVL_04_FINER, true);
//   }
//
//   public void windowIconified(WindowEvent e) {
//      //#debug
//      toDLog().pEvent(sc.toSD().d1(e), this, JPsychoGifPlayerRun.class, "windowIconified", IDLog.LVL_04_FINER, true);
//   }
//
//   public void windowOpened(WindowEvent e) {
//      //#debug
//      toDLog().pEvent(sc.toSD().d1(e), this, JPsychoGifPlayerRun.class, "windowOpened", IDLog.LVL_04_FINER, true);
//   }
//
//}
