package us.nineworlds.xstreamer.model;

import org.eclipse.jface.viewers.TreeNode;

public class UpgradeTypeTreeNode extends TreeNode {

	private static final long serialVersionUID = 1L;
	String upgradeName;

	public UpgradeTypeTreeNode(Object upgrade) {
		super(upgrade);
		upgradeName = (String) upgrade;
	}

	@Override
	public String toString() {
		return upgradeName;
	}
	
	@Override
	public boolean hasChildren() {
		return false;
	}

}
