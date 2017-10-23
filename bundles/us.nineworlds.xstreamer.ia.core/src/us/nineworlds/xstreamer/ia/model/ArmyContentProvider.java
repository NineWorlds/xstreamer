package us.nineworlds.xstreamer.ia.model;

import java.util.Collections;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeNode;


import us.nineworlds.iadata.IASpec;

public class ArmyContentProvider implements ITreeContentProvider {
	
	private static final Object[] EMPTY_ARRAY = new Object[0];

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof IASpec) {
			IASpec iaSpec = (IASpec) inputElement;
			return Collections.singletonList(new IASpecTreeNode(iaSpec)).toArray();
		}
		return EMPTY_ARRAY;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IASpec) {
			IASpec iaspec = (IASpec) parentElement;
			return iaspec.getArmy().getDeployments().toArray();
		}
		
		if (parentElement instanceof TreeNode) {
			TreeNode treenode = (TreeNode) parentElement;
			return treenode.getChildren();
		}
		
		return EMPTY_ARRAY;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof IASpec) {
			return true;
		}
		
		if (element instanceof TreeNode) {
			return ((TreeNode) element).hasChildren();
		}
				
		return false;
	}

}
