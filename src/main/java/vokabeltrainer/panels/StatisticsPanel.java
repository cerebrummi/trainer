package vokabeltrainer.panels;

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

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.panels.statistics.StatisticsTable;
import vokabeltrainer.panels.statistics.StatisticsTableRow;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;

public class StatisticsPanel extends JPanel
{
   private static final long serialVersionUID = -3937351898121564812L;

   private JPanel tablePanel;
   private JPanel wordPanel;
   private JPanel horizontalPanel;
   private Translator translator;

   public StatisticsPanel(App app, Common common)
   {
      translator = common.getTranslator();
      horizontalPanel = new JPanel();
      TrainLayout horizontalPanelLayout = new TrainLayout(horizontalPanel);
      horizontalPanel.setLayout(horizontalPanelLayout);

      tablePanel = new JPanel();
      tablePanel.setLayout(new TotemLayout(tablePanel));
      tablePanel.setOpaque(true);
      tablePanel.setBackground(app.appColors.statistics.getPanelBackground());
      tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

      wordPanel = new JPanel();
      BullsEyeLayout wordPanelLayout = new BullsEyeLayout(wordPanel);
      wordPanel.setLayout(wordPanelLayout);
      wordPanel.setMinimumSize(new Dimension(500, 160));
      wordPanel.setMaximumSize(new Dimension(700, 800));
      wordPanel.setOpaque(true);
      wordPanel.setBackground(app.appColors.statistics.getSelectedBackground());

      horizontalPanel.add(tablePanel);
      horizontalPanel.add(wordPanel);

      add(horizontalPanel);
      setLayout(new BullsEyeLayout(this));
      setOpaque(true);
      setBackground(app.appColors.statistics.getPanelBackground());
   }

   public void setValues(App app, Common common, Model model)
   {
      tablePanel.removeAll();
      wordPanel.removeAll();

      JPanel titlePanel = new JPanel(new FlowLayout());
      titlePanel.setOpaque(false);
      titlePanel.setBackground(app.appColors.getTransparent());
      titlePanel.setMinimumSize(new Dimension(580, 50));
      titlePanel.setMaximumSize(new Dimension(580, 50));

      JLabel title = new JLabel(
            translator.realisticTranslate(app, Translation.TRAININGSUEBERSICHT));
      title.setForeground(app.appColors.statistics.getTextForeground());
      title.setFont(app.appFonts.germanFont.deriveFont(30F));
      titlePanel.add(title);

      StatisticsTable table = new StatisticsTable(app, common, model.data.findStatisticsModel(app, common));

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
                        statisticsTableRow.getJListHtoD(app, common));
                  scroller.setOpaque(true);
                  scroller.setBackground(
                        app.appColors.statistics.getSelectedBackground());
                  scroller.setBorder(BorderFactory.createEmptyBorder());
                  scroller.getViewport().setOpaque(true);
                  scroller.getViewport().setBackground(
                        app.appColors.statistics.getSelectedBackground());
                  scroller.setViewportBorder(BorderFactory.createEmptyBorder());
                  wordPanel.add(scroller);
               }
               else if (column == 2)
               {
                  StatisticsTableRow statisticsTableRow = ((StatisticsTableRow) table
                        .getValueAt(table.getSelectedRow(), 2));
                  JScrollPane scroller = new JScrollPane(
                        statisticsTableRow.getJListDtoH(app, common));
                  scroller.setOpaque(true);
                  scroller.setBackground(
                        app.appColors.statistics.getSelectedBackground());
                  scroller.setBorder(BorderFactory.createEmptyBorder());
                  scroller.getViewport().setOpaque(true);
                  scroller.getViewport().setBackground(
                        app.appColors.statistics.getSelectedBackground());
                  scroller.setViewportBorder(BorderFactory.createEmptyBorder());
                  wordPanel.add(scroller);
               }
               wordPanel.validate();
               wordPanel.repaint();
            }
         }
      });

      JScrollPane scroller = new JScrollPane(table);
      scroller.setOpaque(false);
      scroller.setBackground(app.appColors.getTransparent());
      scroller.setBorder(BorderFactory.createEmptyBorder());
      scroller.getViewport().setOpaque(false);
      scroller.getViewport().setBackground(app.appColors.getTransparent());
      scroller.setViewportBorder(BorderFactory.createEmptyBorder());
      scroller.setBackground(app.appColors.getDarkRed());

      tablePanel.add(titlePanel);
      tablePanel.add(scroller);
   }
}
