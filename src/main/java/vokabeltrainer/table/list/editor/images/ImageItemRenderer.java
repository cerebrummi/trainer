package vokabeltrainer.table.list.editor.images;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import vokabeltrainer.ComponentTitledBorder;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationImages;

public class ImageItemRenderer extends JPanel
      implements ListCellRenderer<ImageItem>
{
   private static final long serialVersionUID = -2888266762824574533L;
   private JLabel label;

   public ImageItemRenderer()
   {
      label = new JLabel();
      add(label);
      label.setIconTextGap(5);
      setPreferredSize(new Dimension(210, 210));
      setMinimumSize(new Dimension(210, 210));
      setMaximumSize(new Dimension(210, 210));
      setOpaque(true);
      setBackground(ApplicationColors.getLightGrayGold());
   }

   @Override
   public Component getListCellRendererComponent(
         JList<? extends ImageItem> list, ImageItem value, int index,
         boolean isSelected, boolean cellHasFocus)
   {

      if (value != null)
      {
         label.setIcon(value.getThumbnail());
         JCheckBox box = new JCheckBox();

         if (value.isChecked())
         {
            box.setIcon(new ImageIcon(ApplicationImages.getOkaySaveIcon()));
         }
         else
         {
            box.setIcon(new ImageIcon(ApplicationImages.getCancel()));
         }

         TitledBorder tBorder = BorderFactory.createTitledBorder(
               BorderFactory.createLineBorder(ApplicationColors.gold, 2),
               value.toString());
         tBorder.setTitleColor(ApplicationColors.getShadyBlue());

         ComponentTitledBorder border = new ComponentTitledBorder(box, this,
               tBorder, 25);

         this.setBorder(border);
      }

      return this;
   }
}