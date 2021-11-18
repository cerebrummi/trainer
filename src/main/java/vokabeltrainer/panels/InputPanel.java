package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import vokabeltrainer.ApplicationColors;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.common.SaveExpressions;
import vokabeltrainer.panels.input.ChapterComboBox;
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
   private ChapterComboBox chapterBox;
   private JButton tableInfoButton;

   public InputPanel()
   {
      setLayout(new TrainLayout(this));
      
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal));
      horizontal.add(new JPanel());
      horizontal.add(initLeftside());
      horizontal.add(new JPanel());
      horizontal.add(initRightside());

      JPanel spanner = new JPanel();
      spanner.setMinimumSize(new Dimension(1550, 30));
      spanner.setMaximumSize(new Dimension(1550, 30));
      spanner.add(new JLabel("Um eine Vokabel wieder aufzurufen, einmal anklicken und dann Enter/Eingabe Taste auf der Tastatur drücken."));

      vertical.add(horizontal);
      vertical.add(spanner);
      
      this.add(vertical);
      
      initController();
   }

  

   public void reset()
   {
      chapterBox.setModel(Data.getChapterComboBoxModelAsChapter());
      if (chapterBox.getModel().getSize() > 0)
      {
         chapterBox.setSelectedIndex(chapterBox.getItemCount() - 1);
      }
      else
      {
         chapterBox.addItem(new Chapter());
         chapterBox.setSelectedIndex(-0);
         this.validate();
         this.repaint();
      }
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
               chapterBox.setModel(Data.getChapterComboBoxModelAsChapter());
               chapterBox.setSelectedItem(currentChapter);
            }
            return null;
         }
      }.execute();
   }

   private void initController()
   {
      newWordPunktationButton
            .addActionListener(event -> openNewNikudExpressionDialog());

      chapterBox.addActionListener(event -> {
         this.currentChapter = chapterBox
               .getItemAt(chapterBox.getSelectedIndex());
         this.doShowTable();
      });
      
      tableInfoButton.addActionListener(event -> {
         JOptionPane.showMessageDialog(this, "",
               "Cerebrummi©", JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make("Tabelle",
                     "einmal klicken markiert einen Eintrag",
                     "Enter drücken öffnet den markierten Eintrag",
                     "zweimal klicken wählt einen Eintrag aus (Stecknadel)")));
      });

      tableInfoButton.addMouseListener(new MouseListener()
      {

         @Override
         public void mouseClicked(MouseEvent e)
         {

         }

         @Override
         public void mousePressed(MouseEvent e)
         {

         }

         @Override
         public void mouseReleased(MouseEvent e)
         {

         }

         @Override
         public void mouseEntered(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         }

         @Override
         public void mouseExited(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         }
      });
   }

   private void openNewNikudExpressionDialog()
   {
      NikudExpressionEditorView editor = new NikudExpressionEditorController()
            .getNikudExpressionEditorDialog();
      editor.setExpression(new Expression(true, false), true);
      editor.setLocationRelativeTo(Common.getjFrame());
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
      ExpressionTableModel tableModel = Data.findTranslations(Language.GERMAN_TO_HEBREW,
            null, null, null, currentChapter, null, SortingType.DATE);
      tablePanel.removeAll();
      ExpressionTable table = new ExpressionTable(tableModel, Language.GERMAN_TO_HEBREW,
            this, true);
      JScrollPane tableScroller = new JScrollPane(table);
      tableScroller.setOpaque(false);
      tableScroller.getViewport().setOpaque(false);
      tableScroller.setViewportBorder(BorderFactory.createEmptyBorder());
      tableScroller.getVerticalScrollBar().setUnitIncrement(30);
      tableScroller.setMinimumSize(new Dimension(500, 700));
      tableScroller.setMaximumSize(new Dimension(500, 700));

      tablePanel.add(tableScroller);
      tablePanel.validate();
      tablePanel.repaint();
   }
   
   private Component initRightside()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      
      JPanel flow = new JPanel();
      tableInfoButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      tableInfoButton.setBackground(new Color(0, 0, 0, 0));
      tableInfoButton.setMinimumSize(new Dimension(20, 50));
      tableInfoButton.setMaximumSize(new Dimension(20, 50));
      tableInfoButton.setMargin(new Insets(0, 0, 0, 0));
      flow.add(tableInfoButton);
      vertical.add(flow);
      vertical.add(initChapterBox());
      vertical.add(initTablePanel());

      return vertical;
   }

   private Component initChapterBox()
   {
      chapterBox = new ChapterComboBox();
      chapterBox.setMinimumSize(new Dimension(500, 30));
      chapterBox.setMaximumSize(new Dimension(500, 30));
      chapterBox.setPreferredSize(new Dimension(500, 30));
      chapterBox.setSize(new Dimension(500, 30));
      chapterBox.setMaximumRowCount(10);
      chapterBox.setFont(Main.getGermanFont(14));
      return chapterBox;
   }

   private Component initTablePanel()
   {
      tablePanel = new JPanel(new BorderLayout());
      tablePanel.setMinimumSize(new Dimension(500, 700));
      tablePanel.setMaximumSize(new Dimension(500, 700));
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
      newWordPunktationButton.setBorder(BorderFactory.createMatteBorder(10, 10,
            10, 10, ApplicationColors.getGreen()));

      leftside.add(newWordPunktationButton);
      return leftside;
   }
}
