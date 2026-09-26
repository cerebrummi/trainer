package vokabeltrainer.panels.sentences.table.list;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.Serial;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.KeyStroke;

import vokabeltrainer.common.main.App;

public class ExpressionList extends JList<String>
{
   @Serial
   private static final long serialVersionUID = -8585511215166225268L;

   private boolean withLock;
   private App app;

   public ExpressionList(App app)
   {
      this.app = app;
      setSelectionModel(new ExpressionListSelectionModel());
      setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
      setVisibleRowCount(3);
      getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false),
            new Object());
      getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false),
            new Object());
      getActionMap().get("clearSelection").setEnabled(true);

      setCellRenderer(new ExpressionListCellRenderer(app));
   }

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (withLock)
      {
         g.drawImage(app.appImages.getLock(), 0, 0, this);
      }
   }

   public void setLock(boolean lock)
   {
      this.withLock = lock;
   }
}
