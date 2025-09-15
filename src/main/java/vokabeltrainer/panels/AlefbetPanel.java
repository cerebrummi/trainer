package vokabeltrainer.panels;

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

import vokabeltrainer.TextImage;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.common.colors.AlefbetColors;
import vokabeltrainer.panels.letterpicture.LetterPictureAlphabetPanel;
import vokabeltrainer.panels.letterpicture.LetterTextField;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class AlefbetPanel extends JPanel
{
   private static final long serialVersionUID = 9130321171813967337L;

   private JButton pictureInfoButton;
   private JPanel letterPanel;
   private LetterPictureAlphabetPanel letterPictureAlphabetPanel;
   private JButton resultButton;
   private JButton resetButton;
   private Translator translator = Common.getTranslator();

   public AlefbetPanel()
   {
      setLayout(new BullsEyeLayout(this));
      setOpaque(true);
      setBackground(AlefbetColors.getPanelBackground());

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 165));
      horizontal.setOpaque(false);
      horizontal.setBackground(ApplicationColors.getTransparent());
      
      this.letterPictureAlphabetPanel = new LetterPictureAlphabetPanel();
      letterPictureAlphabetPanel.setOpaque(false);
      letterPictureAlphabetPanel.setBackground(ApplicationColors.getTransparent());
      
      horizontal.add(initLetterPanel());
      horizontal.add(letterPictureAlphabetPanel);
      horizontal.add(initButtons());

      add(horizontal);

      initController();
      this.setFocusTraversalPolicyProvider(true);
      this.setFocusTraversalPolicy(
            this.letterPictureAlphabetPanel.getFocusTraversalPolicy());
   }

   private Component initButtons()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 14));
      vertical.setOpaque(false);

      JPanel filler = new JPanel();
      filler.setMinimumSize(new Dimension(200, 1));
      filler.setMaximumSize(new Dimension(200, 1));
      filler.setOpaque(false);

      resultButton = new JButton(translator.realisticTranslate(Translation.AUSWERTEN));
      resultButton.setBackground(AlefbetColors.getButton());
      resultButton.setForeground(AlefbetColors.getButtonForeground());
      resultButton.setFont(ApplicationFonts.getButtonFont());
      
      resetButton = new JButton(
            translator.realisticTranslate(Translation.ZURUECKSETZEN));
      resetButton.setBackground(AlefbetColors.getButton());
      resetButton.setForeground(AlefbetColors.getButtonForeground());
      resetButton.setFont(ApplicationFonts.getButtonFont());

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

      JLabel title = new JLabel(
            translator.realisticTranslate(Translation.ALEFBET_UEBEN));
      title.setForeground(AlefbetColors.getTextForeground());
      title.setFont(ApplicationFonts.getGermanFont(24F));
      letterPanel.add(title);

      pictureInfoButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      pictureInfoButton.setBackground(AlefbetColors.getButton());
      pictureInfoButton.setForeground(AlefbetColors.getButtonForeground());
      pictureInfoButton.setMinimumSize(new Dimension(50, 50));
      pictureInfoButton.setMaximumSize(new Dimension(50, 50));
      pictureInfoButton.setMargin(new Insets(0, 0, 0, 0));
      letterPanel.add(pictureInfoButton);

      return letterPanel;
   }

   private void initController()
   {
      resultButton.addActionListener(_ -> {
         for (JTextComponent jtc : letterPictureAlphabetPanel.getTextFields())
         {
            if (((LetterTextField) jtc).isOkay())
            {
               jtc.setBackground(ApplicationColors.getGreen());
            }
            else if (jtc.getText().isBlank())
            {
               jtc.setBackground(ApplicationColors.getLightYellow());
            }
            else
            {
               jtc.setBackground(ApplicationColors.getLightGrayGold());
            }
         }
      });

      resetButton.addActionListener(_ -> {
         for (JTextComponent jtc : letterPictureAlphabetPanel.getTextFields())
         {
            jtc.setBackground(AlefbetColors.getButton());
            jtc.setForeground(AlefbetColors.getButtonForeground());
            jtc.setText("");
         }
      });

      pictureInfoButton.addActionListener(_ -> {
         JOptionPane.showMessageDialog(letterPanel, "",
               Settings.getWindowTitle(), JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(
                     translator.realisticTranslate(Translation.BILDERBUCHSTABEN),
                     translator.realisticTranslate(Translation.ALLE_BILDER_KANN_MAN),
                     translator
                           .realisticTranslate(Translation.AUCH_EINZELN_ANKLICKEN))));
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
