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
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.colors.DictionaryColors;
import vokabeltrainer.common.main.Common;
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

   public TrashCanDialog(Common common, View view, TrashCanControllerConnector connector)
   {
      super(view.getjFrame(), "Papierkorb",
            Dialog.ModalityType.APPLICATION_MODAL);
      
      this.connector = connector;
      translator = common.getTranslator();
      restore = false;
      setSize(1000, 620);
      getContentPane().setPreferredSize(new Dimension(1000, 620));

      layout = new TrashCanBackgroundPanel();
      layout.setPreferredSize(new Dimension(1000, 620));
      layout.setBorder(BorderFactory.createLineBorder(new Color(169, 136, 103),
            15, false));
      layout.setLayout(new TrainLayout(layout, 15));
      getContentPane().add(layout);

      initGui();
      initController(common, view);
   }

   private void initGui()
   {
      tablePanel = new JPanel(new BorderLayout());
      tablePanel.setMinimumSize(new Dimension(400, 540));
      tablePanel.setMinimumSize(new Dimension(400, 580));
      tablePanel.setOpaque(false);

      layout.add(tablePanel);
      layout.add(initControlPanel());
   }

   private Component initControlPanel()
   {
      JPanel vertical = new JPanel();
      vertical.setLayout(new TotemLayout(vertical, 15));
      vertical.setOpaque(false);
      vertical.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));

      selectAllInTableButton = new JButton(translator
            .realisticTranslate(Translation.TABELLE_AUSWAEHLEN));
      selectAllInTableButton.setHorizontalAlignment(SwingConstants.LEFT);
      selectAllInTableButton.setFont(ApplicationFonts.buttonFont);
      selectAllInTableButton
            .setForeground(DictionaryColors.getButtonForeground());
      selectAllInTableButton.setBackground(DictionaryColors.getBackground());
      selectAllInTableButton
            .setIcon(new ImageIcon(ApplicationImages.getSelect()));

      clearInTableSelectedButton = new JButton(translator
            .realisticTranslate(Translation.TABELLENAUSWAHL_AUFHEBEN));
      clearInTableSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      clearInTableSelectedButton.setFont(ApplicationFonts.buttonFont);
      clearInTableSelectedButton
            .setForeground(DictionaryColors.getButtonForeground());
      clearInTableSelectedButton
            .setBackground(DictionaryColors.getBackground());
      clearInTableSelectedButton
            .setIcon(new ImageIcon(ApplicationImages.getClear()));

      restoreButton = new JButton(translator
            .realisticTranslate(Translation.AUSWAHL_WIEDER_HERSTELLEN));
      restoreButton.setHorizontalAlignment(SwingConstants.LEFT);
      restoreButton.setFont(ApplicationFonts.buttonFont);
      restoreButton.setForeground(DictionaryColors.getButtonForeground());
      restoreButton.setBackground(DictionaryColors.getBackground());
      restoreButton.setIcon(new ImageIcon(ApplicationImages.getRestore()));

      vertical.add(selectAllInTableButton);
      vertical.add(clearInTableSelectedButton);
      vertical.add(restoreButton);
      return vertical;
   }

   private void initController(Common common, View view)
   {
      this.restoreButton.addActionListener(_ -> {
         if (isTableNotNull())
         {
            connector.restoreSelectedExpressions(common, view,
                  table.getSelectedExpressions(false));
         }
      });

      this.selectAllInTableButton.addActionListener(_ -> {
         connector.selectAllExpressionsInTable(common, view);
      });

      clearInTableSelectedButton.addActionListener(_ -> {
         connector.unselectAllExpressionsInTable(common, view);
      });
   }

   public void doShowTable(Common common, View view, ExpressionTableModel tableModel)
   {
      if (tableModel.getRowCount() == 0)
      {
         EmptyNotification.display(view);
      }
      else
      {
         table = new ExpressionTable(common, view, tableModel, Direction.NEW_TO_OWN,
               connector, false,
               new ExpressionColumnModel(common, Direction.NEW_TO_OWN));
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
