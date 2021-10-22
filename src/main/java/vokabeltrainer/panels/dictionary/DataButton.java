package vokabeltrainer.panels.dictionary;

import java.util.Collections;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import vokabeltrainer.types.Expression;

public class DataButton extends JButton
{
   private static final long serialVersionUID = 5406649403725608010L;

   private List<Expression> data = Collections.emptyList();

   private int index = -1;

   public List<Expression> getData()
   {
      return data;
   }

   public void setData(List<Expression> data)
   {
      this.data = data;
      this.index = 0;
   }

   public DataButton()
   {
      // TODO Auto-generated constructor stub
   }

   public DataButton(Icon icon)
   {
      super(icon);
      // TODO Auto-generated constructor stub
   }

   public DataButton(String text)
   {
      super(text);
      // TODO Auto-generated constructor stub
   }

   public DataButton(Action a)
   {
      super(a);
      // TODO Auto-generated constructor stub
   }

   public DataButton(String text, Icon icon)
   {
      super(text, icon);
   }

   public Expression getIndexExpression()
   {
      if (!data.isEmpty())
      {
         return data.get(index);
      }

      return null;
   }

   public void nextIndex()
   {
      if (!data.isEmpty())
      {
         index = (index + 1) % data.size();
      }

   }

}
