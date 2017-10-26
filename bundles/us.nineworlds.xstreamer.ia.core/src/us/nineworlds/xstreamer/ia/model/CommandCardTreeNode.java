package us.nineworlds.xstreamer.ia.model;

import org.eclipse.jface.viewers.TreeNode;

import us.nineworlds.iadata.command.CommandCard;
import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.iadata.util.CommandCardsDBLoader;
import us.nineworlds.xstreamer.ia.lookup.CommandCardLookup;

public class CommandCardTreeNode extends TreeNode {

	private CommandCard commandCard;
	
	public CommandCardTreeNode(CommandCard userObject) {
		super(userObject);
		commandCard = userObject;
	}

	@Override
	public String toString() {
		
		CommandCard card = CommandCardLookup.getInstance().findCommandCard(commandCard.getIaspecname(), commandCard.getFaction().toString());
		
		
		StringBuilder stringBuilder = new StringBuilder();
		if (card != null) {			
			commandCard.setName(card.getName());
			commandCard.setCost(card.getCost());
			commandCard.setLimit(card.getLimit());			
		}
		
		stringBuilder.append(commandCard.getName());
		
		stringBuilder.append(" (");
		stringBuilder.append(commandCard.getCost());
		stringBuilder.append(")");
		
		return stringBuilder.toString();
	}
	
	@Override
	public boolean hasChildren() {
		return false;
	}
}
