package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import vokabeltrainer.ApplicationColors;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.table.list.editor.NikudExpressionEditorController;
import vokabeltrainer.table.list.editor.NikudExpressionEditorView;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;
import vokabeltrainer.types.SortingType;

public class InputPanel extends BackgroundPanelTiled implements TableConnector
{
   private static final long serialVersionUID = 4956932074948450143L;

   private JButton newWordPunktationButton;

   private JPanel tablePanel;
   private Chapter currentChapter;

   public InputPanel()
   {
      setLayout(new TotemLayout(this));

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal));
      horizontal.add(new JPanel());
      horizontal.add(initLeftside());
      horizontal.add(new JPanel());
      horizontal.add(initTablePanel());

      JPanel spanner = new JPanel();
      spanner.setMinimumSize(new Dimension(1550, 30));
      spanner.setMaximumSize(new Dimension(1550, 30));

      this.add(horizontal);
      this.add(spanner);

      initController();
   }

   public void reset()
   {
      tablePanel.removeAll();
      tablePanel.validate();
      tablePanel.repaint();
   }

   @Override
   public void save()
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            if (new SaveExpressions().save())
            {
               doShowTable();
            }
            return null;
         }
      }.execute();
   }
   
   private void initController()
   {
      newWordPunktationButton
            .addActionListener(event -> openNewNikudExpressionDialog());
   }

   private void openNewNikudExpressionDialog()
   {
      NikudExpressionEditorView editor = new NikudExpressionEditorController()
            .getNikudExpressionEditorDialog();
      editor.setExpression(new Expression(true, false, false), true);
      editor.setLocationRelativeTo(null);
      editor.setVisible(true);
      if (editor.isSave())
      {
         Expression expression = editor.getExpression();
         Data.putExpressionInNewMap(expression.getUuid(), expression);
         this.currentChapter = expression.getChapter();
         save();
      }
   }
   
   private void doShowTable()
   {
      ExpressionTableModel tableModel = Data.findTranslations(
            Language.GERMAN, null, null, null,
            currentChapter, null, SortingType.DATE);
      tablePanel.removeAll();
      ExpressionTable table = new ExpressionTable(tableModel, Language.GERMAN,
            this, true);
      JScrollPane tableScroller = new JScrollPane(table);
      tableScroller.setOpaque(false);
      tableScroller.getViewport().setOpaque(false);
      tableScroller.setViewportBorder(BorderFactory.createEmptyBorder());
      tableScroller.getVerticalScrollBar().setUnitIncrement(30);
      tableScroller.setMinimumSize(new Dimension(300,300));
      tableScroller.setMaximumSize(new Dimension(500,800));

      tablePanel.add(tableScroller);
      tablePanel.validate();
      tablePanel.repaint();
   }
   
   private Component initTablePanel()
   {
      tablePanel = new JPanel(new BorderLayout());
      tablePanel.setMinimumSize(new Dimension(500, 800));
      tablePanel.setMaximumSize(new Dimension(500, 800));
      return tablePanel;
   }

   private Component initLeftside()
   {
      JPanel leftside = new JPanel();
      leftside.setLayout(new BullsEyeLayout(leftside));

      newWordPunktationButton = new JButton("neue Vokabel");
      newWordPunktationButton.setFont(Main.getGermanFont(16F));
      newWordPunktationButton.setHorizontalAlignment(SwingConstants.LEFT);
      newWordPunktationButton.setMinimumSize(new Dimension(300, 60));
      newWordPunktationButton.setMaximumSize(new Dimension(300, 60));
      newWordPunktationButton
            .setIcon(new ImageIcon(ApplicationImages.getNewWord()));
      newWordPunktationButton.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10,
            ApplicationColors.getGreen()));

      leftside.add(newWordPunktationButton);
      return leftside;
   }
}
