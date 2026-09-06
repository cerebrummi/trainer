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
import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
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
   private Translator translator;

   public AlefbetPanel(App app, Common common)
   {
      translator = common.getTranslator();
      setLayout(new BullsEyeLayout(this));
      setOpaque(true);
      setBackground(app.appColors.alefbet.getPanelBackground());

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 165));
      horizontal.setOpaque(false);
      horizontal.setBackground(app.appColors.getTransparent());

      this.letterPictureAlphabetPanel = new LetterPictureAlphabetPanel(app, common);
      letterPictureAlphabetPanel.setOpaque(false);
      letterPictureAlphabetPanel.setBackground(app.appColors.getTransparent());

      horizontal.add(initLetterPanel(app));
      horizontal.add(letterPictureAlphabetPanel);
      horizontal.add(initButtons(app));

      add(horizontal);

      initController(app);
      this.setFocusTraversalPolicyProvider(true);
      this.setFocusTraversalPolicy(
            this.letterPictureAlphabetPanel.getFocusTraversalPolicy());
   }

   private Component initButtons(App app)
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 14));
      vertical.setOpaque(false);

      JPanel filler = new JPanel();
      filler.setMinimumSize(new Dimension(200, 1));
      filler.setMaximumSize(new Dimension(200, 1));
      filler.setOpaque(false);

      resultButton = new JButton(
            translator.realisticTranslate(app, Translation.AUSWERTEN));
      resultButton.setBackground(app.appColors.alefbet.getButton());
      resultButton.setForeground(app.appColors.alefbet.getButtonForeground());
      resultButton.setFont(app.appFonts.buttonFont);

      resetButton = new JButton(
            translator.realisticTranslate(app, Translation.ZURUECKSETZEN));
      resetButton.setBackground(app.appColors.alefbet.getButton());
      resetButton.setForeground(app.appColors.alefbet.getButtonForeground());
      resetButton.setFont(app.appFonts.buttonFont);

      vertical.add(filler);
      vertical.add(resultButton);
      vertical.add(resetButton);
      return vertical;
   }

   private Component initLetterPanel(App app)
   {
      letterPanel = new JPanel();
      letterPanel.setLayout(new TotemLayout(letterPanel));
      letterPanel.setOpaque(false);

      JLabel title = new JLabel(
            translator.realisticTranslate(app, Translation.ALEFBET_UEBEN));
      title.setForeground(app.appColors.alefbet.getTextForeground());
      title.setFont(app.appFonts.germanFont.deriveFont(24F));
      letterPanel.add(title);

      pictureInfoButton = new JButton(
            new ImageIcon(app.appImages.getInfoButtonIcon()));
      pictureInfoButton.setBackground(app.appColors.alefbet.getButton());
      pictureInfoButton
            .setForeground(app.appColors.alefbet.getButtonForeground());
      pictureInfoButton.setMinimumSize(new Dimension(50, 50));
      pictureInfoButton.setMaximumSize(new Dimension(50, 50));
      pictureInfoButton.setMargin(new Insets(0, 0, 0, 0));
      letterPanel.add(pictureInfoButton);

      return letterPanel;
   }

   private void initController(App app)
   {
      resultButton.addActionListener(_ -> {
         for (JTextComponent jtc : letterPictureAlphabetPanel.getTextFields())
         {
            if (((LetterTextField) jtc).isOkay())
            {
               jtc.setBackground(app.appColors.getGreen());
            }
            else if (jtc.getText().isBlank())
            {
               jtc.setBackground(app.appColors.getLightYellow());
            }
            else
            {
               jtc.setBackground(app.appColors.getLightGrayGold());
            }
         }
      });

      resetButton.addActionListener(_ -> {
         for (JTextComponent jtc : letterPictureAlphabetPanel.getTextFields())
         {
            jtc.setBackground(app.appColors.alefbet.getButton());
            jtc.setForeground(app.appColors.alefbet.getButtonForeground());
            jtc.setText("");
         }
      });

      pictureInfoButton.addActionListener(_ -> {
         JOptionPane.showMessageDialog(letterPanel, "",
               app.settings.getWindowTitle(), JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(app,
                     translator
                           .realisticTranslate(app, Translation.BILDERBUCHSTABEN),
                     translator.realisticTranslate(app, 
                           Translation.ALLE_BILDER_KANN_MAN),
                     translator.realisticTranslate(app, 
                           Translation.AUCH_EINZELN_ANKLICKEN))));
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
