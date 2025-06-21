package vokabeltrainer.editing;

public enum GermanLetter
      implements
      Letter
{
   A(
         " 0041",
         "\u0041"),
   B(
         " 0042",
         "\u0042"),
   C(
         " 0043",
         "\u0043"),
   D(
         " 0044",
         "\u0044"),
   E(
         " 0045",
         "\u0045"),
   F(
         " 0046",
         "\u0046"),
   G(
         " 0047",
         "\u0047"),
   H(
         " 0048",
         "\u0048"),
   I(
         " 0049",
         "\u0049"),
   J(
         " 004a",
         "\u004A"),
   K(
         " 004b",
         "\u004B"),
   L(
         " 004c",
         "\u004C"),
   M(
         " 004d",
         "\u004D"),
   N(
         " 004e",
         "\u004E"),
   O(
         " 004f",
         "\u004F"),
   P(
         " 0050",
         "\u0050"),
   Q(
         " 0051",
         "\u0051"),
   R(
         " 0052",
         "\u0052"),
   S(
         " 0053",
         "\u0053"),
   T(
         " 0054",
         "\u0054"),
   U(
         " 0055",
         "\u0055"),
   V(
         " 0056",
         "\u0056"),
   W(
         " 0057",
         "\u0057"),
   X(
         " 0058",
         "\u0058"),
   Y(
         " 0059",
         "\u0059"),
   Z(
         " 005a",
         "\u005A"),
   UE(
         " 00dc",
         "\u00DC"),
   AE(
         " 00c4",
         "\u00C4"),
   OE(
         " 00d6",
         "\u00D6"),
   a(
         " 0061",
         "\u0061"),
   b(
         " 0062",
         "\u0062"),
   c(
         " 0063",
         "\u0063"),
   d(
         " 0064",
         "\u0064"),
   e(
         " 0065",
         "\u0065"),
   f(
         " 0066",
         "\u0066"),
   g(
         " 0067",
         "\u0067"),
   h(
         " 0068",
         "\u0068"),
   i(
         " 0069",
         "\u0069"
   ),
   j(
         " 006a",
         "\u006A"
   ),
   k(
         " 006b",
         "\u006B"
   ),
   l(
         " 006c",
         "\u006C"
   ),
   m(
         " 006d",
         "\u006D"
   ),
   n(
         " 006e",
         "\u006E"
   ),
   o(
         " 006f",
         "\u006F"
   ),
   p(
         " 0070",
         "\u0070"
   ),
   q(
         " 0071",
         "\u0071"
   ),
   r(
         " 0072",
         "\u0072"
   ),
   s(
         " 0073",
         "\u0073"
   ),
   t(
         " 0074",
         "\u0074"
   ),
   u(
         " 0075",
         "\u0075"
   ),
   v(
         " 0076",
         "\u0076"
   ),
   w(
         " 0077",
         "\u0077"
   ),
   x(
         " 0078",
         "\u0078"
   ),
   y(
         " 0079",
         "\u0079"
   ),
   z(
         " 007a",
         "\u007A"
   ),
   ue(
         " 00fc",
         "\u00FC"
   ),
   ae(
         " 00e4",
         "\u00E4"
   ),
   oe(
         " 00f6",
         "\u00F6"
   ),
   sz(
         " 00df",
         "\u00DF"
   ),
   SPACE(
         " 0020",
         "\u0020"
   ), 
   NEWSPACE(
         " 00A0",
         "\u00A0"
         );

   private String code;
   private String unicode;

   GermanLetter(String code, String unicode)
   {
      this.code = code.toUpperCase() + "_DE";
      this.unicode = unicode;
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
