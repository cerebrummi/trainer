package vokabeltrainer.table.list.editor;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;

public class CerebrummiFocusTraversalPolicy extends FocusTraversalPolicy
{

   int focusNumber = 0;
   private Component[] focusList;

   public CerebrummiFocusTraversalPolicy(Component[] focusList)
   {
      this.focusList = focusList;
   }

   @Override
   public Component getComponentAfter(Container focusCycleRoot,
         Component aComponent)
   {
      focusNumber = (focusNumber + 1) % focusList.length;
      return focusList[focusNumber];
   }

   @Override
   public Component getComponentBefore(Container focusCycleRoot,
         Component aComponent)
   {
      focusNumber = (focusList.length + focusNumber - 1) % focusList.length;
      return focusList[focusNumber];
   }

   @Override
   public Component getDefaultComponent(Container focusCycleRoot)
   {
      return focusList[0];
   }

   @Override
   public Component getLastComponent(Container focusCycleRoot)
   {
      return focusList[focusList.length - 1];
   }

   @Override
   public Component getFirstComponent(Container focusCycleRoot)
   {
      return focusList[0];
   }
}