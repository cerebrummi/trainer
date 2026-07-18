package vokabeltrainer.common.main;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import vokabeltrainer.cmd.Mode;
import vokabeltrainer.resources.Blue;
import vokabeltrainer.resources.Gruen;
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
      Mode mode = Mode.LOCAL_ORIGINAL;
      App app = new App(mode);
      
      Common common = new Common();
           
      initEnums(common);
      
      View view = initView(app, common, mode);

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

   private static View initView(App app, Common common, Mode mode)
   {
      View view = new View(app, common, mode);
      
      Model model = new Model(app.settings, common, view);
      
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

