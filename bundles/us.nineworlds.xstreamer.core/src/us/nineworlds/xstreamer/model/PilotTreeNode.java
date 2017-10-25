package us.nineworlds.xstreamer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.TreeNode;

import com.github.xws.Pilot;
import com.github.xws.Upgrades;

public class PilotTreeNode extends TreeNode {

	private static final long serialVersionUID = -9033190175203649457L;
	private Pilot pilot;
	private Upgrades upgrades;

	public PilotTreeNode(Pilot userObject) {
		super(userObject);
		pilot = userObject;
		upgrades = pilot.getUpgrades();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(pilot.getName());
		stringBuilder.append(" (");
		stringBuilder.append(pilot.getPoints());
		stringBuilder.append(")");
		return stringBuilder.toString();
	}

	@Override
	public boolean hasChildren() {
		if (upgrades != null) {
			return upgrades.getAdditionalProperties().size() > 0;
		}

		return false;
	}

	@Override
	public TreeNode[] getChildren() {
		List<UpgradeTreeNode> upgrades = new ArrayList<>();
		for (Entry<String, Object> entry : this.upgrades.getAdditionalProperties().entrySet()) {
			UpgradeTreeNode upgradesTreeNode = new UpgradeTreeNode(entry.getKey(), (List<String>) entry.getValue());
			upgrades.add(upgradesTreeNode);
		}
		
		return upgrades.toArray(new TreeNode[upgrades.size()]);
	}
}
