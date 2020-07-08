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

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.common.Main;
import vokabeltrainer.editing.HebrewLetter;

public class LetterPictureButtonPanel extends JPanel
{
   private static final long serialVersionUID = 7788782278925301915L;

   private BufferedImage picture;
   private String nameGerman;
   private HebrewLetter nameHebrew;

   private JButton pictureButton;
   private JButton letterButton;
   private JButton germanButton;
   private JButton hebrewButton;
   private JButton blankButton;

   private JPanel pictureCard;
   private JPanel letterCard;
   private JPanel germanCard;
   private JPanel hebrewCard;
   private JPanel blankCard;

   private CardLayout layout;

   public LetterPictureButtonPanel(BufferedImage picture, String nameGerman,
         HebrewLetter nameHebrew, Card[] cards)
   {
      this.nameGerman = nameGerman;
      this.nameHebrew = nameHebrew;

      this.setMinimumSize(new Dimension(50, 50));
      this.setMaximumSize(new Dimension(50, 50));

      this.picture = picture;
      layout = new CardLayout();
      this.setLayout(layout);
      this.setOpaque(false);

      initPictureCard();
      initLetterCard();
      initHebrewCard();
      initGermanCard();
      initBlankCard();

      for(Card card : cards)
      {
         switch(card)
         {
         case BLANK:
            this.add(blankCard);
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
         }
      }

      initController();
   }

   private void initController()
   {
      pictureButton.addActionListener(event -> {
         layout.next(this);
      });

      letterButton.addActionListener(event -> {
         layout.next(this);
      });

      germanButton.addActionListener(event -> {
         layout.next(this);
      });

      hebrewButton.addActionListener(event -> {
         layout.next(this);
      });
      
      blankButton.addActionListener(event -> {
         layout.next(this);
      });

   }

   private void initGermanCard()
   {
      germanCard = new JPanel(new BorderLayout());
      germanCard.setOpaque(false);
      germanCard.setPreferredSize(new Dimension(50, 50));
      if (nameHebrew.getTranscript().equals(HebrewLetter.SPACE.getTranscript()))
      {
         germanButton = new JButton(new ImageIcon(picture));
         germanButton.setBorder(BorderFactory.createEmptyBorder());
         germanButton.setMargin(new Insets(0, 0, 0, 0));
         germanButton.setOpaque(false);
      }
      else
      {
         germanButton = new JButton(nameGerman);
         germanButton.setFont(Main.getGermanFont(10F));
         germanButton.setBorder(BorderFactory.createEmptyBorder());
         germanButton.setMargin(new Insets(0, 0, 0, 0));
         germanButton.setContentAreaFilled(false);
         germanButton.setOpaque(false);
      }
      germanCard.add(germanButton, BorderLayout.CENTER);
   }

   private void initHebrewCard()
   {
      hebrewCard = new JPanel(new BorderLayout());
      hebrewCard.setOpaque(false);
      hebrewCard.setPreferredSize(new Dimension(50, 50));
      if (nameHebrew.getTranscript().equals(HebrewLetter.SPACE.getTranscript()))
      {
         hebrewButton = new JButton(new ImageIcon(picture));
         hebrewButton.setBorder(BorderFactory.createEmptyBorder());
         hebrewButton.setMargin(new Insets(0, 0, 0, 0));
         hebrewButton.setOpaque(false);
      }
      else
      {
         hebrewButton = new JButton(nameHebrew.getTranscript());
         hebrewButton.setBorder(BorderFactory.createEmptyBorder());
         hebrewButton.setMargin(new Insets(0, 0, 0, 0));
         hebrewButton.setFont(Main.getGermanFont(10F));
         hebrewButton.setContentAreaFilled(false);
         hebrewButton.setOpaque(false);
      }
      hebrewCard.add(hebrewButton, BorderLayout.CENTER);
   }

   private void initLetterCard()
   {
      letterCard = new JPanel(new BorderLayout());
      letterCard.setOpaque(false);
      letterCard.setPreferredSize(new Dimension(50, 50));
      if (nameHebrew.getTranscript().equals(HebrewLetter.SPACE.getTranscript()))
      {
         letterButton = new JButton(new ImageIcon(picture));
         letterButton.setBorder(BorderFactory.createEmptyBorder());
         letterButton.setMargin(new Insets(0, 0, 0, 0));
         letterButton.setOpaque(false);
      }
      else
      {
         letterButton = new JButton("<html>"
               + HebrewLetter.getLetterUnicode(nameHebrew) + "</html>");
         letterButton.setFont(Main.getHebrewFont(30F));
         letterButton.setBorder(BorderFactory.createEmptyBorder());
         letterButton.setContentAreaFilled(false);
         letterButton.setMargin(new Insets(-10, 0, 0, 0));
         letterButton.setOpaque(false);
      }

      letterCard.add(letterButton, BorderLayout.CENTER);
   }

   private void initPictureCard()
   {
      pictureCard = new JPanel(new BorderLayout());
      pictureCard.setOpaque(false);
      pictureCard.setPreferredSize(new Dimension(50, 50));
      pictureButton = new JButton(new ImageIcon(picture));
      pictureButton.setBorder(BorderFactory.createEmptyBorder());
      pictureButton.setMargin(new Insets(0, 0, 0, 0));
      pictureButton.setOpaque(false);
      pictureCard.add(pictureButton, BorderLayout.CENTER);
   }
   
   private void initBlankCard()
   {
      blankCard = new JPanel(new BorderLayout());
      blankCard.setOpaque(false);
      blankCard.setPreferredSize(new Dimension(50, 50));
      blankButton = new JButton(new ImageIcon(ApplicationImages.getLetterEmpty()));
      blankButton.setBorder(BorderFactory.createEmptyBorder());
      blankButton.setMargin(new Insets(0, 0, 0, 0));
      blankButton.setOpaque(false);
      blankCard.add(blankButton, BorderLayout.CENTER);
      
   }

   public void nextCard()
   {
      layout.next(this);
   }
}
