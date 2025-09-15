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
import vokabeltrainer.common.Common;
import vokabeltrainer.panels.notifications.EmptyNotification;
import vokabeltrainer.panels.translation.Translation;
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

   public TrashCanDialog(TrashCanControllerConnector connector)
   {
      super(Common.getjFrame(), "Papierkorb",
            Dialog.ModalityType.APPLICATION_MODAL);
      this.connector = connector;
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
      initController();
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

      selectAllInTableButton = new JButton(Common.getTranslator()
            .realisticTranslate(Translation.TABELLE_AUSWAEHLEN));
      selectAllInTableButton.setHorizontalAlignment(SwingConstants.LEFT);
      selectAllInTableButton.setFont(ApplicationFonts.getButtonFont());
      selectAllInTableButton
            .setIcon(new ImageIcon(ApplicationImages.getSelect()));

      clearInTableSelectedButton = new JButton(Common.getTranslator()
            .realisticTranslate(Translation.TABELLENAUSWAHL_AUFHEBEN));
      clearInTableSelectedButton.setHorizontalAlignment(SwingConstants.LEFT);
      clearInTableSelectedButton.setFont(ApplicationFonts.getButtonFont());
      clearInTableSelectedButton
            .setIcon(new ImageIcon(ApplicationImages.getClear()));

      restoreButton = new JButton(Common.getTranslator().realisticTranslate(Translation.AUSWAHL_WIEDER_HERSTELLEN));
      restoreButton.setHorizontalAlignment(SwingConstants.LEFT);
      restoreButton.setFont(ApplicationFonts.getButtonFont());
      restoreButton.setIcon(new ImageIcon(ApplicationImages.getRestore()));

      vertical.add(selectAllInTableButton);
      vertical.add(clearInTableSelectedButton);
      vertical.add(restoreButton);
      return vertical;
   }

   private void initController()
   {
      this.restoreButton.addActionListener(_ -> {
         if (isTableNotNull())
         {
            connector.restoreSelectedExpressions(
                  table.getSelectedExpressions(false));
         }
      });

      this.selectAllInTableButton.addActionListener(_ -> {
         connector.selectAllExpressionsInTable();
      });

      clearInTableSelectedButton.addActionListener(_ -> {
         connector.unselectAllExpressionsInTable();
      });
   }

   public void doShowTable(ExpressionTableModel tableModel)
   {
      if (tableModel.getRowCount() == 0)
      {
         EmptyNotification.display();
      }
      else
      {
         table = new ExpressionTable(tableModel,
               Direction
                     .NEW_TO_OWN,
               connector, false, new ExpressionColumnModel(Direction.NEW_TO_OWN
                     ));
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
