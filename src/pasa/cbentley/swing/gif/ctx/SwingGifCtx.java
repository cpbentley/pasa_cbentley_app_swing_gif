/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.swing.gif.ctx;

import java.awt.image.BufferedImage;
import java.util.List;

import pasa.cbentley.core.src4.ctx.ACtx;
import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.images.anim.ImageFrame;
import pasa.cbentley.swing.images.ctx.ImgCtx;

/**
 * Depends on SwingCtx for the {@link BufferedImage}, {@link ImageFrame} framework
 * <br>
 * @author Charles Bentley
 *
 */
public class SwingGifCtx extends ACtx {

   private final SwingCtx sc;

   private final ImgCtx imgc;

   public SwingGifCtx(SwingCtx sc, ImgCtx imgc) {
      super(sc.getUC());
      this.sc = sc;
      this.imgc = imgc;
   }
   

   public SwingCtx getSwingCtx() {
      return sc;
   }

   public ImgCtx getImgCtx() {
      return imgc;
   }
   
   public void addI18NKey(List<String> list) {
      list.add("i18nGif");
   }
   
   public int getCtxID() {
      return 455;
   }
}
