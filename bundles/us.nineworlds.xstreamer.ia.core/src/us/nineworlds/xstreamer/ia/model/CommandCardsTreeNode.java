package us.nineworlds.xstreamer.ia.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeNode;

import us.nineworlds.iadata.command.CommandCard;
import us.nineworlds.iadata.command.CommandCards;
import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.iadata.deployment.Deployments;

public class CommandCardsTreeNode extends TreeNode {

	private List<CommandCards> commandCards;

	public CommandCardsTreeNode(List<CommandCards> userObject) {
		super(userObject);
		commandCards = userObject;
	}

	@Override
	public String toString() {
		return "Command Cards";
	}

	@Override
	public boolean hasChildren() {
		return !commandCards.isEmpty();
	}
	
	@Override
	public TreeNode[] getChildren() {
		List<CommandCardTreeNode> treeNodes = new ArrayList<>();
		
		for (CommandCards commandCard : commandCards) {
			CommandCardTreeNode node = new CommandCardTreeNode(commandCard.getCommandCard());
			treeNodes.add(node);
		}
		
		return treeNodes.toArray(new TreeNode[treeNodes.size()]);
	}
}
