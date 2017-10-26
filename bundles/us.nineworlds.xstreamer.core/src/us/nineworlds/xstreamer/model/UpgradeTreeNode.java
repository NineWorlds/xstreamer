package us.nineworlds.xstreamer.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeNode;

import com.github.xws.Upgrades;

public class UpgradeTreeNode extends TreeNode {

	private static final long serialVersionUID = 1L;
	String upgradeSlot;
	List<String> upgradeOptions;
	Upgrades parentUpgrades;

	public UpgradeTreeNode(String upgradeSlot, List<String> upgrades, Upgrades parent) {
		super(upgradeSlot);
		this.upgradeSlot = upgradeSlot;
		this.upgradeOptions = upgrades;
		this.parentUpgrades = parent;
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
		
		
		for(String upgradeName : upgradeOptions) {
			UpgradeTypeTreeNode treeNode = new UpgradeTypeTreeNode(upgradeName, parentUpgrades.findUpgrade(upgradeName));
			upgradeType.add(treeNode);
		}
		return upgradeType.toArray(new TreeNode[upgradeType.size()]);
	}
}

