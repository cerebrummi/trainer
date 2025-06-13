package vokabeltrainer.editing;

public enum GermanLetter
      implements
      Letter
{
   A(
         " 0041_de",
         "\u0041"),
   B(
         " 0042_de",
         "\u0042"),
   C(
         " 0043_de",
         "\u0043"),
   D(
         " 0044_de",
         "\u0044"),
   E(
         " 0045_de",
         "\u0045"),
   F(
         " 0046_de",
         "\u0046"),
   G(
         " 0047_de",
         "\u0047"),
   H(
         " 0048_de",
         "\u0048"),
   I(
         " 0049_de",
         "\u0049"),
   J(
         " 004a_de",
         "\u004a"),
   K(
         " 004b_de",
         "\u004b"),
   L(
         " 004c_de",
         "\u004c"),
   M(
         " 004d_de",
         "\u004d"),
   N(
         " 004e_de",
         "\u004e"),
   O(
         " 004f_de",
         "\u004f"),
   P(
         " 0050_de",
         "\u0050"),
   Q(
         " 0051_de",
         "\u0051"),
   R(
         " 0052_de",
         "\u0052"),
   S(
         " 0053_de",
         "\u0053"),
   T(
         " 0054_de",
         "\u0054"),
   U(
         " 0055_de",
         "\u0055"),
   V(
         " 0056_de",
         "\u0056"),
   W(
         " 0057_de",
         "\u0057"),
   X(
         " 0058_de",
         "\u0058"),
   Y(
         " 0059_de",
         "\u0059"),
   Z(
         " 005a_de",
         "\u005a"),
   UE(
         " 00dc_de",
         "\u00dc"),
   AE(
         " 00c4_de",
         "\u00c4"),
   OE(
         " 00d6_de",
         "\u00d6"),
   a(
         " 0061_de",
         "\u0061"),
   b(
         " 0062_de",
         "\u0062"),
   c(
         " 0063_de",
         "\u0063"),
   d(
         " 0064_de",
         "\u0064"),
   e(
         " 0065_de",
         "\u0065"),
   f(
         " 0066_de",
         "\u0066"),
   g(
         " 0067_de",
         "\u0067"),
   h(
         " 0068_de",
         "\u0068"),
   i(
         " 0069_de",
         "\u0069"
   ),
   j(
         " 006a_de",
         "\u006a"
   ),
   k(
         " 006b_de",
         "\u006b"
   ),
   l(
         " 006c_de",
         "\u006c"
   ),
   m(
         " 006d_de",
         "\u006d"
   ),
   n(
         " 006e_de",
         "\u006e"
   ),
   o(
         " 006f_de",
         "\u006f"
   ),
   p(
         " 0070_de",
         "\u0070"
   ),
   q(
         " 0071_de",
         "\u0071"
   ),
   r(
         " 0072_de",
         "\u0072"
   ),
   s(
         " 0073_de",
         "\u0073"
   ),
   t(
         " 0074_de",
         "\u0074"
   ),
   u(
         " 0075_de",
         "\u0075"
   ),
   v(
         " 0076_de",
         "\u0076"
   ),
   w(
         " 0077_de",
         "\u0077"
   ),
   x(
         " 0078_de",
         "\u0078"
   ),
   y(
         " 0079_de",
         "\u0079"
   ),
   z(
         " 007a_de",
         "\u007a"
   ),
   ue(
         " 00fc_de",
         "\u00fc"
   ),
   ae(
         " 00e4_de",
         "\u00e4"
   ),
   oe(
         " 00f6_de",
         "\u00f6"
   ),
   sz(
         " 00df_de",
         "\u00df"
   ),
   SPACE(
         " 0020_de",
         "\u0020"
   ), 
   NEWSPACE(
         " 00A0_de",
         "\u00A0"
         );

   private String code;
   private String unicode;

   GermanLetter(String code, String unicode)
   {
      this.code = code.toUpperCase();
      this.unicode = unicode.toUpperCase();
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
      return GermanLetter.NEWSPACE == this;
   }

   @Override
   public boolean isSpace()
   {
      return GermanLetter.SPACE == this;
   }

   @Override
   public LetterType isType()
   {
      return LetterType.GERMAN;
   }

   @Override
   public Letter getNewspace()
   {
      return GermanLetter.NEWSPACE;
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
}
