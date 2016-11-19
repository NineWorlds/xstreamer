package us.nineworlds.xstreamer.forms;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.part.ViewPart;

public class FirstPlayerPage extends ViewPart {
	private FormToolkit toolkit;
	private ScrolledForm form;
	
	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.setText("Player 1");	
	}

	@Override
	public void setFocus() {
		form.setFocus();
	}
	
	@Override
	public void dispose() {

	}

}
