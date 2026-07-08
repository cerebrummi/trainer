package vokabeltrainer.panels;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Data;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.panels.sentences.table.ExpressionColumnModel;
import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.SortingType;
import vokabeltrainer.types.grammatical.expressionkind.ExpressionKind;

public class SentencesPanel extends JPanel implements TableConnector
{
   private static final long serialVersionUID = -3678970123371154151L;
   Integer levelOfDifficulty;
   private ExpressionTable table;
   private JPanel tablePanel;

   public SentencesPanel(Integer levelOfDifficulty)
   {
      this.levelOfDifficulty = levelOfDifficulty;
      setLayout(new BullsEyeLayout(this));
      add(initGui());

      initController();
   }

   private void initController()
   {

   }

   private Component initGui()
   {
      tablePanel = new JPanel();
      TotemLayout layout = new TotemLayout(tablePanel);
      
      tablePanel.setLayout(layout);
      tablePanel.setMinimumSize(new Dimension(1400, 800));
      tablePanel.setMaximumSize(new Dimension(1400, 800));
      tablePanel.setBackground(ApplicationColors.darkSilverBlue);

      ExpressionTableModel tableModel = Data.findTranslations(null, ExpressionKind.TEXT,
            null, null, null,
            SortingType.ALPHABET, levelOfDifficulty, Direction.NEW_TO_OWN, null);
      tablePanel.removeAll();
      table = new ExpressionTable(tableModel,
            Direction.NEW_TO_OWN, this, true, new ExpressionColumnModel());

      tablePanel.add(new JScrollPane(table));
      return tablePanel;
   }

   @Override
   public void save()
   {
      ExpressionTableModel tableModel = Data.findTranslations(null, ExpressionKind.TEXT,
            null, null, null,
            SortingType.ALPHABET, levelOfDifficulty, Direction.NEW_TO_OWN, null);
      tablePanel.removeAll();
      table = new ExpressionTable(tableModel,
            Direction.NEW_TO_OWN, this, true, new ExpressionColumnModel());

      tablePanel.add(new JScrollPane(table));
      tablePanel.validate();
      tablePanel.repaint();
      
   }

   @Override
   public void fireTableCellUpdated(JTable table, int selectedRow, int i)
   {
      ((ExpressionTableModel) table.getModel())
      .fireTableCellUpdated(table.getSelectedRow(), 0);
   }

}
