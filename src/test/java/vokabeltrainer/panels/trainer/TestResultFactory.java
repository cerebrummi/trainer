package vokabeltrainer.panels.trainer;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import vokabeltrainer.common.Main;
import vokabeltrainer.types.Expression;

public class TestResultFactory
{
  static String[] args = new String[3];
   
   @BeforeAll
   public static void loadStuff()
   {
      Main.main(args);
   }
   
   
   @Test
   public void testResultDtoH_Alignment()
   {
      Expression expression = new Expression(true);
      expression.setHebrew("בּוקר טוב");
      String answer = "בּקר טוב";
      
      Result result = Resultfactory.getResultDtoH(expression, answer);
      
      SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            JFrame window = new JFrame();
           JPanel panel = new JPanel(new FlowLayout());
           for(BufferedImage image: result.getLetterFeedbackImages())
           {
              panel.add(new JLabel(new ImageIcon(image)));
           }
            window.getContentPane().add(panel);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(1300, 735);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
         }
      });
   }
   
   
}
