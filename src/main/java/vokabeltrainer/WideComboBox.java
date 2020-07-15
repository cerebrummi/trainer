package vokabeltrainer;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class WideComboBox<E> extends JComboBox<E>
{

   private static final long serialVersionUID = -892693496529388870L;

   public WideComboBox()
   {
   }

   public WideComboBox(final E items[])
   {
      super(items);
   }

   public WideComboBox(Vector<E> items)
   {
      super(items);
   }

   public WideComboBox(ComboBoxModel<E> aModel)
   {
      super(aModel);
   }

   private boolean layingOut = false;

   public void doLayout()
   {
      try
      {
         layingOut = true;
         super.doLayout();
      }
      finally
      {
         layingOut = false;
      }
   }

   public Dimension getSize()
   {
      Dimension dim = super.getSize();
      if (!layingOut)
         dim.width = Math.max(dim.width, getPreferredSize().width);
      return dim;
   }
}
