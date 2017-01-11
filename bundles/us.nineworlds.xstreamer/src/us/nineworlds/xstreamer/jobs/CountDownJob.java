package us.nineworlds.xstreamer.jobs;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.preferences.PreferenceConstants;

public class CountDownJob extends Job {
	
	private boolean running = true;
	private String formattedTime;

	public CountDownJob(String name, String formattedTime) {
		super(name);
		this.formattedTime = formattedTime;
	}
	
	@Override
	public boolean shouldSchedule() {
		return running;
	}
	
	public void stop() {
		running = false;
	}
	
	@Override
	public boolean belongsTo(Object family) {
		return "timer".equals(family);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String timerFilePath = preferenceStore.getString(PreferenceConstants.TIMER_FILE_PATH);
		String timerFileName = preferenceStore.getString(PreferenceConstants.TIMER_FILE);

		File counterFile = new File(timerFilePath + File.separator + timerFileName);
		try {
			FileUtils.writeStringToFile(counterFile, formattedTime, "UTF-8");
		} catch (IOException e) {
			running = false;
			e.printStackTrace();
		}
		
		return Status.OK_STATUS;
	}

}
