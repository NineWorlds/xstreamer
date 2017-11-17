package us.nineworlds.xstreamer.jface.listviewer;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Composite;

public class XStreamerListViewer extends ListViewer {

	public XStreamerListViewer(Composite parent, int flags) {
		super(parent, flags);
	}
	
	@Override
	public void listRemoveAll() {
		super.listRemoveAll();
	}
	
}
