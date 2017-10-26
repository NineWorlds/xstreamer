package us.nineworlds.xstreamer.model;

import org.eclipse.jface.viewers.TreeNode;

import com.github.xws.Upgrade;

public class UpgradeTypeTreeNode extends TreeNode {

	private static final long serialVersionUID = 1L;
	String upgradeName;
	Upgrade upgrade;

	public UpgradeTypeTreeNode(String upgradeName, Upgrade upgrade) {
		super(upgrade);
		this.upgradeName = upgradeName;
		this.upgrade = upgrade;
	}
	
	public Upgrade getUpgrade() {
		return upgrade;
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
