package us.nineworlds.xstreamer.ia.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeNode;

import us.nineworlds.iadata.IASpec;

public class IASpecTreeNode extends TreeNode {

	private static final long serialVersionUID = 1L;
	private IASpec playerModel;

	public IASpecTreeNode(IASpec userObject) {
		super(userObject);
		playerModel = userObject;
	}

	@Override
	public String toString() {
		return playerModel.getArmy().getName();
	}
	
	@Override
	public boolean hasChildren() {
		return playerModel.getArmy().getDeployments().size() > 0;
	}
	
	@Override
	public TreeNode[] getChildren() {
		
		List<TreeNode> treeNodes = new ArrayList<>();
		
		treeNodes.add(new ArmyTreeNode(playerModel.getArmy().getDeployments()));
		treeNodes.add(new CommandCardsTreeNode(playerModel.getCommandCards()));
				
		return treeNodes.toArray(new TreeNode[treeNodes.size()]);
	}
}
