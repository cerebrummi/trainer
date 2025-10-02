package vokabeltrainer.panels.statistics;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vokabeltrainer.common.Common;
import vokabeltrainer.common.ImageData;
import vokabeltrainer.common.colors.StatisticsColors;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;

public class ListImageRow extends JPanel
{
   private static final long serialVersionUID = -5001282133186284698L;

   private JButton imageButton;
   private Expression expression;

   public ListImageRow(Expression expression, Direction language)
   {
      setLayout(new FlowLayout());
      setOpaque(true);
      setBackground(StatisticsColors.getSelectedBackground());
      setForeground(StatisticsColors.getTextForegroundInvers());

      imageButton = new JButton(
            Common.getTranslator().realisticTranslate(Translation.BILD));
      imageButton.setForeground(StatisticsColors.getTextForeground());
      this.expression = expression;

      if (ImageData.isImageForExpressionAvailable(expression.getUuid()))
      {
         add(imageButton);
      }
      if (Direction.OWN_TO_NEW.equals(language))
      {
         JLabel label = new JLabel(
               expression.getWordGermanForStatistics(Direction.OWN_TO_NEW));
         label.setForeground(StatisticsColors.getTextForeground());
         add(label);
      }
      else
      {
         JLabel label = new JLabel(
               expression.getWordGermanForStatistics(Direction.NEW_TO_OWN));
         label.setForeground(StatisticsColors.getTextForeground());
         add(label);
      }

      initController();
   }

   private void initController()
   {
      imageButton.addActionListener(_ -> {
         JOptionPane.showMessageDialog(imageButton, null, null,
               JOptionPane.PLAIN_MESSAGE, new ImageIcon(
                     ImageData.loadImageOriginal(expression.getUuid())));
      });
   }

}
