package us.nineworlds.xstreamer.forms;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;

import com.example.rcpapp.Activator;
import com.github.xws.XwsSpec;

import us.nineworlds.xstreamer.preferences.PreferenceConstants;

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

	@Override
	public XwsSpec getPlayerModel() {
		return Activator.getPlayer1();
	}

	@Override
	public String playerFileName() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		
		return preferenceStore.getString(PreferenceConstants.FIRST_PLAYER_FILENAME);
	}
	
	@Override
	public String squadTemplate() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		return preferenceStore.getString(PreferenceConstants.TEMPLATE_FIRST_PLAYER_FILE);
	}
	
}
