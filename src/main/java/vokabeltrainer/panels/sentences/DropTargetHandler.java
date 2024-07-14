package vokabeltrainer.panels.sentences;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;

import javax.swing.JList;
import vokabeltrainer.types.Word;

public class DropTargetHandler implements DropTargetListener
{

   private JList<String> list;

   public DropTargetHandler(JList<String> resultList)
   {
      this.list = resultList;
   }

   public void dragEnter(DropTargetDragEvent dtde)
   {
      if (dtde.getTransferable()
            .isDataFlavorSupported(Word.WORD_DATA_FLAVOR))
      {
         System.out.println("Accept...");
         dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
      }
      else
      {
         System.out.println("Drag rejected");
         dtde.rejectDrag();
      }
   }

   public void dragOver(DropTargetDragEvent dtde)
   {
   }

   public void dropActionChanged(DropTargetDragEvent dtde)
   {
   }

   public void dragExit(DropTargetEvent dte)
   {
   }

   public void drop(DropTargetDropEvent dtde)
   {
      System.out.println("Dropped...");
      if (dtde.getTransferable()
            .isDataFlavorSupported(Word.WORD_DATA_FLAVOR))
      {
         Transferable t = dtde.getTransferable();
         if (t.isDataFlavorSupported(Word.WORD_DATA_FLAVOR))
         {
            try
            {
               Object transferData = t
                     .getTransferData(Word.WORD_DATA_FLAVOR);
               if (transferData instanceof Word)
               {
                  Word word = (Word) transferData;
                  dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                  list.add(new DragButton(word));
                  list.revalidate();
                  list.repaint();
               }
               else
               {
                  dtde.rejectDrop();
               }
            }
            catch (UnsupportedFlavorException ex)
            {
               ex.printStackTrace();
               dtde.rejectDrop();
            }
            catch (IOException ex)
            {
               ex.printStackTrace();
               dtde.rejectDrop();
            }
         }
         else
         {
            dtde.rejectDrop();
         }
      }
   }

}