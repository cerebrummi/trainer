package vokabeltrainer.panels.letterpicture;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.editing.LetterForAnalysis;
import vokabeltrainer.editing.NikudLetter;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.scale.Scale;

public class NikudPictureButtonPanel extends JPanel
{
   private static final long serialVersionUID = 7788782278925301915L;

   private BufferedImage letterPicture;
   private BufferedImage imagePicture;
   private LetterForAnalysis letter;

   private JButton pictureButton;
   private JButton letterButton;
   private JButton germanButton;
   private JButton hebrewButton;

   private JPanel pictureCard;
   private JPanel letterCard;
   private JPanel germanCard;
   private JPanel hebrewCard;

   private CardLayout layout;
   private Scale scale;
   private Translator translator;

   public NikudPictureButtonPanel(App app, Common common, LetterForAnalysis letter, Card[] cards)
   {
      this.letter = letter;
      this.translator = common.getTranslator();
      scale = new Scale(50);

      this.setMinimumSize(new Dimension(50, 50));
      this.setMaximumSize(new Dimension(50, 50));

      this.letterPicture = NikudLetterImage.make(app, letter);

      layout = new CardLayout();
      this.setLayout(layout);
      this.setOpaque(false);

      initPictureCard(app);
      initLetterCard();
      initHebrewCard(app);
      initGermanCard(app);

      for (Card card : cards)
      {
         switch (card)
         {
         case BLANK:
            // nothing
            break;
         case GERMAN:
            this.add(germanCard);
            break;
         case HEBREW:
            this.add(hebrewCard);
            break;
         case LETTER:
            this.add(letterCard);
            break;
         case PICTURE:
            this.add(pictureCard);
            break;
         case LETTER_HANDWRITTEN:
            // nothing
            break;
         }
      }

      initController();
   }

   private void initController()
   {
      pictureButton.addActionListener(_ -> {
         layout.next(this);
      });

      letterButton.addActionListener(_ -> {
         layout.next(this);
      });

      germanButton.addActionListener(_ -> {
         layout.next(this);
      });

      hebrewButton.addActionListener(_ -> {
         layout.next(this);
      });

   }

   private void initGermanCard(App app)
   {
      germanCard = new JPanel(new BorderLayout());
      germanCard.setBackground(app.appColors.alefbet.getButton());
      germanCard.setForeground(app.appColors.alefbet.getButtonForeground());
      germanCard.setOpaque(true);
      germanCard.setPreferredSize(new Dimension(50, 50));
      if (letter.getContent() == NikudLetter.SPACE)
      {
         germanButton = new JButton(new ImageIcon(letterPicture));
      }
      else
      {
         germanButton = new JButton(
               letter.getNikudContent().getGermanPictureName(translator));
         germanButton.setFont(app.appFonts.germanFont.deriveFont(10F));
         germanButton.setContentAreaFilled(false);
      }
      germanButton.setBorder(BorderFactory.createEmptyBorder());
      germanButton.setMargin(new Insets(0, 0, 0, 0));
      germanButton.setOpaque(false);
      germanCard.add(germanButton, BorderLayout.CENTER);
   }

   private void initHebrewCard(App app)
   {
      hebrewCard = new JPanel(new BorderLayout());
      hebrewCard.setOpaque(false);
      hebrewCard.setPreferredSize(new Dimension(50, 50));
      if (letter.getContent() == NikudLetter.SPACE)
      {
         hebrewButton = new JButton(new ImageIcon(letterPicture));
         hebrewButton.setBorder(BorderFactory.createEmptyBorder());
         hebrewButton.setMargin(new Insets(0, 0, 0, 0));
         hebrewButton.setOpaque(false);
      }
      else
      {
         hebrewButton = new JButton(letter.getContent().getTranscript());
         hebrewButton.setBackground(app.appColors.alefbet.getButton());
         hebrewButton.setForeground(app.appColors.alefbet.getButtonForeground());
         hebrewButton.setFont(app.appFonts.germanFont.deriveFont(10F));
         hebrewButton.setContentAreaFilled(false);
      }
      hebrewButton.setBorder(BorderFactory.createEmptyBorder());
      hebrewButton.setMargin(new Insets(0, 0, 0, 0));
      hebrewButton.setOpaque(false);

      hebrewCard.add(hebrewButton, BorderLayout.CENTER);
   }

   private void initLetterCard()
   {
      letterCard = new JPanel(new BorderLayout());
      letterCard.setOpaque(false);
      letterCard.setPreferredSize(new Dimension(50, 50));
      letterButton = new JButton(new ImageIcon(letterPicture));
      letterButton.setBorder(BorderFactory.createEmptyBorder());
      letterButton.setMargin(new Insets(0, 0, 0, 0));
      letterButton.setOpaque(false);

      letterCard.add(letterButton, BorderLayout.CENTER);
   }

   private void initPictureCard(App app)
   {
      pictureCard = new JPanel(new BorderLayout());
      pictureCard.setOpaque(false);
      pictureCard.setPreferredSize(new Dimension(50, 50));
      imagePicture = app.appImages.getLetterPicturesMap()
            .get(letter.getContent());
      if (imagePicture != null)
      {
         pictureButton = new JButton(new ImageIcon(imagePicture));
      }
      else
      {
         pictureButton = new JButton(new ImageIcon(app.appImages
               .getLetterIconsNikudMap().get(letter.getContent())
               .getScaledInstance(scale.getScaleX(), scale.getScaleY(),
                     java.awt.Image.SCALE_SMOOTH)));
      }
      pictureButton.setBorder(BorderFactory.createEmptyBorder());
      pictureButton.setMargin(new Insets(0, 0, 0, 0));
      pictureButton.setOpaque(false);
      pictureCard.add(pictureButton, BorderLayout.CENTER);
   }

   public void nextCard()
   {
      layout.next(this);
   }
}
