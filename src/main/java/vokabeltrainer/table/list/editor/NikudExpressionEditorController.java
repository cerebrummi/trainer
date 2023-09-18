package vokabeltrainer.table.list.editor;

import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import vokabeltrainer.common.ImageData;
import vokabeltrainer.common.Settings;

public class NikudExpressionEditorController implements NikudExpressionEditorControllerConnector
{
   private NikudExpressionEditorView nikudExpressionEditorDialog;
   private TextExpressionEditorView textExpressionEditorDialog;
   
   public NikudExpressionEditorController()
   {
      nikudExpressionEditorDialog = new NikudExpressionEditorView(this);
      textExpressionEditorDialog = new TextExpressionEditorView(this);
   }

   public NikudExpressionEditorView getNikudExpressionEditorDialog()
   {
      return nikudExpressionEditorDialog;
   }
   
   public TextExpressionEditorView getTextExpressionEditorDialog()
   {
	   return textExpressionEditorDialog;
   }

   @Override
   public void chooseImageForExpression() 
   {
	   JFileChooser imageChooser = new JFileChooser(
	            Settings.getExpressionPath());
	   		imageChooser.setAcceptAllFileFilterUsed(false);
	   		imageChooser.setFileFilter(new FileNameExtensionFilter("jpeg-Bild", "jpeg"));
	   		imageChooser.setFileFilter(new FileNameExtensionFilter("jpg-Bild", "jpg"));
	   		imageChooser.setFileFilter(new FileNameExtensionFilter("png-Bild", "png"));
	   		imageChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

	   int choice = imageChooser.showDialog(nikudExpressionEditorDialog, "Wählen Sie ein Bild aus."); // TODO translation !!!

	   if (JFileChooser.APPROVE_OPTION == choice)
	   {
	      String image = imageChooser.getSelectedFile().getPath();
	      UUID uuid = nikudExpressionEditorDialog.getExpression().getUuid();
          ImageData.saveImage(image, uuid);
          nikudExpressionEditorDialog.getImageButton().setIcon(new ImageIcon(ImageData.loadImage(uuid)));
          nikudExpressionEditorDialog.getImageButton().validate();
          nikudExpressionEditorDialog.getImageButton().repaint();
	  }
  }

   @Override
   public void deleteImageForExpression() 
   {
	   int answer = JOptionPane.showConfirmDialog(nikudExpressionEditorDialog, "Wollen Sie das Bild wirklich löschen?"); // TODO translation !!!
	   if(answer == 0)
	   {
		   ImageData.deleteImage(nikudExpressionEditorDialog.getExpression().getUuid());
		   nikudExpressionEditorDialog.getImageButton().setIcon(null);
	       nikudExpressionEditorDialog.getImageButton().validate();
	       nikudExpressionEditorDialog.getImageButton().repaint();
	   }
   }
}
