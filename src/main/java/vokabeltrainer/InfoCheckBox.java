package vokabeltrainer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Settings;

public class InfoCheckBox extends JCheckBox
{

   /**
    * 
    */
   private static final long serialVersionUID = 7704010484130304775L;

   private JButton button;
   
   public InfoCheckBox(String borderTitle, String... infoText)
   {
      button = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      button.setMargin(new Insets(-5, -20, -5, -20));
      button.setOpaque(false);
      button.setBackground(ApplicationColors.getTransparent());
      button.setForeground(ApplicationColors.getGold());
      button.setPreferredSize(new Dimension(14, 32));

      button.addActionListener(event -> {
         JOptionPane.showMessageDialog(this, "",
               Settings.getWindowTitle(), JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(infoText)));
      });
      
      button.addMouseListener(new MouseAdapter()
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
         }

         @Override
         public void mouseExited(MouseEvent e)
         {

            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         }

      });

      ComponentTitledBorder border = new ComponentTitledBorder(button, this,
            BorderFactory.createTitledBorder(borderTitle), 25);
      
      setOpaque(false);
      setBackground(ApplicationColors.getTransparent());
      this.setBorder(border);
      this.setBorderPainted(true);
   }
}
