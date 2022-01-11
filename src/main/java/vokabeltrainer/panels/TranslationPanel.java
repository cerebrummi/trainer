package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vokabeltrainer.ApplicationColors;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Main;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.TranslationCode;
import vokabeltrainer.panels.translation.TranslationCodeWrapper;
import vokabeltrainer.panels.translation.TranslationController;
import vokabeltrainer.panels.translation.TranslationField;
import vokabeltrainer.panels.translation.TranslationLanguage;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class TranslationPanel extends JPanel
{
   private static final long serialVersionUID = 369293645105172512L;
   private JComboBox<TranslationCodeWrapper> fromLanguage;
   private JComboBox<TranslationCodeWrapper> toLanguage;
   private Component nameField;
   private JPanel changePanel;
   private JPanel horizontalTop;
   private TranslationCode currentCode;
   private JButton goButton;
   private JButton saveButton;
   private List<TranslationField> fields;
   private JPanel verticalRightSide;
   private TranslationController controller = new TranslationController();
   private JPanel verticalLeftSide;

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
      JPanel center = new JPanel();
      BullsEyeLayout centerLayout = new BullsEyeLayout(center);
      center.setLayout(centerLayout);
      
      JPanel horizontal = new JPanel();
      TrainLayout horizontalLayout = new TrainLayout(horizontal, 15);
      horizontal.setLayout(horizontalLayout);
      
      JLabel appTranslation = new JLabel();
      
      center.add(horizontal);
      
      return center;
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

      horizontalTop = new JPanel();
      TrainLayout horizontal1Layout = new TrainLayout(horizontalTop, 15);
      horizontalTop.setLayout(horizontal1Layout);

      fromLanguage = new JComboBox<>(TranslationCode.valuesAvailable());
      fromLanguage.setMinimumSize(new Dimension(300, 30));
      currentCode = TranslationCode.none;
      
      JLabel label = new JLabel(">>>");
      label.setFont(Settings.getToolBarButtonFont());
      
      toLanguage = new JComboBox<>(TranslationCode.valuesNoOriginal());
      toLanguage.setMinimumSize(new Dimension(400, 30));

      changePanel = new JPanel(new BorderLayout());
      changePanel.setMinimumSize(new Dimension(400, 30));
      initNameBox("");
      changePanel.add(nameField);

      goButton = new JButton(new ImageIcon(ApplicationImages.getSelectDone()));
      saveButton = new JButton(new ImageIcon(ApplicationImages.getSaveWord()));

      JPanel horizontal2 = new JPanel();
      TrainLayout horizontal2Layout = new TrainLayout(horizontal2, 15);
      horizontal2.setLayout(horizontal2Layout);

      wrapper.add(horizontalTop);

      horizontalTop.add(fromLanguage);
      horizontalTop.add(label);
      horizontalTop.add(toLanguage);
      horizontalTop.add(changePanel);
      horizontalTop.add(goButton);
      horizontalTop.add(saveButton);

      horizontal2.add(initAddLanguageLeftSide());
      horizontal2.add(initAddLanguageRightSide());

      vertical.add(wrapper);
      
      vertical.add(horizontal2);

      center.add(vertical);

      return center;
   }

   private void initNameBox(String name)
   {
      changePanel.removeAll();
      nameField = new JLabel(name);
      nameField.setBackground(ApplicationColors.getLightGold());
      nameField.setMinimumSize(new Dimension(400, 30));
      changePanel.add(nameField);
   }

   @SuppressWarnings("unchecked")
   private void initNameIndividualBoxLtR()
   {
      changePanel.removeAll();
      nameField = new JComboBox<TranslationLanguage>(
            TranslationCode.anyLanguagesLeftToRight());
      nameField.setBackground(ApplicationColors.getLightYellow());
      nameField.setMinimumSize(new Dimension(400, 30));
      nameField.setPreferredSize(new Dimension(400, 30));
      ((JComboBox<String>) nameField).setEditable(true);
      nameField.setEnabled(true);
      changePanel.add(nameField);
   }

   @SuppressWarnings("unchecked")
   private void initNameIndividualBoxRtL()
   {
      changePanel.removeAll();
      nameField = new JComboBox<TranslationLanguage>(
            TranslationCode.anyLanguagesRightToLeft());
      nameField.setBackground(ApplicationColors.getLightYellow());
      nameField.setMinimumSize(new Dimension(400, 30));
      ((JComboBox<String>) nameField).setEditable(true);
      nameField.setEnabled(true);
      changePanel.add(nameField);
   }

   private Component initAddLanguageLeftSide()
   {
      verticalLeftSide = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(verticalLeftSide, 5);
      verticalLeftSide.setLayout(verticalLayout);

      return verticalLeftSide;
   }

   private Component initAddLanguageRightSide()
   {
      verticalRightSide = new JPanel();
      TotemLayout verticalLayout = new TotemLayout(verticalRightSide, 5);
      verticalRightSide.setLayout(verticalLayout);

      return verticalRightSide;
   }

   @SuppressWarnings("unchecked")
   private void initController()
   {
      fromLanguage.addActionListener(event -> {
         verticalLeftSide.removeAll();
         TranslationCodeWrapper fromCodeWrapper = fromLanguage.getItemAt(fromLanguage.getSelectedIndex());
         Translator translator = new Translator(fromCodeWrapper);
         for(Translation translation: Translation.values())
         {
            JLabel label = new JLabel(translator.realisticTranslate(translation));
            label.setMinimumSize(new Dimension(400,30));
            label.setMaximumSize(new Dimension(400,30));
            label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            verticalLeftSide.add(label);
         }
         
         Common.getjFrame().validate();
         Common.getjFrame().repaint();
      });

      toLanguage.addActionListener(event -> {
         currentCode = toLanguage.getItemAt(toLanguage.getSelectedIndex()).getCode();

         if (TranslationCode.ANY_ltr_ == currentCode)
         {
            initNameIndividualBoxLtR();
            ((JComboBox<TranslationLanguage>) nameField).setSelectedIndex(0);
         }
         else if (TranslationCode.ANY_rtl_ == currentCode)
         {
            initNameIndividualBoxRtL();
            ((JComboBox<TranslationLanguage>) nameField).setSelectedIndex(0);
         }
         else
         {
            initNameBox(currentCode.getName());
         }
         Common.getjFrame().validate();
         Common.getjFrame().repaint();
      });

      goButton.addActionListener(event -> {

         if (TranslationCode.none == currentCode)
         {
            return;
         }

         fields = new ArrayList<>();
         
         for (Translation translation: Translation.values())
         {
            TranslationField translationField = new TranslationField(translation);
            translationField.setComponentOrientation(currentCode.getOrientation());
            fields.add(translationField);
         }
         
         TranslationCodeWrapper translationWrapper = new TranslationCodeWrapper(currentCode);
         Translator translator = new Translator(translationWrapper);
         
         String name = currentCode.getName();
         UUID uuid = null;

         if (TranslationCode.ANY_ltr_ == currentCode
               || TranslationCode.ANY_rtl_ == currentCode)
         {
            int selectedIndex = ((JComboBox<TranslationLanguage>) nameField)
                  .getSelectedIndex();
            TranslationLanguage translationLanguage = ((JComboBox<TranslationLanguage>) nameField)
                  .getItemAt(selectedIndex);

            if (translationLanguage == null)
            {
               name = (String) ((JComboBox<Object>) nameField)
                     .getSelectedItem();
               name = name.strip();
               translationWrapper.setAnyName(name);
            }
            else if (translationLanguage.getText().isBlank())
            {
               return;
            }
            else
            {
               name = translationLanguage.getText();
               uuid = translationLanguage.getUuid();
               translationWrapper.setAnyName(name);
               translationWrapper.setUuid(uuid);
            }
         }

         verticalRightSide.removeAll();
         for (TranslationField field : fields)
         {
            verticalRightSide.add(field);
            field.setCode(currentCode);
            field.setText(translator.realisticTranslate(field.getTranslation()));
            field.setUuid(uuid);
            field.setName(name);
         }
         Common.getjFrame().validate();
         Common.getjFrame().repaint();
      });

      saveButton.addActionListener(event -> {
         if (TranslationCode.none == currentCode)
         {
            return;
         }
         if (TranslationCode.ANY_ltr_ == currentCode
               || TranslationCode.ANY_rtl_ == currentCode)
         {
            String name = (String) ((JComboBox<Object>) nameField)
                  .getSelectedItem();
            if (name.isBlank())
            {
               return;
            }
         }

         controller.saveTranslations(fields);
      });
   }
}
