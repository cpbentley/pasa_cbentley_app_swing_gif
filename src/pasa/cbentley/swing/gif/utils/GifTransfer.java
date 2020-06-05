/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.swing.gif.utils;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.TransferHandler;

import pasa.cbentley.core.src4.interfaces.ICallBack;
import pasa.cbentley.swing.ctx.SwingCtx;

/**
 * Handles the drag and drop of GIF files.
 * 
 * @author Charles Bentley
 *
 */
public class GifTransfer extends TransferHandler {

   /**
    * 
    */
   private static final long serialVersionUID = -867297165064094819L;

   private SwingCtx          sc;

   private ICallBack         callBack;

   public GifTransfer(SwingCtx sc, ICallBack callBack) {
      this.sc = sc;
      this.callBack = callBack;

   }

   public boolean canImport(TransferHandler.TransferSupport support) {
      if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
         return false;
      }
      return true;
   }

   public boolean importData(TransferHandler.TransferSupport support) {
      if (!canImport(support)) {
         return false;
      }

      Transferable t = support.getTransferable();
      try {
         List<File> list = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
         callBack.callBack(list);
      } catch (UnsupportedFlavorException e) {
         return false;
      } catch (IOException e) {
         return false;
      }
      return true;
   }
}
