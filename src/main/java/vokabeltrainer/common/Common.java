package vokabeltrainer.common;

import javax.swing.JFrame;
import vokabeltrainer.panels.MainView;
import vokabeltrainer.panels.translation.Translator;

public final class Common
{
   private static MainView mainJPanel;
   private static JFrame jFrame;
   private static boolean setMainJPanelOnlyOnce = false;
   private static boolean setJFrameOnlyOnce = false;
   private static Translator translator = new Translator();

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

   public static Translator getTranslator()
   {
      return translator;
   }

   public static void setTranslator(Translator translator)
   {
      Common.translator = translator;
   }
}
