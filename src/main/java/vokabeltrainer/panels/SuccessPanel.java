package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.panels.success.GermanToHebrewTab;
import vokabeltrainer.panels.success.HebrewToGermanTab;
import vokabeltrainer.panels.success.InformationTab;
import vokabeltrainer.tonionlayout.BullsEyeLayout;

public class SuccessPanel extends BackgroundPanelTiled
{
   private static final long serialVersionUID = 8838302053896928354L;
   private JTabbedPane germanHebrewRegister;
   private JPanel infoCard;
   private GermanToHebrewTab languageDtoHcard;
   private HebrewToGermanTab languageHtoDcard;

   public SuccessPanel()
   {
      setLayout(new BorderLayout());

      JPanel eyePanel = new JPanel();
      eyePanel.setLayout(new BullsEyeLayout(eyePanel));

      germanHebrewRegister = new JTabbedPane();
      germanHebrewRegister.setFont(Settings.getSecondaryToolBarButtonFont());
      germanHebrewRegister.setTabPlacement(JTabbedPane.LEFT);
      germanHebrewRegister.setMinimumSize(new Dimension(1254, 613));
      germanHebrewRegister.setMaximumSize(new Dimension(1254, 613));

      infoCard = new InformationTab();
      languageDtoHcard = new GermanToHebrewTab();
      languageHtoDcard = new HebrewToGermanTab();

      germanHebrewRegister.addTab("Start", infoCard);
      germanHebrewRegister.addTab("Deutsch >> Hebräisch", languageDtoHcard);
      germanHebrewRegister.addTab("Hebräisch >> Deutsch", languageHtoDcard);

      eyePanel.add(germanHebrewRegister);

      add(eyePanel, BorderLayout.CENTER);

      initController();
   }

   private void initController()
   {
      germanHebrewRegister.addChangeListener(event -> {
         if (germanHebrewRegister.getSelectedIndex() == 1)
         {
            languageDtoHcard.loadBoxes();
         }
         else if (germanHebrewRegister.getSelectedIndex() == 2)
         {
            languageHtoDcard.loadBoxes();
         }
      });

   }

   public void reset()
   {
      germanHebrewRegister.setSelectedIndex(0);
   }
}
