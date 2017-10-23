package us.nineworlds.xstreamer.ia.lookup;

import java.util.List;

import us.nineworlds.iadata.CommandCardDB;
import us.nineworlds.iadata.DeploymentsDB;
import us.nineworlds.iadata.command.CommandCard;
import us.nineworlds.iadata.command.CommandCards;
import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.iadata.deployment.Deployments;

public class CommandCardLookup {
   
   private static CommandCardLookup instance;
   
   private List<CommandCards> commandCards;
   
   public static CommandCardLookup newInstance(CommandCardDB commandCardDB) {
      return instance = new CommandCardLookup(commandCardDB);
   }
   
   public static CommandCardLookup getInstance() {
      return instance;
   }

   private CommandCardLookup(CommandCardDB commandCards) {
      this.commandCards = commandCards.getCommandCards();
   }
   
   public CommandCard findCommandCard(String iaspecname, String faction) {
      for( CommandCards cards : commandCards) {
    	 CommandCard commandCard = cards.getCommandCard(); 
         if (commandCard.getIaspecname().equals(iaspecname) && commandCard.getFaction().toString().equals(faction)) {
            return commandCard;
         }
      }
      return null;
   }
   
   public List<CommandCards> getDeployments() {
	   return commandCards;
   }

}
