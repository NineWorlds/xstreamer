package us.nineworlds.xstreamer.ia.forms;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;

import us.nineworlds.iadata.IASpec;
import us.nineworlds.xstreamer.ia.Activator;
import us.nineworlds.xstreamer.ia.preferences.PreferenceConstants;

public class SecondPlayerPage extends AbstractPlayerFormPage {

	public static String SECOND_PLAYER_VIEW = "us.nineworlds.xstreamer.view.ia.secondplayer";
	
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
		form.setText("IA Player 2");
	}

	@Override
	void pageContent(Composite parent) {

	}

	@Override
	public IASpec getPlayerModel() {
		return us.nineworlds.xstreamer.ia.core.Activator.getDefault().getPlayer1Model();
	}

	@Override
	public String playerFileName() {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		
		return preferenceStore.getString(PreferenceConstants.SECOND_PLAYER_SQUAD_FILENAME);
	}
	
	@Override
	public String armyTemplate() {
//		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
//		return preferenceStore.getString(PreferenceConstants.TEMPLATE_FIRST_PLAYER_FILE);
		return null;
	}

	@Override
	void resetPlayerModel(IASpec model) {
		us.nineworlds.xstreamer.ia.core.Activator.getDefault().setPlayer2Model(model);
	}
	
}
