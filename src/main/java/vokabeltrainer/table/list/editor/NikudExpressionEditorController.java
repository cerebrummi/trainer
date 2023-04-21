package vokabeltrainer.table.list.editor;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import vokabeltrainer.Settings;
import vokabeltrainer.common.ImageData;

public class NikudExpressionEditorController implements NikudExpressionEditorControllerConnector
{
   private NikudExpressionEditorView nikudExpressionEditorDialog;
   
   public NikudExpressionEditorController()
   {
      nikudExpressionEditorDialog = new NikudExpressionEditorView(this);
   }

   public NikudExpressionEditorView getNikudExpressionEditorDialog()
   {
      return nikudExpressionEditorDialog;
   }

   @Override
   public void chooseImageForExpression() 
   {
	   JFileChooser imageChooser = new JFileChooser(
	            Settings.getExpressionPath());
	   		//imageChooser.setAcceptAllFileFilterUsed(false);
	   		imageChooser.setFileFilter(new FileNameExtensionFilter("jpeg-Bild", "jpeg"));
	   		imageChooser.setFileFilter(new FileNameExtensionFilter("jpg-Bild", "jpg"));
	   		imageChooser.setFileFilter(new FileNameExtensionFilter("png-Bild", "png"));
	   		imageChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

	   int choice = imageChooser.showDialog(nikudExpressionEditorDialog, "Wählen Sie ein Bild aus."); // TODO translation !!!

	   if (JFileChooser.APPROVE_OPTION == choice)
	   {
	      String image = imageChooser.getSelectedFile().getPath();
          ImageData.saveImage(image, nikudExpressionEditorDialog.getExpression().getUuid());
	  }
  }
}
