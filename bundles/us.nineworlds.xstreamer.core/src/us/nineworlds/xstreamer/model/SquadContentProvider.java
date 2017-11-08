package us.nineworlds.xstreamer.model;

import java.util.Collections;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeNode;

import com.github.xws.XwsSpec;

public class SquadContentProvider implements ITreeContentProvider {
	
	private static final Object[] EMPTY_ARRAY = new Object[0];

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof XwsSpec) {
			XwsSpec xwsSpec = (XwsSpec) inputElement;
			return Collections.singletonList(new XWSquadTreeNode(xwsSpec)).toArray();
		}
		return EMPTY_ARRAY;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof XwsSpec) {
			XwsSpec xwsspec = (XwsSpec) parentElement;
			return xwsspec.getPilots().toArray();
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
		if (element instanceof XwsSpec) {
			return true;
		}
		
		if (element instanceof TreeNode) {
			return ((TreeNode) element).hasChildren();
		}
				
		return false;
	}

}
