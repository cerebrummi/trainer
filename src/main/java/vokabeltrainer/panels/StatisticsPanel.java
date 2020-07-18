package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.panels.statistics.StatisticsTable;
import vokabeltrainer.panels.statistics.StatisticsTableRow;
import vokabeltrainer.types.ExpressionKind;

public class StatisticsPanel extends BackgroundPanelTiled
{
   private static final long serialVersionUID = -3937351898121564812L;

   private List<JLabel> labelLabelList = new ArrayList<>();
   private List<JLabel> valueLabelList = new ArrayList<>();

   private JLabel valueAll;
   private JPanel tablePanel;

   public StatisticsPanel()
   {
      setLayout(new BorderLayout());

      JPanel filler = new JPanel();
      filler.setPreferredSize(new Dimension(500, 60));
      filler.setOpaque(false);
      add(filler, BorderLayout.NORTH);

      JPanel center = new JPanel(new FlowLayout());
      center.setOpaque(false);
      JPanel centerPanel = new JPanel();
      List<ExpressionKind> list = ExpressionKind.getValues();
      centerPanel.setLayout(new GridBagLayout());
      GridBagConstraints constraints = new GridBagConstraints();
      centerPanel.setOpaque(false);
      centerPanel.setSize(500, (list.size() + 2) * 30);

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

      center.add(centerPanel);

      tablePanel = new JPanel(new BorderLayout());

      add(center, BorderLayout.WEST);
      add(tablePanel, BorderLayout.CENTER);
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
      tablePanel.add(new JLabel("Trainings Übersicht"), BorderLayout.NORTH);
      tablePanel.add(new JScrollPane(new StatisticsTable(Data.findStatisticsModel())),
            BorderLayout.CENTER);
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
