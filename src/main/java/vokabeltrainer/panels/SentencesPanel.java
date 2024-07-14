package vokabeltrainer.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.panels.sentences.DragButton;
import vokabeltrainer.panels.sentences.DragGestureHandler;
import vokabeltrainer.panels.sentences.DropTargetHandler;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.types.Word;

public class SentencesPanel extends JPanel
{
   private static final long serialVersionUID = -3678970123371154151L;

   public SentencesPanel()
   {
      setLayout(new BorderLayout());

      add(new JScrollPane(initGui()), BorderLayout.CENTER);

      initController();
   }

   private void initController()
   {

   }

   private Component initGui()
   {
      JPanel panel = new JPanel();
      TotemLayout layout = new TotemLayout(panel);
      panel.setLayout(layout);
      panel.setBackground(ApplicationColors.getBackgroundGold());

      String[] items = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
            "Item 6", "Item 7", "Item 8", "Item 9", "Item 10" };

      JList<String> questionList = new JList<>(items);
      questionList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
      questionList.setVisibleRowCount(1);
      questionList.setMinimumSize(new Dimension(1800, 100));
      questionList.setMaximumSize(new Dimension(1800, 100));

      JList<String> resultList = new JList<>();
      resultList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
      resultList.setVisibleRowCount(1);
      resultList.setMinimumSize(new Dimension(1800, 100));
      resultList.setMaximumSize(new Dimension(1800, 100));

      DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(
            questionList, DnDConstants.ACTION_COPY_OR_MOVE,
            new DragGestureHandler(questionList));

      new DropTarget(resultList, DnDConstants.ACTION_COPY_OR_MOVE,
            new DropTargetHandler(resultList), true);

      panel.add(new JLabel("oben"));
      panel.add(questionList);
      panel.add(new JLabel("mitte"));
      panel.add(resultList);
      panel.add(new JLabel("unten"));

      return panel;
   }

}
