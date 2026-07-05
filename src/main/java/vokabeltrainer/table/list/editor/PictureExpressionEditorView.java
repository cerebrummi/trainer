package vokabeltrainer.table.list.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.ApplicationFonts;
import vokabeltrainer.common.ApplicationImages;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.common.colors.InputColors;
import vokabeltrainer.table.EscapeAction;
import vokabeltrainer.table.list.editor.images.ImageDropHandler;
import vokabeltrainer.table.list.editor.images.ImageItem;
import vokabeltrainer.table.list.editor.images.ImageItemRenderer;
import vokabeltrainer.tonionlayout.BullsEyeLayout;
import vokabeltrainer.tonionlayout.ExpanderLayout;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.types.Expression;

public class PictureExpressionEditorView extends JDialog
{
   private static final long serialVersionUID = 5853498340870217732L;

   private Expression expression;

   private JPanel outerLayout;

   private Dimension screenSize;

   private JList<ImageItem> imageList;

   private DefaultListModel<ImageItem> model;

   private ImageItemRenderer renderer;

   private JPanel imagePanel;

   private ImageDropHandler dropHandler;

   public PictureExpressionEditorView(
         NikudExpressionEditorControllerConnector connector)
   {
      super(Common.getjFrame(), Settings.getWindowTitle()
            + " Bilder hineinziehen und fallen lassen. Rechtsklick auf jedes Bild öffnet Menü für Bild. Links Doppelklick öffnet Bild.",
            Dialog.ModalityType.APPLICATION_MODAL);
      setResizable(true);
      screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setSize(Math.min(screenSize.width - 60, 1320),
            Math.min(screenSize.height - 60, 825));

      outerLayout = new JPanel();
      outerLayout.setBackground(ApplicationColors.getShadyBlue());
      outerLayout.setBorder(BorderFactory
            .createLineBorder(InputColors.getEditorBackground(), 15, false));
      outerLayout.setLayout(new TotemLayout(outerLayout, 15));

      outerLayout.add(initImagePanel());

      getContentPane().add(new JScrollPane(outerLayout));

      initController();
   }

   private Component initImagePanel()
   {
      imagePanel = new JPanel()
      {
         @Override
         public void paintComponent(Graphics g)
         {
            super.paintComponent(g);
            g.drawImage(ApplicationImages.getScroll(), 0, 0, this);
         }
      };
      ExpanderLayout layout = new ExpanderLayout(imagePanel);
      imagePanel.setLayout(layout);
      imagePanel.setOpaque(true);
      imagePanel.setBackground(Color.WHITE);

      model = new DefaultListModel<>();
      imageList = new JList<>(model);
      imageList.setOpaque(false);
      imageList.setMinimumSize(new Dimension(1200, 400));
      imageList.setMaximumSize(new Dimension(1600, 600));

      imageList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
      imageList.setVisibleRowCount(-1);
      imageList.setFixedCellWidth(254);
      imageList.setFixedCellHeight(254);

      imagePanel.add(imageList);

      return imagePanel;
   }

   private void initController()
   {
      renderer = new ImageItemRenderer();
      imageList.setCellRenderer(renderer);

      imageList.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent e)
         {
            if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e))
            {
               int index = imageList.locationToIndex(e.getPoint());

               if (index < 0)
               {
                  return;
               }

               Rectangle bounds = imageList.getCellBounds(index, index);

               if (bounds == null || !bounds.contains(e.getPoint()))
               {
                  return;
               }

               ImageItem item = imageList.getModel().getElementAt(index);

               SwingUtilities.invokeLater(() -> {
                  System.setProperty("java.awt.headless", "true");
                  JDialog dialog = new JDialog(Common.getjFrame(),
                        Settings.getWindowTitle(),
                        Dialog.ModalityType.APPLICATION_MODAL);
                  JPanel panelinside = new JPanel();
                  BullsEyeLayout layoutBullsEye = new BullsEyeLayout(
                        panelinside);
                  panelinside.setLayout(layoutBullsEye);

                  int maxWidth = Toolkit.getDefaultToolkit()
                        .getScreenSize().width - 60;
                  int maxHeight = Toolkit.getDefaultToolkit()
                        .getScreenSize().height - 160;
                  maxWidth = Math.min(maxWidth, item.getImage().getWidth());
                  maxHeight = Math.min(maxHeight, item.getImage().getHeight());

                  JButton button = new JButton(
                        createScreenSize(item.getImage(), maxWidth, maxHeight));
                  button.addActionListener(_ -> dialog.dispose());

                  panelinside.add(button);
                  dialog.getContentPane().add(new JScrollPane(panelinside));

                  dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                  dialog.pack();
                  dialog.setLocationRelativeTo(null);
                  dialog.setVisible(true);
               });

            }
         }
      });

      imageList.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent e)
         {
            if (SwingUtilities.isRightMouseButton(e))
            {
               int index = imageList.locationToIndex(e.getPoint());

               if (index < 0)
               {
                  return;
               }

               Rectangle bounds = imageList.getCellBounds(index, index);

               if (bounds == null || !bounds.contains(e.getPoint()))
               {
                  return;
               }

               ImageItem item = imageList.getModel().getElementAt(index);

               JPopupMenu menu = createImageContextMenu(index, item, bounds);

               menu.show(imageList, e.getX(), e.getY());

               e.consume();

               imageList.repaint(bounds);
            }
         }
      });

      getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE_KEY");
      getRootPane().getActionMap().put("ESCAPE_KEY", new EscapeAction(this));
   }

   private JPopupMenu createImageContextMenu(int index, ImageItem item,
         Rectangle bounds)
   {
      JPopupMenu menu = new JPopupMenu();

      JMenuItem saveItem = new JMenuItem("Bild speichern");
      saveItem.setFont(ApplicationFonts.getButtonFont());
      saveItem.addActionListener(_ -> {
         item.setChecked(true);
         // SwingWorker saveImage
         imageList.repaint(bounds);
      });

      JMenuItem deleteItem = new JMenuItem("Bild löschen");
      deleteItem.setFont(ApplicationFonts.getButtonFont());
      deleteItem.addActionListener(_ -> {
         item.setChecked(false);
         // SwingWorker deleteImage
         imageList.repaint(bounds);
      });

      JMenuItem rotateRight = new JMenuItem("90° nach rechts drehen");
      rotateRight.addActionListener(_ -> {
         item.setImage(rotate90(item, true));
         imageList.repaint(bounds);
      });

      JMenuItem rotateLeft = new JMenuItem("90° nach links drehen");
      rotateLeft.addActionListener(_ -> {
         item.setImage(rotate90(item, false));
         imageList.repaint(bounds);
      });

      JMenuItem removeFromListItem = new JMenuItem("Bild entfernen");
      removeFromListItem.setFont(ApplicationFonts.getButtonFont());
      removeFromListItem.addActionListener(_ -> {
         DefaultListModel<ImageItem> model = (DefaultListModel<ImageItem>) imageList
               .getModel();
         model.remove(index);
         if (item.isChecked())
         {
            // SwingWorker deleteImage
         }
         imageList.repaint();
      });

      menu.add(saveItem);
      menu.add(deleteItem);
      menu.addSeparator();
      if (!item.isChecked())
      {
         menu.add(rotateRight);
         menu.add(rotateLeft);
         menu.addSeparator();
      }
      menu.add(removeFromListItem);

      return menu;
   }

   public void disposeDialog()
   {
      this.dispose();
   }

   public void setExpression(Expression expression)
   {
      this.expression = expression;
      model = new DefaultListModel<>();
      imageList = new JList<>(model);
      imageList.setOpaque(false);
      imageList.setMinimumSize(new Dimension(1200, 400));
      imageList.setMaximumSize(new Dimension(1600, 600));

      imageList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
      imageList.setVisibleRowCount(-1);
      imageList.setFixedCellWidth(254);
      imageList.setFixedCellHeight(254);

      imagePanel.removeAll();
      imagePanel.add(imageList);
      dropHandler = new ImageDropHandler(
            this.expression.getUuid(), model);
      imageList.setTransferHandler(dropHandler);

      initController();
   }

   private ImageIcon createScreenSize(BufferedImage image, int maxWidth,
         int maxHeight)
   {
      double scale = Math.min((double) maxWidth / image.getWidth(),
            (double) maxHeight / image.getHeight());

      int width = (int) (image.getWidth() * scale);
      int height = (int) (image.getHeight() * scale);

      Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      return new ImageIcon(scaled);
   }

   private BufferedImage rotate90(ImageItem source, boolean clockwise)
   {
      try
      {
         String extension = getFileExtension(source.getImagePath());
         String format = extension.equalsIgnoreCase("jpeg") ? "jpg" : extension;

         int width = source.getImage().getWidth();
         int height = source.getImage().getHeight();

         boolean jpg = format.equalsIgnoreCase("jpg");

         int imageType = jpg ? BufferedImage.TYPE_INT_RGB
               : BufferedImage.TYPE_INT_ARGB;

         BufferedImage target = new BufferedImage(height, width, imageType);

         Graphics2D g2 = target.createGraphics();

         g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
               RenderingHints.VALUE_INTERPOLATION_BICUBIC);

         if (jpg)
         {
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, target.getWidth(), target.getHeight());
         }

         if (clockwise)
         {
            g2.translate(height, 0);
            g2.rotate(Math.PI / 2.0);
         }
         else
         {
            g2.translate(0, width);
            g2.rotate(-Math.PI / 2.0);
         }

         g2.drawImage(source.getImage(), 0, 0, null);
         g2.dispose();

         return target;
      }
      catch (Exception e)
      {
         JOptionPane.showMessageDialog(imageList,
               "Bild konnte nicht gedreht werden:\n" + e.getMessage(), "Fehler",
               JOptionPane.ERROR_MESSAGE);
      }
      return source.getImage();
   }

   private String getFileExtension(Path path) throws IOException
   {
      String fileName = path.getFileName().toString();

      int dot = fileName.lastIndexOf('.');

      if (dot < 0 || dot == fileName.length() - 1)
      {
         throw new IOException("Datei hat keine Bild-Endung.");
      }

      return fileName.substring(dot + 1).toLowerCase();
   }
}
