package us.nineworlds.xstreamer.forms;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import us.nineworlds.xstreamer.jobs.StringWriterJob;

public class PlayerScoreUpdateButtonSelectionListener2 implements SelectionListener {

	GeneralFormPage page;
	
	public PlayerScoreUpdateButtonSelectionListener2(GeneralFormPage page) {
		this.page = page;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		if (page.firstPlayerScore.getText() != null) {
			scheduleJob("player1Score", "player1Score.txt", page.firstPlayerScore.getText());
		}
		if (page.secondPlayerScore.getText() != null) {
			scheduleJob("player2Score", "player2Score.txt", page.secondPlayerScore.getText());
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
