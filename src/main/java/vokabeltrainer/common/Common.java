package vokabeltrainer.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.swing.JFrame;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import vokabeltrainer.panels.MainView;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.list.editor.LanguageExpressionEditorView;

public final class Common
{
   private static MainView mainJPanel;
   private static JFrame jFrame;
   private static LanguageExpressionEditorView languageExpressionEditor;

   private static boolean setMainJPanelOnlyOnce = false;
   private static boolean setJFrameOnlyOnce = false;
   private static Translator translator = new Translator();
   private static Settings settings;
   
   private static NimbusLookAndFeel nimbus;
   

   private Common()
   {

   }
   
   public static MainView getMainJPanel()
   {
      return mainJPanel;
   }

   static void setMainJPanel(MainView mainJPanel)
   {
      if (!setMainJPanelOnlyOnce)
      {
         Common.mainJPanel = mainJPanel;
      }
   }

   public static JFrame getjFrame()
   {
      return jFrame;
   }

   static void setjFrame(JFrame jFrame)
   {
      if (!setJFrameOnlyOnce)
      {
         Common.jFrame = jFrame;
      }
   }
   
   public static LanguageExpressionEditorView getLanguageExpressionEditor()
   {
      return languageExpressionEditor;
   }

   public static void setLanguageExpressionEditor(LanguageExpressionEditorView languageExpressionEditor)
   {
      Common.languageExpressionEditor = languageExpressionEditor;
   }

   public static Translator getTranslator()
   {
      return translator;
   }

   public static void setTranslator(Translator translator)
   {
      Common.translator = translator;
   }

   public static Settings getSettings()
   {
      return settings;
   }

   public static NimbusLookAndFeel getNimbus()
   {
      return nimbus;
   }

   public static void setNimbus(NimbusLookAndFeel nimbus)
   {
      Common.nimbus = nimbus;
   }

   public static boolean isSchabbat()
   {
      ZonedDateTime now = ZonedDateTime.now();
      DayOfWeek day = now.getDayOfWeek();
      int hour = now.getHour();
      if(day.equals(DayOfWeek.FRIDAY) && hour > 18)
      {
         return true;
      }
      else if (day.equals(DayOfWeek.SATURDAY) && hour < 18)
      {
         return true;
      }
      
      return false;
   }
   
   public static boolean isSchabbatPossible(LocalDate date)
   {
      DayOfWeek day = date.getDayOfWeek();
      if(day.equals(DayOfWeek.FRIDAY) || day.equals(DayOfWeek.SATURDAY))
      {
         return true;
      }
      
      return false;
   }
}
