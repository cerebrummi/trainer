package vokabeltrainer.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.Main;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.panels.letterpicture.LetterPictureAlphabetPanel;
import vokabeltrainer.panels.letterpicture.LetterTextField;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class AlefbetPanel extends BackgroundPanelTiled
{
   private static final long serialVersionUID = 9130321171813967337L;

   private JButton pictureInfoButton;
   private JPanel letterPanel;
   private LetterPictureAlphabetPanel letterPictureAlphabetPanel;
   private JButton resultButton;
   private JButton resetButton;
   
   public AlefbetPanel()
   {
      setLayout(new TrainLayout(this, 165));
      this.letterPictureAlphabetPanel = new LetterPictureAlphabetPanel();
      
      add(initLetterPanel());
      add(letterPictureAlphabetPanel);
      add(initButtons());

      initController();
   }

   private Component initButtons()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 14));
      
      JPanel filler = new JPanel();
      filler.setMinimumSize(new Dimension(200,1));
      filler.setMaximumSize(new Dimension(200,1));
      
      resultButton = new JButton("auswerten");
      resultButton.setFont(Settings.getButtonFont());
      resetButton = new JButton("zurücksetzen");
      resetButton.setFont(Settings.getButtonFont());
      
      vertical.add(filler);
      vertical.add(resultButton);
      vertical.add(resetButton);
      return vertical;
   }

   private Component initLetterPanel()
   {
      letterPanel = new JPanel();
      letterPanel.setLayout(new TotemLayout(letterPanel));
      letterPanel.setOpaque(false);

      JLabel title = new JLabel(" Alefbet üben");
      title.setFont(Main.getGermanFont(24F));
      letterPanel.add(title);

      pictureInfoButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      pictureInfoButton.setBackground(new Color(0, 0, 0, 0));
      pictureInfoButton.setMinimumSize(new Dimension(50, 50));
      pictureInfoButton.setMaximumSize(new Dimension(50, 50));
      pictureInfoButton.setMargin(new Insets(0, 0, 0, 0));
      letterPanel.add(pictureInfoButton);

      return letterPanel;
   }

   private void initController()
   {
      resultButton.addActionListener(event -> {
        for(JTextComponent jtc : letterPictureAlphabetPanel.getTextFields())
        {
           if(((LetterTextField)jtc).isOkay())
           {
              jtc.setBackground(Settings.getGreen());
           }
           else if(jtc.getText().isBlank())
           {
              jtc.setBackground(Settings.getLightYellow());
           }
           else
           {
              jtc.setBackground(Settings.getLightGrayGold());
           }
        }
      });
      
      resetButton.addActionListener(event -> {
        for(JTextComponent jtc : letterPictureAlphabetPanel.getTextFields())
        {
           jtc.setBackground(Settings.getLightYellow());
           jtc.setText("");
        }
      });
      
      pictureInfoButton.addActionListener(event -> {
         JOptionPane.showMessageDialog(letterPanel, "",
               "Cerebrummi©", JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make("Bilderbuchstaben",
                     "Alle Bilder kann man", "auch einzeln anklicken.")));
      });

      pictureInfoButton.addMouseListener(new MouseListener()
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
   }
}
