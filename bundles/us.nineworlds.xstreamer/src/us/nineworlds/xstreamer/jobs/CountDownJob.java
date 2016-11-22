package us.nineworlds.xstreamer.jobs;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.forms.CountDownTimerPage;
import us.nineworlds.xstreamer.preferences.PreferenceConstants;

public class CountDownJob extends Job {
	
	private boolean running = true;

	public CountDownJob(String name) {
		super(name);
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
		long timeLeft = Activator.getCountDownTime();
		if (timeLeft <= 0 || running == false) {
			running = false;
			return Status.CANCEL_STATUS;
		}

		Duration duration = new Duration(Activator.getCountDownTime());
		duration = duration.minus(1000);
		Activator.setCountDownTime(duration.getMillis());

		final Period periodLeft = duration.toPeriod();
		PeriodFormatter formatter = new PeriodFormatterBuilder().minimumPrintedDigits(2).printZeroAlways().appendHours()
				.appendSeparator(":").minimumPrintedDigits(2).printZeroAlways().appendMinutes().appendSeparator(":")
				.minimumPrintedDigits(2).printZeroAlways().appendSeconds().toFormatter();
		String formattedTime = periodLeft.toString(formatter);
		Runnable updateUi = () -> {
			int hours = periodLeft.getHours();
			int mins = periodLeft.getMinutes();
			int seconds = periodLeft.getSeconds();
			NumberFormat numberFormat = new DecimalFormat("00");
			CountDownTimerPage.hourCountDownLabel.setText(numberFormat.format(hours));
			CountDownTimerPage.minuteCountDownLabel.setText(numberFormat.format(mins));
			CountDownTimerPage.secondsCountDownLabel.setText(numberFormat.format(seconds));
		};

		Display.getDefault().asyncExec(updateUi);
		
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
		
		schedule(1000);

		return Status.OK_STATUS;
	}

}
