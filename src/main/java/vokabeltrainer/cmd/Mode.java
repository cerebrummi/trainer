package vokabeltrainer.cmd;

public enum Mode
{
   LOCAL_ORIGINAL(false, true), // Standard
   WEB_ORIGINAL(true, true);
   
   private boolean web;
   private boolean free;

   Mode(boolean web, boolean free)
   {
      this.web = web;
      this.free = free;
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
