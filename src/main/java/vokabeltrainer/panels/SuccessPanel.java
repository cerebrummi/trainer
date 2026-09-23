package vokabeltrainer.panels;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.success.LanguageTab;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.panels.success.InformationTab;
import vokabeltrainer.panels.success.InformationTabDtoH;
import vokabeltrainer.panels.success.InformationTabHtoD;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.types.Direction;

public class SuccessPanel extends JPanel
{
   private static final long serialVersionUID = 8838302053896928354L;
   private JTabbedPane germanHebrewRegister;
   private JPanel infoCard;
   private LanguageTab languageDtoHcard;
   private LanguageTab languageHtoDcard;
   private Translator translator;

   public SuccessPanel(App app, Common common, Model model, View view)
   {
      translator = common.getTranslator();
      
      setLayout(new BullsEyeLayout(this));
      setOpaque(true);
      setBackground(app.appColors.getBackground());

      germanHebrewRegister = new JTabbedPane();
      germanHebrewRegister.setForeground(app.appColors.success.getTextForeground());
      germanHebrewRegister
            .setFont(app.appFonts.secondaryToolbarButtonFont);
      germanHebrewRegister.setTabPlacement(JTabbedPane.LEFT);
      germanHebrewRegister.setMinimumSize(new Dimension(1254, 613));
      germanHebrewRegister.setMaximumSize(new Dimension(1500, 800));

      infoCard = new InformationTab(app, common);
      languageDtoHcard = new LanguageTab(app, common, new InformationTabDtoH(app, common),
            Direction.OWN_TO_NEW);
      languageHtoDcard = new LanguageTab(app, common, new InformationTabHtoD(app, common),
            Direction.NEW_TO_OWN);

      germanHebrewRegister.addTab(
            translator.realisticTranslate(app, Translation.KARTEIKASTEN), infoCard);
      germanHebrewRegister.addTab(
            translator.realisticTranslate(app, Translation.DEUTSCH) + " >> "
                  + translator.realisticTranslate(app, Translation.HEBRAEISCH),
            languageDtoHcard);
      germanHebrewRegister.addTab(
            translator.realisticTranslate(app, Translation.HEBRAEISCH) + " >> "
                  + translator.realisticTranslate(app, Translation.DEUTSCH),
            languageHtoDcard);

      add(germanHebrewRegister);

      initController(app, common, model, view);
   }

   private void initController(App app, Common common, Model model, View view)
   {
      germanHebrewRegister.addChangeListener(_ -> {
         if (germanHebrewRegister.getSelectedIndex() == 1)
         {
            languageDtoHcard.loadBoxes(app, common, model, view);
         }
         else if (germanHebrewRegister.getSelectedIndex() == 2)
         {
            languageHtoDcard.loadBoxes(app, common, model, view);
         }
      });

   }

   public void reset()
   {
      germanHebrewRegister.setSelectedIndex(0);
   }
}
