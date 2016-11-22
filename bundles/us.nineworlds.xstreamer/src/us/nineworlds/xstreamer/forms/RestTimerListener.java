package us.nineworlds.xstreamer.forms;

import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import us.nineworlds.xstreamer.jobs.CountDownJob;

public class RestTimerListener implements SelectionListener {
	Button startButton;

	public RestTimerListener(Button startButton) {
		this.startButton = startButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		startButton.setText("Start");
		IJobManager jobManager = Job.getJobManager();
		Job[] find = jobManager.find("timer");
		
		if (find != null) {
			for (Job job : find) {
				if (job instanceof CountDownJob) {
					CountDownJob cjob = (CountDownJob) job;
					cjob.stop();
					cjob.cancel();
				}
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		
	}

}
