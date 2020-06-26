package vokabeltrainer.panels.trainer.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.Command;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.Common;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class StartTrainingView extends JDialog
{
   private static final long serialVersionUID = -2204963503225031512L;

   private JPanel layout;
   private JTabbedPane tabbedPane;
   private Language languageDirection;
   private Command fieldOfTraining;
   private List<Expression> newExpressions;
   private boolean training;
   private Set<Expression> oldExpressionsHToD;
   private Set<Expression> oldExpressionsDToH;

   public StartTrainingView(StartTrainingControllerConnector connector)
   {
      super(Common.getjFrame(), "Cerebrummi©",
            Dialog.ModalityType.APPLICATION_MODAL);

      training = false;
      oldExpressionsDToH = connector.getOldExpressionsDToH();
      oldExpressionsHToD = connector.getOldExpressionsHToD();

      setSize(982, 480);
      layout = new JPanel();
      layout.setBorder(BorderFactory.createLineBorder(new Color(169, 136, 103),
            15, false));
      layout.setLayout(new BorderLayout());
      getContentPane().add(layout);

      tabbedPane = new JTabbedPane();
      tabbedPane.setFont(Settings.getToolBarButtonFont());
      layout.add(tabbedPane, BorderLayout.CENTER);

      tabbedPane.addTab("GEBIET", new ImageIcon(ApplicationImages.getArrow()),
            new FieldOfTrainingTab(this));
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

   public void showNoWordsForTraining()
   {
      training = false;
      this.setVisible(false);
      
      JOptionPane.showMessageDialog(Common.getjFrame(), "", "Cerebrummi©",
            JOptionPane.PLAIN_MESSAGE,
            new ImageIcon(TextImage.make("Keine Worte zum Üben ausgewählt.",
                  "Bitte neue Worte auswählen,",
                  "um das Training zu starten.")));
   }

   public Language getLanguageDirection()
   {
      return languageDirection;
   }

   public void setLanguageDirection(Language languageDirection)
   {
      this.languageDirection = languageDirection;
   }

   public Command getFieldOfTraining()
   {
      return fieldOfTraining;
   }

   public void setNewExpressions(List<Expression> newExpressions)
   {
      this.newExpressions = newExpressions;
   }

   public void setFieldOfTraining(Command fieldOfTraining)
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

   public Set<Expression> getOldExpressionsHToD()
   {
      return oldExpressionsHToD;
   }

   public void setOldExpressionsHToD(Set<Expression> oldExpressionsHToD)
   {
      this.oldExpressionsHToD = oldExpressionsHToD;
   }

   public Set<Expression> getOldExpressionsDToH()
   {
      return oldExpressionsDToH;
   }

   public void setOldExpressionsDToH(Set<Expression> oldExpressionsDToH)
   {
      this.oldExpressionsDToH = oldExpressionsDToH;
   }

}
