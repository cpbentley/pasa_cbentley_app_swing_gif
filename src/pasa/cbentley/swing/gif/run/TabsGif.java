package pasa.cbentley.swing.gif.run;

import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.gif.ui.AbstractGifTab;
import pasa.cbentley.swing.imytab.TabbedBentleyPanel;

public class TabsGif extends TabbedBentleyPanel {

   protected SwingGifCtx gifc;
   private AbstractGifTab tab1;
   private AbstractGifTab tab2;
   private AbstractGifTab tab3;

   public TabsGif(SwingGifCtx gifc) {
      super(gifc.getSwingCtx(), "tabs_gif");
      this.gifc = gifc;
   }

   public void disposeTab() {

   }

   public void initTabs() {
      String gif1 = "/gifs/pasc_ice_black_clouds_1024.gif";
      tab1 = new AbstractGifTab(gifc, "tab_1", gif1, "tab_1.key");
      
      String gif2 = "/gifs/create_new_key.gif";
      tab2 = new AbstractGifTab(gifc, "tab_2", gif2, "tab_2.key");
     
      String gif3 = "/gifs/under_construction.gif";
      tab3 = new AbstractGifTab(gifc, "tab_3", gif3, "tab_3.key");
     
      this.addMyTab(tab1);
      this.addMyTab(tab2);
      this.addMyTab(tab3);
   }

   public AbstractGifTab getFirst() {
      return tab1;
   }
}
