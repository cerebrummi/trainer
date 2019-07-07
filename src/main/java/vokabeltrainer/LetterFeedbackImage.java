package vokabeltrainer;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import vokabeltrainer.common.Main;
import vokabeltrainer.editing.HebrewLetter;

public class LetterFeedbackImage
{

   public static BufferedImage make(HebrewLetter letter, boolean okay)
   {
      BufferedImage finalImg = new BufferedImage(letter.getPixelWidth(), 70,
            BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = finalImg.createGraphics();
      g2d.setColor(Settings.getTexturedBackgroundColor());
      g2d.fillRect(0, 0, letter.getPixelWidth(), 70);
      g2d.setColor(Settings.getDarkRed());
      if (!okay)
      {
         Path2D.Float path = new Path2D.Float();
         double midX = ((double) letter.getPixelWidth()) / 2;
         path.moveTo(midX, 0D);
         path.lineTo(midX, 24D);
         path.lineTo(midX - 4D, 16D);
         g2d.draw(path);
         int[] xPoints = { (int) midX, 0, letter.getPixelWidth(),
               ((int) midX) + 1 };
         int[] yPoints = { 24, 16, 16, 24 };
         g2d.fillPolygon(xPoints, yPoints, 4);
      }
      g2d.setColor(Color.BLACK);
      g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
            RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
            RenderingHints.VALUE_COLOR_RENDER_QUALITY);
      g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
            RenderingHints.VALUE_DITHER_ENABLE);
      g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
            RenderingHints.VALUE_FRACTIONALMETRICS_ON);
      g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
      g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
            RenderingHints.VALUE_STROKE_PURE);
      g2d.setFont(Main.getHebrewFont(30F));
      FontMetrics fm = g2d.getFontMetrics();
      fm = g2d.getFontMetrics();
      g2d.drawString(letter.getUnicode(), 0, fm.getAscent() + 34);
      g2d.dispose();
      return finalImg;
   }

}
