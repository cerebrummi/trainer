package vokabeltrainer.table;

import java.awt.Component;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.types.Expression;

public class ExpressionCellRenderer2 implements TableCellRenderer
{
   private App app;
   private Model model;
   private JPanel content;
   private JLabel infos;

   public ExpressionCellRenderer2(App app, Model model)
   {
      this.app = app;
      this.model = model;
      content = new JPanel();
      TotemLayout layout = new TotemLayout(content, 15);
      content.setLayout(layout);
      infos = new JLabel(new ImageIcon(app.appImages.getIcon_bulb()));
      infos.setSize(60, 60);
      infos.setBorder(BorderFactory.createEmptyBorder());

      content.add(infos);
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
         boolean isSelected, boolean hasFocus, int row, int column)
   {
      Expression expression = (Expression) value;
      UUID uuid = expression.getUuid();
      if (model.imageData.isImageForExpressionAvailable(uuid))
      {
         infos.setIcon(new ImageIcon(app.appImages.getIcon_bulb_on()));
      }
      else
      {
         infos.setIcon(new ImageIcon(app.appImages.getIcon_bulb()));
      }

      if (isSelected)
      {
         content.setBorder(BorderFactory
               .createLineBorder(app.appColors.getMediumGreen(), 3));
      }
      else
      {
         content.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
      }

      if (row % 2 == 1)
      {
         content.setBackground(app.appColors.table.getRow1());
      }
      else
      {
         content.setBackground(app.appColors.table.getRow2());
      }

      return content;
   }

}
