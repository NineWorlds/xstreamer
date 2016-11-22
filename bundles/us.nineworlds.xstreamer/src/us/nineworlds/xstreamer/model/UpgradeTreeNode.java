package us.nineworlds.xstreamer.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeNode;

public class UpgradeTreeNode extends TreeNode {

	private static final long serialVersionUID = 1L;
	String upgradeSlot;
	List<String> upgradeOptions;

	public UpgradeTreeNode(String upgradeSlot, List<String> upgrades) {
		super(upgradeSlot);
		this.upgradeSlot = upgradeSlot;
		this.upgradeOptions = upgrades;
	}

	@Override
	public String toString() {
		return upgradeSlot;
	}
	
	@Override
	public boolean hasChildren() {
		if (upgradeOptions == null || upgradeOptions.isEmpty()) {
			return false;
		}
		return true;
	}
	
	@Override
	public TreeNode[] getChildren() {
		List<UpgradeTypeTreeNode> upgradeType = new ArrayList();
		for(String upgrade : upgradeOptions) {
			UpgradeTypeTreeNode treeNode = new UpgradeTypeTreeNode(upgrade);
			upgradeType.add(treeNode);
		}
		return upgradeType.toArray(new TreeNode[upgradeType.size()]);
	}
}

