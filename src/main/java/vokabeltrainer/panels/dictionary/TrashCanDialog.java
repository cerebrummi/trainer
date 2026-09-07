package vokabeltrainer.panels.dictionary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import vokabeltrainer.TrashCanBackgroundPanel;
import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.common.main.View;
import vokabeltrainer.panels.notifications.EmptyNotification;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.ExpressionColumnModel;
import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Direction;

public class TrashCanDialog extends JDialog implements TrashCanDialogConnector
{
   private static final long serialVersionUID = 5581839704958393075L;

   private JPanel layout;
   private ExpressionTable table;
   private JPanel tablePanel;
   private JButton restoreButton;
   private JButton selectAllInTableButton;
   private JButton clearInTableSelectedButton;
   private boolean restore;
   private TrashCanControllerConnector connector;
   private Translator translator;

   public TrashCanDialog(App app, Common common, Model model, View view, TrashCanControllerConnector connector)
   {
      super(view.getjFrame(), "Papierkorb",
            Dialog.ModalityType.APPLICATION_MODAL);
      
      this.connector = connector;
      translator = common.getTranslator();
      restore = false;
      setSize(1000, 620);
      getContentPane().setPreferredSize(new Dimension(1000, 620));

      layout = new TrashCanBackgroundPanel(app);
      layout.setPreferredSize(new Dimension(1000, 620));
      layout.setBorder(BorderFactory.createLineBorder(new Color(169, 136, 103),
            15, false));
      layout.setLayout(new TrainLayout(layout, 15));
      getContentPane().add(layout);

      initGui(app);
      initController(app, common, model, view);
   }

   private void initGui(App app)
   {
      tablePanel = new JPanel(new BorderLayout());
      tablePanel.setMinimumSize(new Dimension(400, 540));
      tablePanel.setMinimumSize(new Dimension(400, 580));
      tablePanel.setOpaque(false);

      layout.add(tablePanel);
      layout.add(initControlPanel(app));
   }

   private Component initControlPanel(App app)
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 15));
      vertical.setOpaque(false);
      vertical.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));

      selectAllInTableButton = new JButton(translator
            .realisticTranslate(app, Translation.TABELLE_AUSWAEHLEN));
      selectAllInTableButton.setHorizontalAlignment(SwingConstants.LEFT);
      selectAllInTableButton.setFont(app.appFonts.buttonFont);
      selectAllInTableButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      selectAllInTableButton.setBackground(app.appColors.dictionary.getBackground());
      selectAllInTableButton
            .setIcon(new ImageIcon(app.appImages.getSelect()));

      clearInTableSelectedButton = new JButton(translator
            .realisticTranslate(app, Translation.TABELLENAUSWAHL_AUFHEBEN));
      clearInTableSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      clearInTableSelectedButton.setFont(app.appFonts.buttonFont);
      clearInTableSelectedButton
            .setForeground(app.appColors.dictionary.getButtonForeground());
      clearInTableSelectedButton
            .setBackground(app.appColors.dictionary.getBackground());
      clearInTableSelectedButton
            .setIcon(new ImageIcon(app.appImages.getClear()));

      restoreButton = new JButton(translator
            .realisticTranslate(app, Translation.AUSWAHL_WIEDER_HERSTELLEN));
      restoreButton.setHorizontalAlignment(SwingConstants.LEFT);
      restoreButton.setFont(app.appFonts.buttonFont);
      restoreButton.setForeground(app.appColors.dictionary.getButtonForeground());
      restoreButton.setBackground(app.appColors.dictionary.getBackground());
      restoreButton.setIcon(new ImageIcon(app.appImages.getRestore()));

      vertical.add(selectAllInTableButton);
      vertical.add(clearInTableSelectedButton);
      vertical.add(restoreButton);
      return vertical;
   }

   private void initController(App app, Common common, Model model, View view)
   {
      this.restoreButton.addActionListener(_ -> {
         if (isTableNotNull())
         {
            connector.restoreSelectedExpressions(app, common, model, view,
                  table.getSelectedExpressions(false));
         }
      });

      this.selectAllInTableButton.addActionListener(_ -> {
         connector.selectAllExpressionsInTable(app, common, model, view);
      });

      clearInTableSelectedButton.addActionListener(_ -> {
         connector.unselectAllExpressionsInTable(app, common, model, view);
      });
   }

   public void doShowTable(App app, Common common, Model model, View view, ExpressionTableModel tableModel)
   {
      if (tableModel.getRowCount() == 0)
      {
         EmptyNotification.display(app, view);
      }
      else
      {
         table = new ExpressionTable(app, common, model, view, tableModel, Direction.NEW_TO_OWN,
               connector, false,
               new ExpressionColumnModel(app, common, model, Direction.NEW_TO_OWN));
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setOpaque(false);
         scrollPane.getViewport().setOpaque(false);
         scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
         tablePanel.add(scrollPane);
      }
      this.tableValidateRepaint();
   }

   public void clearTable()
   {
      stopTableEditing();
      tablePanel.removeAll();
   }

   private void stopTableEditing()
   {
      if (table != null && table.isEditing())
      {
         table.getCellEditor().stopCellEditing();
      }
   }

   @Override
   public boolean isRestore()
   {
      return restore;
   }

   @Override
   public void tableValidateRepaint()
   {
      tablePanel.validate();
      tablePanel.repaint();
   }

   @Override
   public boolean isTableNotNull()
   {
      return table != null;
   }

   @Override
   public void setRestore(boolean restore)
   {
      this.restore = restore;
   }

   @Override
   public void selectAllExpressionsInTable()
   {
      table.selectAllExpressions();
   }

   @Override
   public void unselectAllExpressionsInTable()
   {
      table.clearTableDataSelection();
   }

}
