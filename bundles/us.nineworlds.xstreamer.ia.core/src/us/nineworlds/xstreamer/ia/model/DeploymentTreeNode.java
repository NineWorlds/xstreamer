package us.nineworlds.xstreamer.ia.model;

import org.eclipse.jface.viewers.TreeNode;

import us.nineworlds.iadata.deployment.Deployment;

public class DeploymentTreeNode extends TreeNode {

	private Deployment deployment;

	public DeploymentTreeNode(Deployment userObject) {
		super(userObject);
		deployment = userObject;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(deployment.getName());
		stringBuilder.append(" (");
		stringBuilder.append(deployment.getDeploymentCost());
		stringBuilder.append(")");
		return stringBuilder.toString();
	}

	@Override
	public boolean hasChildren() {
		return false;
	}
}
