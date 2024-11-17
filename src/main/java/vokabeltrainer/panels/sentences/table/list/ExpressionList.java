package vokabeltrainer.panels.sentences.table.list;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.KeyStroke;

import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.types.LanguageDirection;

public class ExpressionList extends JList<String>
{

   private static final long serialVersionUID = -8585511215166225268L;
   
   private boolean withLock;

   public ExpressionList(LanguageDirection language)
   {
      setSelectionModel(new ExpressionListSelectionModel());
      setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
      setVisibleRowCount(3);
      getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false),
            new Object());
      getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false),
            new Object());
      getActionMap().get("clearSelection").setEnabled(true);

      setCellRenderer(new ExpressionListCellRenderer(language));
   }
   
   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      if (withLock)
      {
         g.drawImage(ApplicationImages.getLock(), 0, 0, this);
      }
   }
   
   public void setLock(boolean lock)
   {
      this.withLock = lock;
   }
}
