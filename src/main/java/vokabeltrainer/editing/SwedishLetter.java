package vokabeltrainer.editing;

import java.io.InputStream;

public enum SwedishLetter implements Letter
{
   A(" 0041", "\u0041", "oah"), 
   B(" 0042", "\u0042", "bej"), 
   C(" 0043", "\u0043", "cija"), 
   D(" 0044", "\u0044", "dija"), 
   E(" 0045", "\u0045", "ija"), 
   F(" 0046", "\u0046", "eff"), 
   G(" 0047", "\u0047", "gija"), 
   H(" 0048", "\u0048", "hoa"), 
   I(" 0049", "\u0049", "ij"), 
   J(" 004a", "\u004A", "ji"), 
   K(" 004b", "\u004B", "koa"), 
   L(" 004c", "\u004C", "ell"), 
   M(" 004d", "\u004D", "emm"), 
   N(" 004e", "\u004E", "enn"), 
   O(" 004f", "\u004F", "oh"), 
   P(" 0050", "\u0050", "pija"), 
   Q(" 0051", "\u0051", "kü"), 
   R(" 0052", "\u0052", "ell"), 
   S(" 0053", "\u0053", "ess"), 
   T(" 0054", "\u0054", "tia"), 
   U(" 0055", "\u0055", "ü"), 
   V(" 0056", "\u0056", "via"), 
   W(" 0057", "\u0057", "dübbelvia"), 
   X(" 0058", "\u0058", "ex"), 
   Y(" 0059", "\u0059", "ij"), 
   Z(" 005a", "\u005A", "zä-ta"), 
   ARING(" 00C5", "\u00C5", "oar"), 
   AE(" 00c4", "\u00C4", "är"), 
   OE(" 00d6", "\u00D6", "ör"), 
   a(" 0061", "\u0061", "oah"), 
   b(" 0062", "\u0062", "bej"), 
   c(" 0063", "\u0063", "cija"), 
   d(" 0064", "\u0064", "dija"), 
   e(" 0065", "\u0065", "ija"), 
   f(" 0066", "\u0066", "eff"), 
   g(" 0067", "\u0067", "gija"), 
   h(" 0068", "\u0068", "hoa"),
   i(" 0069", "\u0069", "ij"), 
   j(" 006a", "\u006A", "ji"), 
   k(" 006b", "\u006B", "koa"), 
   l(" 006c", "\u006C", "ell"), 
   m(" 006d", "\u006D", "emm"), 
   n(" 006e", "\u006E", "enn"), 
   o(" 006f", "\u006F", "oh"), 
   p(" 0070", "\u0070", "pija"), 
   q(" 0071", "\u0071", "kü"), 
   r(" 0072", "\u0072", "er"), 
   s(" 0073", "\u0073", "ess"), 
   t(" 0074", "\u0074", "tia"), 
   u(" 0075", "\u0075", "ü"), 
   v(" 0076", "\u0076", "via"), 
   w(" 0077", "\u0077", "dübbelvia"), 
   x(" 0078", "\u0078", "ex"), 
   y(" 0079", "\u0079", "ij"), 
   z(" 007a", "\u007A", "zä-ta"), 
   aring(" 00E5", "\u00E5", "oar"), 
   ae(" 00e4", "\u00E4", "är"), 
   oe(" 00f6", "\u00F6", "ör"), 
   SPACE(" 0020", "\u0020", ""), 
   NEWSPACE(" 00A0", "\u00A0", "");

   private String code;
   private String unicode;
   private String pronunciation;
   private byte[] sound;

   SwedishLetter(String code, String unicode, String pronunciation)
   {
      this.code = code.toUpperCase() + "_SE";
      this.unicode = unicode;
      this.pronunciation = pronunciation;
   }

   @Override
   public String getCode()
   {
      return code;
   }

   @Override
   public String getUnicode()
   {
      return unicode;
   }

   @Override
   public boolean isNewspace()
   {
      return SwedishLetter.NEWSPACE == this;
   }

   @Override
   public boolean isSpace()
   {
      return SwedishLetter.SPACE == this;
   }

   @Override
   public LetterType isType()
   {
      return LetterType.SWEDISH;
   }

   @Override
   public Letter getNewspace()
   {
      return SwedishLetter.NEWSPACE;
   }

   @Override
   public int getPixelWidth()
   {
      return 18;
   }

   @Override
   public String getTranscript()
   {
      return name();
   }

   public String getPronunciation()
   {
      return pronunciation;
   }

   public byte[] getSound()
   {
      return sound;
   }

   public void setSound(InputStream in)
   {
      try
      {
         sound = in.readAllBytes();
      }
      catch (Exception e)
      {
         // nothing
      }
   }
}
