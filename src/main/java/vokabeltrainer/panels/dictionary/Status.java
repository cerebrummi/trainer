package vokabeltrainer.panels.dictionary;

import java.util.ArrayDeque;

public enum Status
{
   OPENED_PAGE,
   TAB_CHAPTER,
   TAB_EXPRESSIONKIND,
   TAB_SEARCH,
   TAB_SELECTED_EXPRESSIONS,
   CHAPTER_WHICH,
   EXPRESSIONKIND_WHICH,
   SEARCH_WHICH_OWN,
   SEARCH_WHICH_NEW, DATA_CHAPTER;
   
   private static ArrayDeque<Status> status = new ArrayDeque<>();

   public static void push(Status status)
   {
      Status.status.push(status);
   }

   public static Status pop()
   {
      return Status.status.pollLast();
   }

   public static Status peek()
   {
      return Status.status.peekLast();
   }
}
