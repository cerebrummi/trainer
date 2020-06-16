package vokabeltrainer.panels.dictionary;

import java.util.HashMap;

public class Interaction
{ 
   private static HashMap<Interaction, Command> interactions;
   
   static
   {
      Interaction.interactions = new HashMap<>();
 
      interactions.put(new Interaction(Action.GERMAN, Status.TAB_CHAPTER), Command.NO_TABLE);
      interactions.put(new Interaction(Action.HEBREW, Status.TAB_CHAPTER), Command.NO_TABLE);
      interactions.put(new Interaction(Action.GERMAN, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.HEBREW, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      
      interactions.put(new Interaction(Action.GERMAN, Status.TAB_EXPRESSIONKIND), Command.NO_TABLE);
      interactions.put(new Interaction(Action.HEBREW, Status.TAB_EXPRESSIONKIND), Command.NO_TABLE);
      interactions.put(new Interaction(Action.GERMAN, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.HEBREW, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      
      interactions.put(new Interaction(Action.GERMAN, Status.TAB_SEARCH), Command.NO_TABLE);
      interactions.put(new Interaction(Action.HEBREW, Status.TAB_SEARCH), Command.NO_TABLE);
      interactions.put(new Interaction(Action.GERMAN, Status.SEARCH_WHICH), Command.TABLE_SEARCH_WHICH_GERMAN);
      interactions.put(new Interaction(Action.HEBREW, Status.SEARCH_WHICH), Command.TABLE_SEARCH_WHICH_HEBREW);
      
   }
   
   public static Command getCommand(Interaction interaction)
   {
      return interactions.get(interaction);
   }
   
   public Interaction(Action action, Status status)
   {
      this.action = action;
      this.status = status;
   }
   
   private Status status;
   private Action action;

   Status getStatus()
   {
      return status;
   }

   Action getAction()
   {
      return action;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((action == null) ? 0 : action.hashCode());
      result = prime * result + ((status == null) ? 0 : status.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Interaction other = (Interaction) obj;
      if (action != other.action)
         return false;
      if (status != other.status)
         return false;
      return true;
   }


}
