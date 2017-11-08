package us.nineworlds.xstreamer.ia.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeNode;

import us.nineworlds.iadata.deployment.Deployments;

public class ArmyTreeNode extends TreeNode {

	private List<Deployments> deployments;

	public ArmyTreeNode(List<Deployments> userObject) {
		super(userObject);
		deployments = userObject;
	}

	@Override
	public String toString() {
		return "Army";
	}

	@Override
	public boolean hasChildren() {
		return !deployments.isEmpty();
	}
	
	@Override
	public TreeNode[] getChildren() {
		List<DeploymentTreeNode> treeNodes = new ArrayList<>();
		
		for (Deployments deployments : deployments) {
			DeploymentTreeNode node = new DeploymentTreeNode(deployments.getDeployment());
			treeNodes.add(node);
		}
		
		return treeNodes.toArray(new TreeNode[treeNodes.size()]);
	}
}
