package vokabeltrainer.common.main;

import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import vokabeltrainer.cmd.Mode;
import vokabeltrainer.common.CerebrummiPreferences;
import vokabeltrainer.resources.Blue;
import vokabeltrainer.resources.Buchstabenbilder;
import vokabeltrainer.resources.Fonts;
import vokabeltrainer.resources.Gruen;
import vokabeltrainer.resources.Images;
import vokabeltrainer.resources.LetterIcons;
import vokabeltrainer.resources.LetterIconsHandwritten;
import vokabeltrainer.resources.Sounds;
import vokabeltrainer.types.grammatical.Binjan;
import vokabeltrainer.types.grammatical.Gender;
import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;
import vokabeltrainer.types.grammatical.GrammaticalPerson;
import vokabeltrainer.types.grammatical.Numerus;
import vokabeltrainer.types.grammatical.VerbTimes;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public final class Main
{
   static void main(String[] args)
   { 
      Common common = new Common();
      Model model = new Model();
      Mode mode = Mode.LOCAL_ORIGINAL;
      
      initEnums(common);
      
      View view = initView(common, model, mode);

      SwingUtilities.invokeLater(() -> {
        view.startApp();
      });

      initPostView();
   }
   
   public static void initEnums(Common common)
   {
      Binjan.setTranslator(common.getTranslator());
      Gender.setTranslator(common.getTranslator());
      GrammaticalPerson.setTranslator(common.getTranslator());
      Numerus.setTranslator(common.getTranslator());
      VerbTimes.setTranslator(common.getTranslator());
      GrammaticalParentEnum.setTranslator(common.getTranslator());
      ExpressionKind.setTranslator(common.getTranslator());
   }

   private static View initView(Common common, Model model, Mode mode)
   {
      CerebrummiPreferences.read(mode);

      try
      {
         Fonts.read();
      }
      catch (FontFormatException | IOException e)
      {
         System.exit(1);
      }

      Fonts.define();

      try
      {
         Images.read();
         LetterIcons.readNikud();
         LetterIconsHandwritten.readNikud();
         Buchstabenbilder.read(common);
      }
      catch (IOException e1)
      {
         System.exit(1);
      }

      Sounds.read();
      
      View view = new View(common, mode);
      
      model.initDatabase(common, view);
      model.initImageData(common, view);
      model.initSoundData(common, view);
      
      view.getMainJPanel().initContent(common, model, view);
      view.getMainJPanel().initController(common, model, view);
      
      return view;
   }
   
   private static void initPostView()
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground()
         {
            try
            {
               Blue.read();
               Gruen.read();
            }
            catch (IOException e)
            {
               System.exit(1);
            }
            return null;
         }

      }.execute();
   }
}

