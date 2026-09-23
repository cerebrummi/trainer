package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import vokabeltrainer.InputLanguagePanel.Selection;
import vokabeltrainer.TextImage;
import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.SaveExpressions;
import vokabeltrainer.common.main.View;
import vokabeltrainer.common.main.Settings.LanguageStored;
import vokabeltrainer.panels.input.ChapterComboBox;
import vokabeltrainer.panels.input.TableConnector;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.ExpressionColumnModel;
import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.table.list.editor.NikudExpressionEditorController;
import vokabeltrainer.table.list.editor.LanguageExpressionEditorView;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.SortingType;
import vokabeltrainer.types.WritingDirection;

public class InputPanel extends JPanel implements TableConnector
{
   private static final long serialVersionUID = 4956932074948450143L;

   private JButton newWordPunktationButton;
   private JPanel tablePanel;
   private Chapter currentChapter;
   private ChapterComboBox chapterBox;
   private JButton tableInfoButton;
   private Translator translator;

   private JComboBox<LanguageStored> otherLanguage;

   private JComboBox<WritingDirection> myWritingDirection;

   public InputPanel(App app, Common common, Model model, View view)
   {
      translator = common.getTranslator();
      setLayout(new BullsEyeLayout(this));
      setOpaque(true);
      setBackground(app.appColors.getBackground());

      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setOpaque(false);

      JPanel filler1 = new JPanel();
      filler1.setOpaque(false);

      JPanel filler2 = new JPanel();
      filler2.setOpaque(false);

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal));
      horizontal.add(filler1);
      horizontal.add(initLeftside(app));
      horizontal.add(filler2);
      horizontal.add(initRightside(app));
      horizontal.setOpaque(false);

      JPanel spanner = new JPanel();
      spanner.setMinimumSize(new Dimension(1500, 30));
      spanner.setMaximumSize(new Dimension(1536, 30));
      spanner.add(new JLabel(translator
            .realisticTranslate(app, Translation.UM_EINE_VOKABEL_WIEDER_AUFZURUFEN__)
            + " "
            + translator.realisticTranslate(app, 
                  Translation.EINMAL_ANKLICKEN_UND_DANN_ENTER_EINGABE_TASTE)
            + " " + translator
                  .realisticTranslate(app, Translation.AUF_DER_TASTATUR_DRUECKEN_)));

      vertical.add(horizontal);
      vertical.add(spanner);

      this.add(vertical);

      initController(app, common, model, view);

      setWritingDirection(app);
      setLernsprache(app, false);
   }

   public void reset(App app, Common common, Model model)
   {
      Chapter lastModiefiedChapter = model.data
            .getChapterWithLastModifiedDate(common);
      chapterBox.setModel(model.data.getChapterComboBoxModelAsChapter(app, common));
      if (chapterBox.getModel().getSize() > 0)
      {
         chapterBox.setSelectedItem(lastModiefiedChapter);
      }
      else
      {
         chapterBox.addItem(new Chapter(common));
         chapterBox.setSelectedIndex(0);
         this.validate();
         this.repaint();
      }
   }

   private void initController(App app, Common common, Model model, View view)
   {
      newWordPunktationButton.addActionListener(
            _ -> openNewNikudExpressionDialog(app, common, model, view));

      chapterBox.addActionListener(_ -> {
         this.currentChapter = chapterBox
               .getItemAt(chapterBox.getSelectedIndex());
         this.doShowTable(app, common, model, view);
      });

      myWritingDirection.addActionListener(_ -> {
         WritingDirection writingDirection = WritingDirection.LEFT_TO_RIGHT;
         switch ((WritingDirection) myWritingDirection.getSelectedItem())
         {
         case LEFT_TO_RIGHT:
            writingDirection = WritingDirection.LEFT_TO_RIGHT;
            break;
         case RIGHT_TO_LEFT:
            writingDirection = WritingDirection.RIGHT_TO_LEFT;
            break;
         }
         app.settings.setMyWritingDirection(writingDirection);
      });

      otherLanguage.addActionListener(_ -> {
         Selection selection = Selection.GERMAN;
         switch ((LanguageStored) otherLanguage.getSelectedItem())
         {
         case HEBREW_SIMPLE:
            selection = Selection.SIMPLE;
            break;
         case HEBREW_PLENE_DEFEKTIV:
            selection = Selection.PLENE_DEFEKTIV;
            break;
         case SWEDISH:
            selection = Selection.SWEDISH;
            break;
         case GERMAN:
            selection = Selection.GERMAN;
            break;
         }
         app.settings.setLanguageInput(selection);
      });

      tableInfoButton.addActionListener(_ -> {
         JOptionPane.showMessageDialog(this, "", app.settings.getWindowTitle(),
               JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(app,
                     translator.realisticTranslate(app, Translation.TABELLE),
                     translator.realisticTranslate(app, 
                           Translation.EINMAL_KLICKEN_MARKIERT_EINEN_EINTRAG),
                     translator.realisticTranslate(app, 
                           Translation.ENTER_DRUECKEN_OEFFNET_DEN_MARKIERTEN_EINTRAG),
                     translator.realisticTranslate(app, 
                           Translation.ZWEIMAL_KLICKEN_WAEHLT_EINEN_EINTRAG_AUS__STECKNADEL_))));
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

   private void openNewNikudExpressionDialog(App app, Common common,
         Model model, View view)
   {
      LanguageExpressionEditorView editor = new NikudExpressionEditorController(app,
            common, model, view).getNikudExpressionEditorDialog();
      editor.setExpression(app, common, model, view, new Expression(app, common, true, false),
            true);
      editor.setLocationRelativeTo(view.getjFrame());
      editor.setVisible(true);

      if (editor.isSave())
      {
         Expression expression = editor.getExpression();
         model.data.putExpressionInNewMap(expression.getUuid(), expression);
         this.currentChapter = expression.getChapter();
         save(app, common, model, view);
      }
      setLernsprache(app, true);
   }

   private void doShowTable(App app, Common common, Model model, View view)
   {
      ExpressionTableModel tableModel = model.data.findTranslations(app, common,
            null, null, null, currentChapter, null, SortingType.DATE, null,
            Direction.OWN_TO_NEW, null);
      tablePanel.removeAll();
      ExpressionTable table = new ExpressionTable(app, common, model, view, tableModel,
            Direction.OWN_TO_NEW, this, true,
            new ExpressionColumnModel(app, common, model, Direction.OWN_TO_NEW));
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

   private Component initRightside(App app)
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical));
      vertical.setOpaque(false);

      JPanel flow = new JPanel();
      flow.setOpaque(false);
      tableInfoButton = new JButton(
            new ImageIcon(app.appImages.getInfoButtonIcon()));
      tableInfoButton.setBackground(app.appColors.input.getInfoButtonBackground());
      tableInfoButton.setMinimumSize(new Dimension(20, 50));
      tableInfoButton.setMaximumSize(new Dimension(20, 50));
      tableInfoButton.setMargin(new Insets(0, 0, 0, 0));
      flow.add(tableInfoButton);
      vertical.add(flow);
      vertical.add(initChapterBox(app));
      vertical.add(initTablePanel());

      return vertical;
   }

   private Component initChapterBox(App app)
   {
      chapterBox = new ChapterComboBox();
      chapterBox.setMinimumSize(new Dimension(500, 30));
      chapterBox.setMaximumSize(new Dimension(500, 30));
      chapterBox.setPreferredSize(new Dimension(500, 30));
      chapterBox.setSize(new Dimension(500, 30));
      chapterBox.setMaximumRowCount(10);
      chapterBox.setFont(app.appFonts.comboBoxFont);
      return chapterBox;
   }

   private Component initTablePanel()
   {
      tablePanel = new JPanel(new BorderLayout());
      tablePanel.setOpaque(false);
      tablePanel.setMinimumSize(new Dimension(500, 700));
      tablePanel.setMaximumSize(new Dimension(500, 700));
      return tablePanel;
   }

   private Component initLeftside(App app)
   {
      JPanel leftside = new JPanel();
      leftside.setLayout(new BullsEyeLayout(leftside));
      leftside.setOpaque(false);

      JPanel center = new JPanel();
      center.setLayout(new TotemLayout(center, 15));
      center.setOpaque(false);

      JPanel horizontal = new JPanel();
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.setOpaque(false);

      myWritingDirection = new JComboBox<>(WritingDirection.values());
      myWritingDirection.setEditable(false);
      myWritingDirection.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEmptyBorder(),
            translator.realisticTranslate(app, Translation.SCHREIBRICHTUNG), 0, 0,
            app.appFonts.buttonFont, app.appColors.input.getBoxBackground()));
      myWritingDirection.setOpaque(true);
      myWritingDirection
            .setBackground(app.appColors.input.getBoxBackground());
      myWritingDirection.setForeground(app.appColors.getBoxForeground());
      myWritingDirection.setMinimumSize(new Dimension(250, 50));
      myWritingDirection.setMaximumSize(new Dimension(250, 50));
      myWritingDirection.setMaximumRowCount(2);

      otherLanguage = new JComboBox<>(LanguageStored.values());
      otherLanguage.setEditable(false);
      otherLanguage.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEmptyBorder(),
            translator.realisticTranslate(app, Translation.NEUE_SPRACHE), 0, 0,
            app.appFonts.buttonFont, app.appColors.input.getBoxBackground()));
      otherLanguage.setOpaque(true);
      otherLanguage.setBackground(app.appColors.input.getBoxBackground());
      otherLanguage.setForeground(app.appColors.getBoxForeground());
      otherLanguage.setMinimumSize(new Dimension(250, 50));
      otherLanguage.setMaximumSize(new Dimension(250, 50));
      otherLanguage.setMaximumRowCount(4);

      newWordPunktationButton = new JButton(
            translator.realisticTranslate(app, Translation.NEUE_VOKABEL));
      newWordPunktationButton.setFont(app.appFonts.buttonFont);
      newWordPunktationButton.setHorizontalAlignment(SwingConstants.LEFT);
      newWordPunktationButton.setMinimumSize(new Dimension(300, 60));
      newWordPunktationButton.setMaximumSize(new Dimension(300, 60));
      newWordPunktationButton
            .setIcon(new ImageIcon(app.appImages.getNewWord()));
      newWordPunktationButton.setBackground(app.appColors.input.getButton());
      newWordPunktationButton.setBorder(BorderFactory.createMatteBorder(10, 10,
            10, 10, app.appColors.input.getButtonBorder()));

      horizontal.add(myWritingDirection);
      horizontal.add(otherLanguage);

      center.add(horizontal);
      center.add(newWordPunktationButton);
      leftside.add(center);
      return leftside;
   }

   private void setWritingDirection(App app)
   {
      switch (app.settings.getMyWritingDirection())
      {
      case LEFT_TO_RIGHT:
         myWritingDirection.setSelectedItem(WritingDirection.LEFT_TO_RIGHT);
         break;
      case RIGHT_TO_LEFT:
         myWritingDirection.setSelectedItem(WritingDirection.RIGHT_TO_LEFT);
         break;
      }

   }

   public void setLernsprache(App app, boolean update)
   {
      switch (app.settings.getLanguageInput())
      {
      case GERMAN:
         otherLanguage.setSelectedItem(LanguageStored.GERMAN);
         break;
      case PLENE_DEFEKTIV:
         otherLanguage.setSelectedItem(LanguageStored.HEBREW_PLENE_DEFEKTIV);
         break;
      case SIMPLE:
         otherLanguage.setSelectedItem(LanguageStored.HEBREW_SIMPLE);
         break;
      case SWEDISH:
         otherLanguage.setSelectedItem(LanguageStored.SWEDISH);
         break;
      default:
         otherLanguage.setSelectedItem(LanguageStored.HEBREW_SIMPLE);
      }
      if (update)
      {
         otherLanguage.revalidate();
         otherLanguage.repaint();
      }
   }

   @Override
   public void save(App app, Common common, Model model, View view)
   {
      new SwingWorker<Void, Void>()
      {
         @Override
         protected Void doInBackground() throws Exception
         {
            if (new SaveExpressions(app, model).save(app, common, view))
            {
               chapterBox.setModel(
                     model.data.getChapterComboBoxModelAsChapter(app, common));
               chapterBox.setSelectedItem(currentChapter);
            }
            return null;
         }
      }.execute();
   }

   @Override
   public void fireTableCellUpdated(App app, Common common, Model model,
         View view, JTable table, int selectedRow, int i)
   {
      ((ExpressionTableModel) table.getModel())
            .fireTableCellUpdated(table.getSelectedRow(), 0);
   }

}
