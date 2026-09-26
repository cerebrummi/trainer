package vokabeltrainer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.View;
import vokabeltrainer.editing.InternationalDocument;

public class InfoTextField extends JTextField
{
   @Serial
   private static final long serialVersionUID = -7140719570197015380L;
   private JButton button;
   private String title;

   public InfoTextField(App app, View view, String borderTitle, String... infoText)
   {
      this.title = borderTitle;
      setDocument(new InternationalDocument(40));
      setFont(view.getNimbus().getDefaults().getFont("internationalFont"));
      button = new JButton(
            new ImageIcon(app.appImages.getInfoButtonIcon()));
      button.setMargin(new Insets(-5, -20, -5, -20));
      button.setBackground(new Color(0, 0, 0, 0));
      button.setForeground(app.appColors.getGold());
      button.setPreferredSize(new Dimension(14, 32));

      button.addActionListener(_ -> {
         JOptionPane.showMessageDialog(this, "", app.settings.getWindowTitle(),
               JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(app, infoText)));
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

      this.setBorder(border);
   }
}
