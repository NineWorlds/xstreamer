package us.nineworlds.xstreamer.forms;

import org.eclipse.swt.widgets.Composite;

public class SecondPlayerPage extends AbstractPlayerFormPage {
	public static String SECOND_PLAYER_VIEW = "us.nineworlds.xstreamer.view.secondplayer";

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
		form.setText("Player 2");					
	}

	@Override
	void pageContent(Composite parent) {
		// TODO Auto-generated method stub
		
	}

}
