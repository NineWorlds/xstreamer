package us.nineworlds.xstreamer.forms;

import org.eclipse.swt.widgets.Composite;

public class FirstPlayerPage extends AbstractPlayerFormPage {

	public static String FIRST_PLAYER_VIEW = "us.nineworlds.xstreamer.view.firstplayer";
	
	@Override
	public void setFocus() {
		form.setFocus();
	}

	@Override
	public void dispose() {
		toolkit.dispose();
		super.dispose();
	}

	@Override
	void setPageName() {
		form.setText("Player 1");
	}

	@Override
	void pageContent(Composite parent) {

	}

}
