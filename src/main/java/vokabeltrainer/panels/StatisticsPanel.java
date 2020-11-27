package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.panels.statistics.StatisticsTable;
import vokabeltrainer.panels.statistics.StatisticsTableRow;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class StatisticsPanel extends BackgroundPanelTiled
{
   private static final long serialVersionUID = -3937351898121564812L;

   private JPanel tablePanel;
   private JPanel wordPanel;

   private int height;

   public StatisticsPanel()
   {
      setLayout(new BorderLayout());
      this.setPreferredSize(new Dimension(990, 643));
      this.setSize(990, 643);

      JPanel eyePanel = new JPanel();
      eyePanel.setLayout(new BullsEyeLayout(eyePanel));
      
      JPanel center = new JPanel();
      center.setLayout(new TrainLayout(center, 15));
      center.setOpaque(false);

      tablePanel = new JPanel();
      tablePanel.setLayout(new TotemLayout(tablePanel));
      tablePanel.setOpaque(true);
      tablePanel.setBackground(Settings.getVeryLightGold());
      tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

      wordPanel = new JPanel(new BorderLayout());
      wordPanel.setMinimumSize(new Dimension(1100, 160));
      wordPanel.setMaximumSize(new Dimension(1100, 160));
      wordPanel.setOpaque(true);
      wordPanel.setBackground(Settings.getVeryLightGold());
     
      center.add(tablePanel);

      eyePanel.add(center);
      add(eyePanel, BorderLayout.CENTER);
   }

   public void setValues()
   {
      tablePanel.removeAll();
      wordPanel.removeAll();

      JPanel titlePanel = new JPanel(new FlowLayout());
      titlePanel.setOpaque(false);
      titlePanel.setBackground(Settings.getTransparent());
      titlePanel.setMinimumSize(new Dimension(580, 50));
      titlePanel.setMaximumSize(new Dimension(580, 50));

      JLabel title = new JLabel("Trainings Übersicht");
      title.setFont(Main.getGermanFont(30F));
      titlePanel.add(title);

      StatisticsTable table = new StatisticsTable(Data.findStatisticsModel());

      table.addMouseListener(new MouseAdapter()
      {
         public void mousePressed(MouseEvent mouseEvent)
         {
            JTable table = (JTable) mouseEvent.getSource();
            Point point = mouseEvent.getPoint();
            int row = table.rowAtPoint(point);
            int column = table.columnAtPoint(point);
            if (table.getSelectedRow() != -1 && row == table.getSelectedRow())
            {
               wordPanel.removeAll();

               if (column == 1)
               {
                  StatisticsTableRow statisticsTableRow = ((StatisticsTableRow) table
                        .getValueAt(table.getSelectedRow(), 1));
                  JScrollPane scroller = new JScrollPane(
                        statisticsTableRow.getJListHtoD());
                  scroller.setOpaque(false);
                  scroller.setBackground(Settings.getTransparent());
                  scroller.setBorder(BorderFactory.createEmptyBorder());
                  scroller.getViewport().setOpaque(false);
                  scroller.getViewport()
                        .setBackground(Settings.getTransparent());
                  scroller.setViewportBorder(BorderFactory.createEmptyBorder());
                  wordPanel.add(scroller, BorderLayout.CENTER);
               }
               else if (column == 2)
               {
                  StatisticsTableRow statisticsTableRow = ((StatisticsTableRow) table
                        .getValueAt(table.getSelectedRow(), 2));
                  JScrollPane scroller = new JScrollPane(
                        statisticsTableRow.getJListDtoH());
                  scroller.setOpaque(false);
                  scroller.setBackground(Settings.getTransparent());
                  scroller.setBorder(BorderFactory.createEmptyBorder());
                  scroller.getViewport().setOpaque(false);
                  scroller.getViewport()
                        .setBackground(Settings.getTransparent());
                  scroller.setViewportBorder(BorderFactory.createEmptyBorder());
                  wordPanel.add(scroller, BorderLayout.CENTER);
               }
               wordPanel.validate();
               wordPanel.repaint();
            }
         }
      });
      
      JScrollPane scroller = new JScrollPane(table);
      scroller.setOpaque(false);
      scroller.setBackground(Settings.getTransparent());
      scroller.setBorder(BorderFactory.createEmptyBorder());
      scroller.getViewport().setOpaque(false);
      scroller.getViewport().setBackground(Settings.getTransparent());
      scroller.setViewportBorder(BorderFactory.createEmptyBorder());
      scroller.setBackground(Settings.getDarkRed());

      tablePanel.add(titlePanel);
      tablePanel.add(scroller);
      tablePanel.add(wordPanel);
   }
}
