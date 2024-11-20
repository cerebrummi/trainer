package vokabeltrainer.editing;

import java.io.InputStream;

public enum SwedishLetter implements Letter
{
   A(" 0041_se", "\u0041", "oah"), B(" 0042_se", "\u0042", "bej"), C(" 0043_se",
         "\u0043", "cija"), D(" 0044_se", "\u0044", "dija"), E(" 0045_se",
               "\u0045", "ija"), F(" 0046_se", "\u0046", "eff"), G(" 0047_se",
                     "\u0047", "gija"), H(" 0048_se", "\u0048", "hoa"), I(
                           " 0049_se", "\u0049",
                           "ij"), J(" 004a_se", "\u004a", "ji"), K(" 004b_se",
                                 "\u004b",
                                 "koa"), L(" 004c_se", "\u004c", "ell"), M(
                                       " 004d_se", "\u004d",
                                       "emm"), N(" 004e_se", "\u004e",
                                             "enn"), O(" 004f_se", "\u004f",
                                                   "oh"), P(" 0050_se",
                                                         "\u0050",
                                                         "pija"), Q(" 0051_se",
                                                               "\u0051",
                                                               "kü"), R(
                                                                     " 0052_se",
                                                                     "\u0052",
                                                                     "ell"), S(
                                                                           " 0053_se",
                                                                           "\u0053",
                                                                           "ess"), T(
                                                                                 " 0054_se",
                                                                                 "\u0054",
                                                                                 "tia"), U(
                                                                                       " 0055_se",
                                                                                       "\u0055",
                                                                                       "ü"), V(
                                                                                             " 0056_se",
                                                                                             "\u0056",
                                                                                             "via"), W(
                                                                                                   " 0057_se",
                                                                                                   "\u0057",
                                                                                                   "dübbelvia"), X(
                                                                                                         " 0058_se",
                                                                                                         "\u0058",
                                                                                                         "ex"), Y(
                                                                                                               " 0059_se",
                                                                                                               "\u0059",
                                                                                                               "ij"), Z(
                                                                                                                     " 005a_se",
                                                                                                                     "\u005a",
                                                                                                                     "zä-ta"), ARING(
                                                                                                                           " 00C5_se",
                                                                                                                           "\u00C5",
                                                                                                                           "oar"), AE(
                                                                                                                                 " 00c4_se",
                                                                                                                                 "\u00c4",
                                                                                                                                 "är"), OE(
                                                                                                                                       " 00d6_se",
                                                                                                                                       "\u00d6",
                                                                                                                                       "ör"), a(
                                                                                                                                             " 0061_se",
                                                                                                                                             "\u0061",
                                                                                                                                             "oah"), b(
                                                                                                                                                   " 0062_se",
                                                                                                                                                   "\u0062",
                                                                                                                                                   "bej"), c(
                                                                                                                                                         " 0063_se",
                                                                                                                                                         "\u0063",
                                                                                                                                                         "cija"), d(
                                                                                                                                                               " 0064_se",
                                                                                                                                                               "\u0064",
                                                                                                                                                               "dija"), e(
                                                                                                                                                                     " 0065_se",
                                                                                                                                                                     "\u0065",
                                                                                                                                                                     "ija"), f(
                                                                                                                                                                           " 0066_se",
                                                                                                                                                                           "\u0066",
                                                                                                                                                                           "eff"), g(
                                                                                                                                                                                 " 0067_se",
                                                                                                                                                                                 "\u0067",
                                                                                                                                                                                 "gija"), h(
                                                                                                                                                                                       " 0068_se",
                                                                                                                                                                                       "\u0068",
                                                                                                                                                                                       "hoa"), i(
                                                                                                                                                                                             " 0069_se",
                                                                                                                                                                                             "\u0069",
                                                                                                                                                                                             "ij"), j(
                                                                                                                                                                                                   " 006a_se",
                                                                                                                                                                                                   "\u006a",
                                                                                                                                                                                                   "ji"), k(
                                                                                                                                                                                                         " 006b_se",
                                                                                                                                                                                                         "\u006b",
                                                                                                                                                                                                         "koa"), l(
                                                                                                                                                                                                               " 006c_se",
                                                                                                                                                                                                               "\u006c",
                                                                                                                                                                                                               "ell"), m(
                                                                                                                                                                                                                     " 006d_se",
                                                                                                                                                                                                                     "\u006d",
                                                                                                                                                                                                                     "emm"), n(
                                                                                                                                                                                                                           " 006e_se",
                                                                                                                                                                                                                           "\u006e",
                                                                                                                                                                                                                           "enn"), o(
                                                                                                                                                                                                                                 " 006f_se",
                                                                                                                                                                                                                                 "\u006f",
                                                                                                                                                                                                                                 "oh"), p(
                                                                                                                                                                                                                                       " 0070_se",
                                                                                                                                                                                                                                       "\u0070",
                                                                                                                                                                                                                                       "pija"), q(
                                                                                                                                                                                                                                             " 0071_se",
                                                                                                                                                                                                                                             "\u0071",
                                                                                                                                                                                                                                             "kü"), r(
                                                                                                                                                                                                                                                   " 0072_se",
                                                                                                                                                                                                                                                   "\u0072",
                                                                                                                                                                                                                                                   "ell"), s(
                                                                                                                                                                                                                                                         " 0073_se",
                                                                                                                                                                                                                                                         "\u0073",
                                                                                                                                                                                                                                                         "ess"), t(
                                                                                                                                                                                                                                                               " 0074_se",
                                                                                                                                                                                                                                                               "\u0074",
                                                                                                                                                                                                                                                               "tia"), u(
                                                                                                                                                                                                                                                                     " 0075_se",
                                                                                                                                                                                                                                                                     "\u0075",
                                                                                                                                                                                                                                                                     "ü"), v(
                                                                                                                                                                                                                                                                           " 0076_se",
                                                                                                                                                                                                                                                                           "\u0076",
                                                                                                                                                                                                                                                                           "via"), w(
                                                                                                                                                                                                                                                                                 " 0077_se",
                                                                                                                                                                                                                                                                                 "\u0077",
                                                                                                                                                                                                                                                                                 "dübbelvia"), x(
                                                                                                                                                                                                                                                                                       " 0078_se",
                                                                                                                                                                                                                                                                                       "\u0078",
                                                                                                                                                                                                                                                                                       "ex"), y(
                                                                                                                                                                                                                                                                                             " 0079_se",
                                                                                                                                                                                                                                                                                             "\u0079",
                                                                                                                                                                                                                                                                                             "ij"), z(
                                                                                                                                                                                                                                                                                                   " 007a_se",
                                                                                                                                                                                                                                                                                                   "\u007a",
                                                                                                                                                                                                                                                                                                   "zä-ta"), aring(
                                                                                                                                                                                                                                                                                                         " 00E5_se",
                                                                                                                                                                                                                                                                                                         "\u00E5",
                                                                                                                                                                                                                                                                                                         "oar"), ae(
                                                                                                                                                                                                                                                                                                               " 00e4_se",
                                                                                                                                                                                                                                                                                                               "\u00e4",
                                                                                                                                                                                                                                                                                                               "är"), oe(
                                                                                                                                                                                                                                                                                                                     " 00f6_se",
                                                                                                                                                                                                                                                                                                                     "\u00f6",
                                                                                                                                                                                                                                                                                                                     "ör"), SPACE(
                                                                                                                                                                                                                                                                                                                           " 0020_se",
                                                                                                                                                                                                                                                                                                                           "\u0020",
                                                                                                                                                                                                                                                                                                                           ""), NEWSPACE(
                                                                                                                                                                                                                                                                                                                                 " 00A0_se",
                                                                                                                                                                                                                                                                                                                                 "\u00A0",
                                                                                                                                                                                                                                                                                                                                 "");

   private String code;
   private String unicode;
   private String pronunciation;
   private byte[] sound;

   SwedishLetter(String code, String unicode, String pronunciation)
   {
      this.code = code;
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
