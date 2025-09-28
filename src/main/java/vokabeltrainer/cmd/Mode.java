package vokabeltrainer.cmd;

public enum Mode
{
   LOCAL_ORIGINAL(false, false, true), // Standard
   LOCAL_MARIX(true, false, false),
   WEB_ORIGINAL(false, true, true),
   WEB_MARIX(true, true, false);
   
   private boolean marix;
   private boolean web;
   private boolean free;
   
   Mode(boolean marix, boolean web, boolean free)
   {
      this.marix = marix;
      this.web = web;
      this.free = free;
   }

   public boolean isMarix()
   {
      return marix;
   }

   public boolean isWeb()
   {
      return web;
   }

   public boolean isFree()
   {
      return free;
   }
}
