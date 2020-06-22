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
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.Main;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.panels.letterpicture.LetterPictureAlphabetPanel;
import vokabeltrainer.panels.letterpicture.LetterPictureWordPanel;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class LetterPicturesPanel extends BackgroundPanelTiled
{
   private static final long serialVersionUID = 9130321171813967337L;

   private LetterPictureWordPanel wordPanel;
   private JButton pictureInfoButton;
   private JPanel letterPanel;
   public LetterPicturesPanel()
   {
      setLayout(new TotemLayout(this, 10));

      JPanel horizontal0 = new JPanel();
      horizontal0.setLayout(new TrainLayout(horizontal0, 15));
      horizontal0.setOpaque(false);
      horizontal0.add(initLetterPanel());

      add(horizontal0);
      add(initPictureWord());

      initController();
   }

   private Component initPictureWord()
   {
      wordPanel = new LetterPictureWordPanel();
      return wordPanel;
   }

   private Component initLetterPanel()
   {
      letterPanel = new JPanel();
      letterPanel.setLayout(new TotemLayout(letterPanel));
      letterPanel.setOpaque(false);

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.setOpaque(false);

      JLabel title = new JLabel("Alphabet");
      title.setFont(Main.getGermanFont(24F));
      horizontal.add(title);

      pictureInfoButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      pictureInfoButton.setBackground(new Color(0, 0, 0, 0));
      pictureInfoButton.setMinimumSize(new Dimension(50, 50));
      pictureInfoButton.setMaximumSize(new Dimension(50, 50));
      pictureInfoButton.setMargin(new Insets(0, 0, 0, 0));
      horizontal.add(pictureInfoButton);

      letterPanel.add(horizontal);
      letterPanel.add(new LetterPictureAlphabetPanel());

      return letterPanel;
   }

   private void initController()
   {
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
