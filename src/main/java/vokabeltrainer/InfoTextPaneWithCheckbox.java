package vokabeltrainer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;

public class InfoTextPaneWithCheckbox extends JTextPane
{
   private static final long serialVersionUID = -1004028916251440520L;
   private JCheckBox box = new JCheckBox();

   public InfoTextPaneWithCheckbox(String labeltext)
   {
      box.setFont(ApplicationFonts.getButtonFont());
      box.setMargin(new Insets(-5, 5, -5, -20));
      box.setBackground(new Color(0, 0, 0, 0));
      box.setForeground(ApplicationColors.getGold());
      box.setPreferredSize(new Dimension(32, 32));
      box.addActionListener(event -> {
         System.out.println("Action");
      });
      box.addMouseListener(new MouseAdapter()
      {

         @Override
         public void mouseClicked(MouseEvent e)
         {

         }

         @Override
         public void mousePressed(MouseEvent e)
         {
            
         }

         @Override
         public void mouseReleased(MouseEvent e)
         {

         }

         @Override
         public void mouseEntered(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (box.isSelected())
            {
               box.setSelected(false);
            }
            else
            {
               box.setSelected(true);
            }
         }

         @Override
         public void mouseExited(MouseEvent e)
         {

            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         }

      });

      ComponentTitledBorder border = new ComponentTitledBorder(box, this,
            BorderFactory.createTitledBorder(labeltext), 25);

      this.setBorder(border);
   }

   @Override
   public void addMouseListener(MouseListener mlistener)
   {
      if (box != null)
      {
         box.addMouseListener(mlistener);
      }
      else
      {
         super.addMouseListener(mlistener);
      }
   }
}
