package vokabeltrainer.panels.statistics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import vokabeltrainer.ExpressionComparator;
import vokabeltrainer.Settings;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class StatisticsTableRow
{
   private List<String> germanDtoH;
   private List<String> germanHtoD;
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
      
      Collections.sort(expressionsDtoH, new ExpressionComparator(Language.GERMAN));
      germanDtoH = new ArrayList<>(expressionsDtoH.size());
      for(Expression expression : expressionsDtoH)
      {
         germanDtoH.add(expression.getWordGermanForStatistics());
      }
      
      Collections.sort(expressionsHtoD, new ExpressionComparator(Language.GERMAN));
      germanHtoD = new ArrayList<>(expressionsHtoD.size());
      for(Expression expression : expressionsHtoD)
      {
         germanHtoD.add(expression.getWordGermanForStatistics());
      }
      
      this.model = model;
      takeOutButton = new JButton("herausnehmen");
      takeOutButton.setFont(Settings.getButtonFont());
   }

   public int getExpressionsDtoHSize()
   {
      return germanDtoH.size();
   }

   public int getExpressionsHtoDSize()
   {
      return germanHtoD.size();
   }

   public String getDate()
   {
      return date.format(dateFormatter);
   }

   public JButton getTakeOutButton()
   {
      return takeOutButton;
   }

   public JList<String> getJListHtoD()
   {
      DefaultListModel<String> listModel = new DefaultListModel<>();
      for(String word : germanHtoD)
      {
         listModel.addElement(word);
      }
      JList<String> list = new JList<>(listModel);
      list.setFont(Settings.getButtonFont());
      return list;
   }

   public JList<String> getJListDtoH()
   {
      DefaultListModel<String> listModel = new DefaultListModel<>();
      for(String word : germanDtoH)
      {
         listModel.addElement(word);
      }
      JList<String> list = new JList<>(listModel);
      list.setFont(Settings.getButtonFont());
      return list;
   }
}
