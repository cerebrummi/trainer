package vokabeltrainer.panels.statistics;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vokabeltrainer.common.colors.StatisticsColors;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;

public class ListImageRow extends JPanel
{
   private static final long serialVersionUID = -5001282133186284698L;

   public ListImageRow(Common common, Expression expression, Direction language)
   {
      setLayout(new FlowLayout());
      setOpaque(true);
      setBackground(StatisticsColors.getSelectedBackground());
      setForeground(StatisticsColors.getTextForegroundInvers());

      if (Direction.OWN_TO_NEW.equals(language))
      {
         JLabel label = new JLabel(
               expression.getWordGermanForStatistics(common, Direction.OWN_TO_NEW));
         label.setForeground(StatisticsColors.getTextForeground());
         add(label);
      }
      else
      {
         JLabel label = new JLabel(
               expression.getWordGermanForStatistics(common, Direction.NEW_TO_OWN));
         label.setForeground(StatisticsColors.getTextForeground());
         add(label);
      }
   }
}
