package vokabeltrainer.panels.statistics;

import java.awt.Component;
import java.awt.LayoutManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.swing.JPanel;

import vokabeltrainer.ExpressionComparator;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.TranslationCode;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.SortingType;

public class StatisticsTableRow
{
   private LocalDate date;
   private Translator translator = Common.getTranslator();
   private DateTimeFormatter dateFormatter;
   int row;
   StatisticsTableModel model;
private List<Expression> expressionsHtoD;
private List<Expression> expressionsDtoH;

   public StatisticsTableRow(int row, LocalDate date,
         List<Expression> expressionsDtoH, List<Expression> expressionsHtoD,
         StatisticsTableModel model)
   {
      this.row = row;
      this.date = date;
      this.expressionsDtoH = expressionsDtoH;
      this.expressionsHtoD = expressionsHtoD;
      if (TranslationCode.de_original == Settings.getTranslationCode())
      {
         dateFormatter = DateTimeFormatter
               .ofPattern("EEEE "
                     + translator.realisticTranslate(Translation._DATE), Locale.GERMANY);
      }
      else if (TranslationCode.en == Settings.getTranslationCode())
      {
         dateFormatter = DateTimeFormatter
               .ofPattern("EEEE "
                     + translator.realisticTranslate(Translation._DATE), Locale.US);
      }
      
      Collections.sort(this.expressionsDtoH, new ExpressionComparator(SortingType.DATE));
      
      Collections.sort(this.expressionsHtoD, new ExpressionComparator(SortingType.DATE));
      
      this.model = model;
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
   
   public LocalDate getLocalDate()
   {
      return date;
   }

   public Component getJListHtoD()
   {
	  JPanel list = new JPanel();
	  LayoutManager layout = new TotemLayout(list);
	  list.setLayout(layout);
	  
      for(Expression expression : expressionsHtoD)
      {
        list.add(new ListImageRow(expression, Direction.NEW_TO_OWN));
      }

      return list;
   }

   public Component getJListDtoH()
   {
	   JPanel list = new JPanel();
		  LayoutManager layout = new TotemLayout(list);
		  list.setLayout(layout);
		  
	      for(Expression expression : expressionsDtoH)
	      {
	        list.add(new ListImageRow(expression, Direction.OWN_TO_NEW));
	      }

	   return list;
   }
}
