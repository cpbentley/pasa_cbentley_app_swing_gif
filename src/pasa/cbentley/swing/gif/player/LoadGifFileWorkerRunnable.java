package pasa.cbentley.swing.gif.player;

import java.io.IOException;
import java.io.InputStream;

import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.images.anim.GifEnginePlayOnly;
import pasa.cbentley.swing.images.anim.AnimOfImageFrames;
import pasa.cbentley.swing.images.anim.ImageFrameProducerGifOptimized;
import pasa.cbentley.swing.images.ctx.ImgCtx;
import pasa.cbentley.swing.interfaces.ICallBackSwing;

public class LoadGifFileWorkerRunnable implements Runnable {

   private final SwingGifCtx         gifc;

   private final InputStream    inputStream;

   private final ICallBackSwing callBack;

   private String               name;

   public LoadGifFileWorkerRunnable(SwingGifCtx gifc, InputStream inputStream, String name, ICallBackSwing callBack) {
      this.gifc = gifc;
      this.inputStream = inputStream;
      this.name = name;
      this.callBack = callBack;

   }

   public void run() {
      GifEnginePlayOnly engine = new GifEnginePlayOnly(gifc.getImgCtx());
      try {
         engine.setSourceToArray(inputStream);
      } catch (IOException e) {
         e.printStackTrace();
         gifc.getSwingCtx().callBackInMainThread(callBack, e);
         return;
      }
      //create a frame producer
      SwingCtx sc = gifc.getSwingCtx();
      ImgCtx imgc = gifc.getImgCtx();
      ImageFrameProducerGifOptimized producer = new ImageFrameProducerGifOptimized(imgc, engine);
      AnimOfImageFrames imageAnim = new AnimOfImageFrames(imgc, producer);
      imageAnim.setSource(name);
      gifc.getSwingCtx().callBackInMainThread(callBack, imageAnim);
   }
}
