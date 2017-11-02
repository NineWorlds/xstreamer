package us.nineworlds.xstreamer.ia.forms;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;

import us.nineworlds.iadata.IASpec;
import us.nineworlds.xstreamer.ia.Activator;
import us.nineworlds.xstreamer.ia.preferences.PreferenceConstants;

public class FirstPlayerPage extends AbstractPlayerFormPage {

	public static String FIRST_PLAYER_VIEW = "us.nineworlds.xstreamer.view.ia.firstplayer";
	
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
		form.setText("IA Player 1");
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
		return "ia_player1_army.html";
	}
	
	@Override
	public String armyTemplate() {
		return "army_left_aligned.ftl";
	}

	@Override
	void resetPlayerModel(IASpec model) {
		us.nineworlds.xstreamer.ia.core.Activator.getDefault().setPlayer1Model(model);
	}

	@Override
	String commandTemplate() {
		return "command_left_aligned.ftl";
	}

	@Override
	String commandFileName() {
		return "ia_player1_command.html";
	}
	
}
