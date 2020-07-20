package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
import vokabeltrainer.tonionlayout.TotemLayoutTest;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.tonionlayout.TrainLayoutTest;
import vokabeltrainer.types.ExpressionKind;

public class StatisticsPanel extends BackgroundPanelTiled
{
   private static final long serialVersionUID = -3937351898121564812L;

   private List<JLabel> labelLabelList = new ArrayList<>();
   private List<JLabel> valueLabelList = new ArrayList<>();

   private JLabel valueAll;
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

      JPanel centerPanel = new JPanel();
      List<ExpressionKind> list = ExpressionKind.getValues();
      centerPanel.setLayout(new GridBagLayout());
      GridBagConstraints constraints = new GridBagConstraints();
      centerPanel.setOpaque(true);
      centerPanel.setBackground(Settings.getVeryLightGold());
      height = ((list.size() + 2) * 30) + 100;
      centerPanel.setMinimumSize(new Dimension(380, height));
      centerPanel.setMaximumSize(new Dimension(380, height));

      JLabel title = new JLabel("Anzahl der Wortformen");
      title.setFont(Main.getGermanFont(30F));
      title.setSize(250, 30);
      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.gridwidth = 2;
      constraints.fill = GridBagConstraints.HORIZONTAL;
      centerPanel.add(title, constraints);

      int counter = 1;
      for (; counter < list.size(); counter++)
      {
         JLabel label = new JLabel();
         label.setFont(Main.getGermanFont(20F));
         label.setSize(250, 30);
         label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
         labelLabelList.add(label);
         constraints.gridx = 0;
         constraints.gridy = counter;
         constraints.gridwidth = 1;
         centerPanel.add(label, constraints);

         JLabel value = new JLabel();
         value.setFont(Main.getGermanFont(20F));
         value.setSize(250, 30);
         value.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
         value.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
         valueLabelList.add(value);
         constraints.gridx = 1;
         constraints.gridy = counter;
         constraints.gridwidth = 1;
         centerPanel.add(value, constraints);
      }

      JLabel label = new JLabel("Alle  ");
      label.setFont(Main.getGermanFont(20F));
      label.setSize(250, 30);
      label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
      constraints.gridx = 0;
      constraints.gridy = counter;
      constraints.gridwidth = 1;
      centerPanel.add(label, constraints);

      valueAll = new JLabel();
      valueAll.setFont(Main.getGermanFont(20F));
      valueAll.setSize(250, 30);
      valueAll.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
      constraints.gridx = 1;
      constraints.gridy = counter;
      constraints.gridwidth = 1;
      centerPanel.add(valueAll, constraints);
      counter++;

      tablePanel = new JPanel();
      tablePanel.setLayout(new TotemLayout(tablePanel));
      tablePanel.setOpaque(true);
      tablePanel.setBackground(Settings.getVeryLightGold());
      tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

      wordPanel = new JPanel(new BorderLayout());
      wordPanel.setMinimumSize(new Dimension(580, 170));
      wordPanel.setMaximumSize(new Dimension(580, 170));
      wordPanel.setOpaque(true);
      wordPanel.setBackground(Settings.getVeryLightGold());

      center.add(centerPanel);
      center.add(tablePanel);

      eyePanel.add(center);
      add(eyePanel);
   }

   public void setValues()
   {
      List<ExpressionKind> kindList = ExpressionKind.getValues();

      int counter = 0;
      for (JLabel labelLabel : labelLabelList)
      {
         labelLabel.setText(kindList.get(counter).toString() + " ");
         counter++;
      }

      counter = 0;
      for (JLabel valueLabel : valueLabelList)
      {
         valueLabel.setText(" " + findValue(kindList.get(counter)));
         counter++;
      }

      valueAll.setText(" " + String.valueOf(Data.getAlleMapSize()));

      tablePanel.removeAll();

      JPanel titlePanel = new JPanel(new FlowLayout());
      titlePanel.setOpaque(false);
      titlePanel.setBackground(Settings.getTransparent());
      titlePanel.setMinimumSize(new Dimension(580, 50));
      titlePanel.setMaximumSize(new Dimension(580, 50));

      JLabel title = new JLabel("Trainings Übersicht");
      title.setFont(Main.getGermanFont(30F));
      titlePanel.add(title);

      StatisticsTable table = new StatisticsTable(Data.findStatisticsModel());
      table.setOpaque(true);
      table.setBackground(Settings.getVeryLightGold());

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

      JPanel scrollerWrapper = new JPanel(new BorderLayout());
      scrollerWrapper.setMinimumSize(new Dimension(580, height - 250));
      scrollerWrapper.setMaximumSize(new Dimension(580, height - 250));
      scrollerWrapper.setOpaque(true);
      scrollerWrapper.setBackground(Settings.getVeryLightGold());
      
      JScrollPane scroller = new JScrollPane(table);
      scroller.setOpaque(false);
      scroller.setBackground(Settings.getTransparent());
      scroller.setBorder(BorderFactory.createEmptyBorder());
      scroller.getViewport().setOpaque(false);
      scroller.getViewport().setBackground(Settings.getTransparent());
      scroller.setViewportBorder(BorderFactory.createEmptyBorder());
      scrollerWrapper.add(scroller, BorderLayout.CENTER);

      tablePanel.add(titlePanel);
      tablePanel.add(scrollerWrapper);
      tablePanel.add(wordPanel);
   }

   private String findValue(ExpressionKind kind)
   {
      switch (kind)
      {
      case ADJEKTIV:
         return String.valueOf(Data.getAdjektivMapSize());
      case ADVERB:
         return String.valueOf(Data.getAdverbMapSize());
      case BEGRIFF:
         return String.valueOf(Data.getBegriffMapSize());
      case FRAGE:
         return String.valueOf(Data.getFrageMapSize());
      case INTERJEKTION:
         return String.valueOf(Data.getInterjektionMapSize());
      case NUMERAL:
         return String.valueOf(Data.getNumeralMapSize());
      case PRONOM:
         return String.valueOf(Data.getPronomMapSize());
      case UNKOWN:
         return String.valueOf(Data.getUnkownMapSize());
      case VERB:
         return String.valueOf(Data.getVerbMapSize());
      case PARTIKEL:
         return String.valueOf(Data.getPartikelMapSize());
      case SUBSTANTIV:
         return String.valueOf(Data.getSubstantivMapSize());
      case KONSTRUKT:
         return String.valueOf(Data.getConstructusMapSize());
      }
      return "Wert";
   }

}
