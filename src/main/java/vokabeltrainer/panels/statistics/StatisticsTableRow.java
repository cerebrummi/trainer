package vokabeltrainer.panels.statistics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.SaveTraining;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.TrainingStatus;

public class StatisticsTableRow
{
   private List<Expression> expressionsDtoH;
   private List<Expression> expressionsHtoD;
   private LocalDate date;
   private JButton takeOutButton;
   private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("cc dd.MM.yyyy");

   public StatisticsTableRow(int row, LocalDate date, List<Expression> expressionsDtoH,
         List<Expression> expressionsHtoD, StatisticsTableModel<StatisticsTableRow> model)
   {
      this.date = date;
      this.expressionsDtoH = expressionsDtoH;
      this.expressionsHtoD = expressionsHtoD;
      takeOutButton = new JButton("herausnehmen");
      takeOutButton.setFont(Settings.getButtonFont());
      takeOutButton.addActionListener(event -> {
         if (JOptionPane.showConfirmDialog(Common.getjFrame(),
               "Sollen diese Wörter wirklich aus dem Training genommen werden?", "Frage",
               JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
         {
            for (Expression expressionDtoH : this.expressionsDtoH)
            {
               expressionDtoH.setTrainingStatusDToH(new TrainingStatus());
            }
            for (Expression expressionHtoD : this.expressionsHtoD)
            {
               expressionHtoD.setTrainingStatusHToD(new TrainingStatus());
            }
            SaveTraining saveTraining = new SaveTraining();
            saveTraining.save();
            model.deleteRow(row);
         }
      });
   }

   public int getExpressionsDtoHSize()
   {
      return expressionsDtoH.size();
   }

   public int getExpressionsHtoDSize()
   {
      return expressionsHtoD.size();
   }

   public String getDate()
   {
      return date.format(dateFormatter);
   }

   public JButton getTakeOutButton()
   {
      return takeOutButton;
   }
}
