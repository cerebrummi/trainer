package vokabeltrainer.panels;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.colors.SuccessColors;
import vokabeltrainer.common.main.Common;
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

   public SuccessPanel(Common common, View view)
   {
      translator = common.getTranslator();
      
      setLayout(new BullsEyeLayout(this));
      setOpaque(true);
      setBackground(SuccessColors.getPanelBackground());

      germanHebrewRegister = new JTabbedPane();
      germanHebrewRegister.setForeground(SuccessColors.getTextForeground());
      germanHebrewRegister
            .setFont(ApplicationFonts.secondaryToolbarButtonFont);
      germanHebrewRegister.setTabPlacement(JTabbedPane.LEFT);
      germanHebrewRegister.setMinimumSize(new Dimension(1254, 613));
      germanHebrewRegister.setMaximumSize(new Dimension(1500, 800));

      infoCard = new InformationTab(common);
      languageDtoHcard = new LanguageTab(common, new InformationTabDtoH(common),
            Direction.OWN_TO_NEW);
      languageHtoDcard = new LanguageTab(common, new InformationTabHtoD(common),
            Direction.NEW_TO_OWN);

      germanHebrewRegister.addTab(
            translator.realisticTranslate(Translation.KARTEIKASTEN), infoCard);
      germanHebrewRegister.addTab(
            translator.realisticTranslate(Translation.DEUTSCH) + " >> "
                  + translator.realisticTranslate(Translation.HEBRAEISCH),
            languageDtoHcard);
      germanHebrewRegister.addTab(
            translator.realisticTranslate(Translation.HEBRAEISCH) + " >> "
                  + translator.realisticTranslate(Translation.DEUTSCH),
            languageHtoDcard);

      add(germanHebrewRegister);

      initController(common, view);
   }

   private void initController(Common common, View view)
   {
      germanHebrewRegister.addChangeListener(_ -> {
         if (germanHebrewRegister.getSelectedIndex() == 1)
         {
            languageDtoHcard.loadBoxes(common, view);
         }
         else if (germanHebrewRegister.getSelectedIndex() == 2)
         {
            languageHtoDcard.loadBoxes(common, view);
         }
      });

   }

   public void reset()
   {
      germanHebrewRegister.setSelectedIndex(0);
   }
}
