package vokabeltrainer.panels;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Data;
import vokabeltrainer.panels.success.LanguageTab;
import vokabeltrainer.panels.success.InformationTab;
import vokabeltrainer.panels.success.InformationTabDtoH;
import vokabeltrainer.panels.success.InformationTabHtoD;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.types.Language;

public class SuccessPanel extends BackgroundPanelTiled
{
   private static final long serialVersionUID = 8838302053896928354L;
   private JTabbedPane germanHebrewRegister;
   private JPanel infoCard;
   private LanguageTab languageDtoHcard;
   private LanguageTab languageHtoDcard;

   public SuccessPanel()
   {
      setLayout(new BullsEyeLayout(this));

      germanHebrewRegister = new JTabbedPane();
      germanHebrewRegister.setFont(Settings.getSecondaryToolBarButtonFont());
      germanHebrewRegister.setTabPlacement(JTabbedPane.LEFT);
      germanHebrewRegister.setMinimumSize(new Dimension(1254, 613));
      germanHebrewRegister.setMaximumSize(new Dimension(1500, 800));

      infoCard = new InformationTab();
      languageDtoHcard = new LanguageTab(new InformationTabDtoH(), Language.GERMAN);
      languageHtoDcard = new LanguageTab(new InformationTabHtoD(), Language.HEBREW);

      germanHebrewRegister.addTab("Karteikasten", infoCard);
      germanHebrewRegister.addTab("Deutsch >> Hebräisch", languageDtoHcard);
      germanHebrewRegister.addTab("Hebräisch >> Deutsch", languageHtoDcard);

      add(germanHebrewRegister);

      initController();
   }

   private void initController()
   {
      germanHebrewRegister.addChangeListener(event -> {
         if (germanHebrewRegister.getSelectedIndex() == 1)
         {
            Data.unselectAllExpressions();
            languageDtoHcard.loadBoxes();
         }
         else if (germanHebrewRegister.getSelectedIndex() == 2)
         {
            Data.unselectAllExpressions();
            languageHtoDcard.loadBoxes();
         }
      });

   }

   public void reset()
   {
      germanHebrewRegister.setSelectedIndex(0);
   }
}
