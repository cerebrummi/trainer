package vokabeltrainer.panels.statistics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import vokabeltrainer.Settings;
import vokabeltrainer.types.Expression;

public class StatisticsTableRow
{
   private List<Expression> expressionsDtoH;
   private List<Expression> expressionsHtoD;
   private LocalDate date;
   private JButton takeOutButton;
   private DateTimeFormatter dateFormatter = DateTimeFormatter
         .ofPattern("EEEE dd.MM.yyyy", Locale.GERMANY);
   int row;
   StatisticsTableModel model;

   public StatisticsTableRow(int row, LocalDate date,
         List<Expression> expressionsDtoH, List<Expression> expressionsHtoD,
         StatisticsTableModel model)
   {
      this.row = row;
      this.date = date;
      this.expressionsDtoH = expressionsDtoH;
      this.expressionsHtoD = expressionsHtoD;
      this.model = model;
      takeOutButton = new JButton("herausnehmen");
      takeOutButton.setFont(Settings.getButtonFont());
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
