package vokabeltrainer.panels.sentences;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.JList;

import vokabeltrainer.types.Word;

public class DragGestureHandler implements DragGestureListener
{

   private JList<String> list;

   public DragGestureHandler(JList<String> list)
   {
      this.list = list;
   }

   @Override
   public void dragGestureRecognized(DragGestureEvent dragevent)
   {
      Object selectedValue = list.getSelectedValue();
      if (selectedValue instanceof DragButton)
      {
         String button = (String) selectedValue;
         Transferable t = new Word(button);
         DragSource ds = dragevent.getDragSource();
         ds.startDrag(dragevent, null, t, new DragSourceHandler());
      }
   }
}
