package vokabeltrainer.panels;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.common.Main;
import vokabeltrainer.tonionlayout.BullsEyeLayout;

public class LanguagePanel extends JPanel
{
   private static final long serialVersionUID = 369293645105172512L;

   LanguagePanel()
   {
      setLayout(new BullsEyeLayout(this));
      
      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.setOpaque(false);
      tabbedPane.setFont(Main.getGermanFont(16F));
     
      tabbedPane.addTab("", new ImageIcon(ApplicationImages.getLanguages()), initAddLanguage());
      
      tabbedPane.addTab("", new ImageIcon(ApplicationImages.getNewWordSmall()), initChooseLanguage());

      add(tabbedPane);

      initController();
   }

   private Component initChooseLanguage()
   {
      // TODO Auto-generated method stub
      return null;
   }

   private Component initAddLanguage()
   {
      // TODO Auto-generated method stub
      return null;
   }

   private void initController()
   {
      // TODO Auto-generated method stub
      
   }
}
