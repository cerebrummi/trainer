package vokabeltrainer.panels.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import vokabeltrainer.Command;
import vokabeltrainer.Settings;
import vokabeltrainer.TrashCanBackgroundPanel;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.panels.dictionary.TrashCanControllerConnector;
import vokabeltrainer.ApplicationImages;
import vokabeltrainer.table.ExpressionTable;
import vokabeltrainer.table.ExpressionTableModel;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.Language;

public class TrashCanDialog extends JDialog
{
   private static final long serialVersionUID = 5581839704958393075L;

   private JPanel layout;
   private ButtonGroup languageGroup;
   private ExpressionTable table;
   private Language initialLanguage;
   private JPanel tablePanel;
   private JButton restoreButton;
   private JButton selectAllInTableButton;
   private JButton clearInTableSelectedButton;
   private boolean restore;
   private TrashCanControllerConnector connector;

   public TrashCanDialog(TrashCanControllerConnector connector, Language initialLanguage)
   {
      super(Common.getjFrame(), "Papierkorb",
            Dialog.ModalityType.APPLICATION_MODAL);
      this.connector = connector;
      this.initialLanguage = initialLanguage;
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
      decideOnTable(Command.INITIAL.name());
      pack();
   }

   private void initGui()
   {
      tablePanel = new JPanel(new BorderLayout());
      tablePanel.setMinimumSize(new Dimension(400, 580));
      tablePanel.setMinimumSize(new Dimension(400, 620));
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

      JPanel horizontal1 = new JPanel();
      horizontal1.setLayout(new TrainLayout(horizontal1, 15));
      horizontal1.setOpaque(false);
      horizontal1.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
      languageGroup = new ButtonGroup();
      initLanguageButtonGroup(languageGroup);
      Enumeration<AbstractButton> enumeration1 = languageGroup.getElements();
      while (enumeration1.hasMoreElements())
      {
         AbstractButton button = enumeration1.nextElement();
         button.addActionListener(
               event -> decideOnTable(button.getActionCommand()));
         horizontal1.add(button);
      }

      selectAllInTableButton = new JButton("Tabelle auswählen");
      selectAllInTableButton.setFont(Settings.getButtonFont());
      selectAllInTableButton
            .setIcon(new ImageIcon(ApplicationImages.getSelect()));

      clearInTableSelectedButton = new JButton("Tabellenauswahl aufheben");
      clearInTableSelectedButton.setFont(Settings.getButtonFont());
      clearInTableSelectedButton
            .setIcon(new ImageIcon(ApplicationImages.getClear()));

      restoreButton = new JButton("Auswahl wiederherstellen");
      restoreButton.setFont(Main.getGermanFont(16F));
      restoreButton.setIcon(new ImageIcon(ApplicationImages.getRestore()));

      vertical.add(horizontal1);
      vertical.add(selectAllInTableButton);
      vertical.add(clearInTableSelectedButton);
      vertical.add(restoreButton);
      return vertical;
   }

   private void initController()// TODO
   {
      this.restoreButton.addActionListener(event -> {
         if (table != null)
         {
            List<Expression> list = table.getSelectedExpressions();
            if (!list.isEmpty())
            {
               restore = true;
               Data.restoreExpressions(list);
               decideOnTable(Command.RESTORE.name());
            }
         }
      });

      this.selectAllInTableButton.addActionListener(event -> {
         if (table != null)
         {
            table.selectAllExpressions();
            decideOnTable(Command.SELECT_ALL.name());
         }
      });

      clearInTableSelectedButton.addActionListener(event -> {
         if (table != null)
         {
            table.clearTableDataSelection();
            decideOnTable(Command.CLEAR.name());

         }
      });
   }

   private void decideOnTable(String actionCommand)
   {
      clearTable();

      ExpressionTableModel tableModel = null;

      tableModel = Data.findTranslationsDeletedWords(
            Language.valueOf(languageGroup.getSelection().getActionCommand()));

      if (tableModel.getRowCount() == 0)
      {
         EmptyNotification.display();
         tablePanel.validate();
         tablePanel.repaint();
         return;
      }
      doShowTable(tableModel);
   }

   private void doShowTable(ExpressionTableModel tableModel)
   {
      table = new ExpressionTable(tableModel,
            Language.valueOf(languageGroup.getSelection().getActionCommand()),
            connector.getDictionaryControllerConnector(),
            false);
      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setOpaque(false);
      scrollPane.getViewport().setOpaque(false);
      scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
      tablePanel.add(scrollPane);
      tablePanel.validate();
      tablePanel.repaint();
   }

   private void clearTable()
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

   private void initLanguageButtonGroup(ButtonGroup languageTypeGroup)
   {
      Font font = Main.getGermanFont(20F);

      JRadioButton german = new JRadioButton("Deutsch");
      german.setActionCommand(Language.GERMAN.name());
      german.setFont(font);
      languageTypeGroup.add(german);

      JRadioButton hebrew = new JRadioButton("Hebräisch");
      hebrew.setActionCommand(Language.HEBREW.name());
      hebrew.setFont(font);
      languageTypeGroup.add(hebrew);

      if (Language.GERMAN.equals(initialLanguage))
      {
         german.setSelected(true);
      }
      else
      {
         hebrew.setSelected(true);
      }
   }

   public boolean isRestore()
   {
      return restore;
   }

}
