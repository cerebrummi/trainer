package vokabeltrainer.panels.statistics;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.ImageData;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.LanguageDirection;

public class ListImageRow extends JPanel
{
	private static final long serialVersionUID = -5001282133186284698L;
	
	private JButton imageButton;
	private Expression expression;
	
	public ListImageRow(Expression expression, Direction language) 
	{
		setLayout(new FlowLayout());
	    setOpaque(true);
	    setBackground(ApplicationColors.getWhite());
	    
	    imageButton = new JButton(Common.getTranslator().realisticTranslate(Translation.BILD));
	    this.expression = expression;
	    
	    if(ImageData.isImageForExpressionAvailable(expression.getUuid()))
	    {
	    	add(imageButton);
	    }
	    if(Direction.OWN_TO_NEW.equals(language))
	    {
	    	add(new JLabel(expression.getWordGermanForStatistics(Direction.OWN_TO_NEW)));
	    }
	    else
	    {
	    	add(new JLabel(expression.getWordGermanForStatistics(Direction.NEW_TO_OWN)));
	    }
	    
	    initController();
	}

	private void initController() 
	{		
		imageButton.addActionListener(event -> {
			JOptionPane.showMessageDialog(imageButton, null, null, JOptionPane.PLAIN_MESSAGE, new ImageIcon(ImageData.loadImageOriginal(expression.getUuid())));
		});
	}

}
