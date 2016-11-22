package us.nineworlds.xstreamer.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeNode;

import com.github.xws.Pilot;
import com.github.xws.XwsSpec;

public class XWSquadTreeNode extends TreeNode {

	private static final long serialVersionUID = 1L;
	private XwsSpec playerModel;

	public XWSquadTreeNode(XwsSpec userObject) {
		super(userObject);
		playerModel = userObject;
	}

	@Override
	public String toString() {
		return playerModel.getName();
	}
	
	@Override
	public boolean hasChildren() {
		return playerModel.getPilots().size() > 0;
	}
	
	@Override
	public TreeNode[] getChildren() {
		List<PilotTreeNode> treeNodes = new ArrayList<>();
		for (Pilot pilot : playerModel.getPilots()) {
			PilotTreeNode node = new PilotTreeNode(pilot);
			treeNodes.add(node);
		}
		return treeNodes.toArray(new TreeNode[treeNodes.size()]);
	}
}
