package vokabeltrainer.panels.trainer.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.FieldOfTraining;
import vokabeltrainer.types.LanguageDirection;

public class StartTrainingView extends JDialog
{
   private static final long serialVersionUID = -2204963503225031512L;

   private JPanel layout;
   private JTabbedPane tabbedPane;
   private LanguageDirection languageDirection;
   private FieldOfTraining fieldOfTraining;
   private List<Expression> newExpressions;
   private List<Expression> oldExpressions;
   private boolean training;
   private Translator translator;
   private Set<String> databaseNames;

   public StartTrainingView(App app, Common common, Model model, View view, StartTrainingControllerConnector connector)
   {
      super(view.getjFrame(), app.settings.getWindowTitle(),
            Dialog.ModalityType.APPLICATION_MODAL);
      translator = common.getTranslator();
      training = false;
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setSize(Math.min(screenSize.width - 60, 1500),
            Math.min(screenSize.height - 60, 700));
      layout = new JPanel();
      layout.setBackground(app.appColors.trainer.getPanelBackground());
      layout.setBorder(BorderFactory
            .createLineBorder(app.appColors.trainer.getPanelBackground(), 15, false));
      layout.setLayout(new BorderLayout());
      getContentPane().add(layout);

      tabbedPane = new JTabbedPane();
      tabbedPane.setFont(app.appFonts.toolbarButtonFont);
      layout.add(tabbedPane, BorderLayout.CENTER);

      tabbedPane.addTab(translator.realisticTranslate(Translation.GEBIET),
            new ImageIcon(app.appImages.getArrow()),
            new FieldOfTrainingTab(app, common, model, this));
   }

   public void initTraining()
   {
      training = true;
      this.setVisible(false);
   }

   public void cancelTrainingStart()
   {
      training = false;
      this.setVisible(false);
   }

   public LanguageDirection getLanguageDirection()
   {
      return languageDirection;
   }

   public void setLanguageDirection(LanguageDirection languageDirection)
   {
      this.languageDirection = languageDirection;
   }

   public FieldOfTraining getFieldOfTraining()
   {
      return fieldOfTraining;
   }

   public void setNewExpressions(List<Expression> newExpressions)
   {
      this.newExpressions = newExpressions;
   }

   public void setFieldOfTraining(FieldOfTraining fieldOfTraining)
   {
      this.fieldOfTraining = fieldOfTraining;
   }

   public JTabbedPane getTabbedPane()
   {
      return tabbedPane;
   }

   public List<Expression> getNewExpressions()
   {
      return newExpressions;
   }

   public boolean isTraining()
   {
      return training;
   }

   public List<Expression> getOldExpressions()
   {
      return oldExpressions;
   }

   public void setOldExpressions(List<Expression> oldExpressions)
   {
      this.oldExpressions = oldExpressions;
   }

   public Set<String> getDatabaseNames()
   {
      return this.databaseNames;
   }

   public void setDatabaseNames(Set<String> databaseNames)
   {
      this.databaseNames = databaseNames;
   }

}
