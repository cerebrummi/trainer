package vokabeltrainer.panels.dictionary;

import java.util.ArrayDeque;

public enum Status
{
   OPENED_PAGE, TAB_CHAPTER, TAB_EXPRESSIONKIND, TAB_SEARCH, TAB_SELECTED_EXPRESSIONS, CHAPTER_WHICH, EXPRESSIONKIND_WHICH, SEARCH_WHICH_OWN, SEARCH_WHICH_NEW, DATA_CHAPTER;

   private final static ArrayDeque<Status> status = new ArrayDeque<>();

   public static void init(Status status)
   {
      checkInit();
      Status.status.push(status);
   }
   
   public static void push(Status status)
   {
      checkPush();
      Status.status.push(status);
   }
   
   public static void pushToKeep()
   {
      push(peek());
   }

   public static Status pop()
   {
      checkPop();
      return Status.status.pollLast();
   }

   private static Status peek()
   {
      return Status.status.peekLast();
   }
   
   private static void checkInit()
   {
      switch (status.size())
      {
      case 0:
         return;
      default:
         throw new IllegalStateException();
      }
   }
   
   private static void checkPush()
   {
      switch (status.size())
      {
      case 1:
         return;
      default:
         throw new IllegalStateException();
      }
   }

   private static void checkPop()
   {
      switch (status.size())
      {
      case 2:
         return;
      default:
         throw new IllegalStateException();
      }
   }

}
