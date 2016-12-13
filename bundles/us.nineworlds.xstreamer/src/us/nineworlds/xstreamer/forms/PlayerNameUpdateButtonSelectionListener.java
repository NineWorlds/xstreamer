package us.nineworlds.xstreamer.forms;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.jobs.StringWriterJob;
import us.nineworlds.xstreamer.preferences.PreferenceConstants;

public class PlayerNameUpdateButtonSelectionListener implements SelectionListener {

	GeneralFormPage page;
	
	public PlayerNameUpdateButtonSelectionListener(GeneralFormPage page) {
		this.page = page;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		if (page.firstPlayerName.getText() != null) {
			String player1Filename = preferenceStore.getString(PreferenceConstants.FIRST_PLAYER_NAME_FILENAME);
			scheduleJob("player1Filename", player1Filename, page.firstPlayerName.getText());
		}
		if (page.secondPlayerName.getText() != null) {
			String player1Filename = preferenceStore.getString(PreferenceConstants.SECOND_PLAYER_NAME_FILENAME);
			scheduleJob("player2Filename", player1Filename, page.secondPlayerName.getText());
		}
	}
	
	private void scheduleJob(String jobname, String filename, String content) {
		StringWriterJob job = new StringWriterJob(jobname, content, filename);
		job.schedule();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

}
