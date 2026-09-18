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
   private static transient App app;
   
   static void main(String[] args)
   { 
      Mode mode = Mode.LOCAL_ORIGINAL;
      app = new App(mode);
      
      Common common = new Common();
           
      initEnums(app, common);
      
      View view = initView(app, common, mode);

      SwingUtilities.invokeLater(() -> {
        view.startApp();
      });

      initPostView(app);
   }
   
   public static void initEnums(App app, Common common)
   {
      Binjan.setTranslator(common.getTranslator());
      Binjan.setApp(app);
      Gender.setTranslator(common.getTranslator());
      Gender.setApp(app);
      GrammaticalPerson.setTranslator(common.getTranslator());
      GrammaticalPerson.setApp(app);
      Numerus.setTranslator(common.getTranslator());
      Numerus.setApp(app);
      VerbTimes.setTranslator(common.getTranslator());
      VerbTimes.setApp(app);
      GrammaticalParentEnum.setTranslator(common.getTranslator());
      GrammaticalParentEnum.setApp(app);
      ExpressionKind.setTranslator(common.getTranslator());
      ExpressionKind.setApp(app);
   }

   public static View initView(App app, Common common, Mode mode)
   {
      View view = new View(app, common, mode);
      
      Model model = new Model(app, common, view);
      
      view.getMainJPanel().initContent(app, common, model, view);
      view.getMainJPanel().initController(app, common, model, view);
      
      return view;
   }
   
   public static void initPostView(App app)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground()
         {
            try
            {
               Blue.read(app);
               Gruen.read(app);
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

