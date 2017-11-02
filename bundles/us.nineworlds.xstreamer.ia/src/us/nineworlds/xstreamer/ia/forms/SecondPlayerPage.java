package us.nineworlds.xstreamer.ia.forms;

import org.eclipse.swt.widgets.Composite;

import us.nineworlds.iadata.IASpec;

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
		return us.nineworlds.xstreamer.ia.core.Activator.getDefault().getPlayer2Model();
	}

	@Override
	public String playerFileName() {
		return "ia_player2_army.html";
	}
	
	@Override
	public String armyTemplate() {
		return "army_left_aligned.ftl";
	}

	@Override
	void resetPlayerModel(IASpec model) {
		us.nineworlds.xstreamer.ia.core.Activator.getDefault().setPlayer2Model(model);
	}

	@Override
	String commandTemplate() {
		return "command_left_aligned.ftl";
	}

	@Override
	String commandFileName() {
		return "ia_player2_command.html";
	}
	
}
