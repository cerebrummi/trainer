package vokabeltrainer.types;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Word implements Transferable
{
   public static final DataFlavor WORD_DATA_FLAVOR = new DataFlavor(Word.class,
         "Word");

   private String word;

   public Word(String word)
   {
      this.word = word;
   }

   @Override
   public DataFlavor[] getTransferDataFlavors()
   {
      return new DataFlavor[] { WORD_DATA_FLAVOR };
   }

   @Override
   public boolean isDataFlavorSupported(DataFlavor flavor)
   {
      return WORD_DATA_FLAVOR.equals(flavor);
   }

   @Override
   public Object getTransferData(DataFlavor flavor)
         throws UnsupportedFlavorException, IOException
   {
      Object value = null;
      if (WORD_DATA_FLAVOR.equals(flavor))
      {
         value = word;
      }
      else
      {
         throw new UnsupportedFlavorException(flavor);
      }
      return value;
   }

   public String getWord()
   {
      return word;
   }

   public void setWord(String word)
   {
      this.word = word;
   }
}
