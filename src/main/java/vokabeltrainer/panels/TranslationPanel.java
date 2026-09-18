package vokabeltrainer.panels;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Main;
import vokabeltrainer.panels.translation.TranslationCode;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.resources.Buchstabenbilder;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class TranslationPanel extends JPanel
{
   private static final long serialVersionUID = 369293645105172512L;

   private JButton applyButton;
   private JComboBox<TranslationCode> chooseLanguage;

   TranslationPanel(App app, Common common)
   {
      setLayout(new BullsEyeLayout(this));

      add(initChooseLanguage(app));

      initController(app, common);
   }

   private Component initChooseLanguage(App app)
   {
      JPanel horizontal = new JPanel();
      TrainLayout horizontalLayout = new TrainLayout(horizontal, 15);
      horizontal.setLayout(horizontalLayout);

      JLabel appTranslation = new JLabel();

      chooseLanguage = new JComboBox<>(TranslationCode.valuesAvailable());
      chooseLanguage.setMinimumSize(new Dimension(300, 30));
      chooseLanguage.setMaximumSize(new Dimension(300, 50));

      applyButton = new JButton(
            new ImageIcon(app.appImages.getSelectDone()));

      horizontal.add(appTranslation);
      horizontal.add(chooseLanguage);
      horizontal.add(applyButton);

      return horizontal;
   }

   private void initController(App app, Common common)
   {
      applyButton.addActionListener(_ -> {
         TranslationCode choosen = chooseLanguage
               .getItemAt(chooseLanguage.getSelectedIndex());
         app.settings.setTranslationCode(choosen);
         common.setTranslator(new Translator());
         Main.initEnums(app, common);
         try
         {
            Buchstabenbilder.read(app, common);
         }
         catch (Exception e)
         {
            // nothing
         }
      });
   }
}
