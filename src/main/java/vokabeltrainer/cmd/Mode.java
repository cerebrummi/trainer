package vokabeltrainer.cmd;

public enum Mode
{
   LOCAL_ORIGINAL(false, false),
   LOCAL_MARIX(true, false),
   WEB_ORIGINAL(false, true),
   WEB_MARIX(true, true);
   
   private boolean marix;
   private boolean web;
   
   Mode(boolean marix, boolean web)
   {
      this.marix = marix;
      this.web = web;
   }

   public boolean isMarix()
   {
      return marix;
   }

   public boolean isWeb()
   {
      return web;
   }
   
   
}
