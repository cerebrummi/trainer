package vokabeltrainer.panels.trainer.dialog.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import vokabeltrainer.common.colors.TrainerColors;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.FieldOfTraining;
import vokabeltrainer.types.LanguageDirection;
import vokabeltrainer.types.Repetition;
import vokabeltrainer.types.TrainingStatus;

public class TrainingTable extends JTable
{
   private static final long serialVersionUID = -1180739124368536646L;

   public TrainingTable(Common common, TrainingTableModel model)
   {
      super(model, new TrainingTableColumnModel(common));

      putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
      setOpaque(false);
      setRowHeight(50);
      setShowHorizontalLines(false);
      setBackground(TrainerColors.getPanelBackgroundDark());
      this.setRowSelectionAllowed(true);
      this.setColumnSelectionAllowed(true);
      this.setCellSelectionEnabled(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }

   public List<Expression> findNewExpressions(
         LanguageDirection languageDirection, FieldOfTraining fieldOfTraining)
   {
      TrainingTableModel model = (TrainingTableModel) getModel();

      Set<Expression> resultSet = new HashSet<>();
      for (TrainingTableRow[] row : model.getData())
      {
         if (row[0].getAmountOfNewWords() > 0)
         {
            resultSet.addAll(findRandomWords(row[0].getExpressionListNewWords(),
                  row[0].getAmountOfNewWords()));
         }
      }
      if (FieldOfTraining.AREA_SELECTED_TEMPORARY == fieldOfTraining)
      {
         return new ArrayList<>(resultSet);
      }
      return initTrainingStatus(resultSet, languageDirection);
   }

   public List<Expression> findOldToBeRepeatedExpressions()
   {
      TrainingTableModel model = (TrainingTableModel) getModel();

      Set<Expression> resultSet = new HashSet<>();
      for (TrainingTableRow[] row : model.getData())
      {
         if (row[0].getExpressionListOldWords() != null)
         {
            resultSet.addAll(row[0].getExpressionListOldWords());
         }
      }
      return new ArrayList<Expression>(resultSet);
   }

   private List<Expression> initTrainingStatus(Set<Expression> resultSet,
         LanguageDirection languageDirection)
   {
      List<Expression> list = new ArrayList<>(resultSet.size());
      switch (languageDirection)
      {
      case OWN_TO_SWEDISH:
      case OWN_TO_HEBREW:
      case OWN_TO_GERMAN:
         for (Expression expression : resultSet)
         {
            if (!expression.getTrainingStatusDToLL().isTrainingStarted())
            {
               expression.setTrainingStatusDToLL(
                     new TrainingStatus(Repetition.NOW));
            }

            list.add(expression);
         }
         break;
      case SWEDISH_TO_OWN:
      case HEBREW_TO_OWN:
      case GERMAN_TO_OWN:
         for (Expression expression : resultSet)
         {
            if (!expression.getTrainingStatusLLToD().isTrainingStarted())
            {
               expression.setTrainingStatusLLToD(
                     new TrainingStatus(Repetition.NOW));
            }
            list.add(expression);
         }
         break;
      }
      return list;
   }

   private List<Expression> findRandomWords(Collection<Expression> listSelected,
         int amountOfNewWords)
   {
      List<Expression> list = new ArrayList<>(listSelected);
      Collections.shuffle(list);
      List<Expression> selected = new ArrayList<>();
      for (int i = 0; i < amountOfNewWords; i++)
      {
         selected.add(list.get(i));
      }
      return selected;
   }
}
