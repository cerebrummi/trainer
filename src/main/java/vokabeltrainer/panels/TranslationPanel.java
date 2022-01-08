package vokabeltrainer.panels;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Main;
import vokabeltrainer.common.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Translation;
import vokabeltrainer.types.TranslationCode;

public class TranslationPanel extends JPanel
{
   private static final long serialVersionUID = 369293645105172512L;
   private JComboBox<TranslationCode> fromLanguage;
   private JComboBox<TranslationCode> toLanguage;
   private Translator translator = Common.getTranslator();
   private JPanel leftSide;

   TranslationPanel()
   {
      setLayout(new BullsEyeLayout(this));

      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.setOpaque(false);
      tabbedPane.setFont(Main.getGermanFont(16F));

      tabbedPane.addTab("", new ImageIcon(ApplicationImages.getLanguages()),
            initAddLanguage());

      tabbedPane.addTab("", new ImageIcon(ApplicationImages.getNewWordSmall()),
            initChooseLanguage());

      add(tabbedPane);

      initController();
   }

   private Component initChooseLanguage()
   {
      // TODO Auto-generated method stub
      return new JPanel();
   }

   private Component initAddLanguage()
   {
      JPanel center = new JPanel();
      BullsEyeLayout centerLayout = new BullsEyeLayout(center);
      center.setLayout(centerLayout);

      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 15);
      vertical.setLayout(verticalLayout);

      JPanel wrapper = new JPanel();
      BullsEyeLayout wrapperLayout = new BullsEyeLayout(wrapper);
      wrapper.setLayout(wrapperLayout);

      JPanel horizontal1 = new JPanel();
      TrainLayout horizontal1Layout = new TrainLayout(horizontal1, 15);
      horizontal1.setLayout(horizontal1Layout);

      fromLanguage = new JComboBox<>(TranslationCode.valuesAvailable());
      JLabel label = new JLabel(">>>");
      label.setFont(Settings.getToolBarButtonFont());
      toLanguage = new JComboBox<>(TranslationCode.values());

      JPanel horizontal2 = new JPanel();
      TrainLayout horizontal2Layout = new TrainLayout(horizontal2, 15);
      horizontal2.setLayout(horizontal2Layout);

      wrapper.add(horizontal1);

      horizontal1.add(fromLanguage);
      horizontal1.add(label);
      horizontal1.add(toLanguage);

      leftSide = initAddLanguageLeftSide();
      
      horizontal2.add(leftSide);
      horizontal2.add(initAddLanguageRightSide());

      vertical.add(wrapper);
      vertical.add(horizontal2);

      center.add(vertical);

      return center;
   }

   private JPanel initAddLanguageLeftSide()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 5);
      vertical.setLayout(verticalLayout);

      for (Translation e : Translation.values())
      {
         JLabel label = new JLabel(translator.translateTo(e,
               fromLanguage.getItemAt(fromLanguage.getSelectedIndex())));
         label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         label.setMinimumSize(new Dimension(400, 30));
         label.setMaximumSize(new Dimension(400, 30));
         vertical.add(label);
      }

      return vertical;
   }

   private Component initAddLanguageRightSide()
   {
      JPanel vertical = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(vertical, 5);
      vertical.setLayout(verticalLayout);

      for (Translation e : Translation.values())
      {
         JTextField textField = new JTextField();
         textField.setMinimumSize(new Dimension(900, 30));
         textField.setMaximumSize(new Dimension(900, 30));
         vertical.add(textField);
      }

      return vertical;
   }

   private void initController()
   {
      fromLanguage.addActionListener(event -> {
         leftSide = initAddLanguageLeftSide();
         validate();
         repaint();
      });

      toLanguage.addActionListener(event -> {
         
      });
   }
}
