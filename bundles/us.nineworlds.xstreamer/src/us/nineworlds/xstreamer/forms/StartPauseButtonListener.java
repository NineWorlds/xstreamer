package us.nineworlds.xstreamer.forms;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.jobs.CountDownJob;

public class StartPauseButtonListener implements SelectionListener {

	Button startButton;

	Timer countDownTimer;

	public StartPauseButtonListener(Button startButton) {
		this.startButton = startButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String buttonText = startButton.getText();
		if ("Start".equals(buttonText)) {
			startButton.setText("Pause");
			setInitialTimer();
			startTimer();
		} else if ("Pause".equals(buttonText)) {
			startButton.setText("Resume");
			countDownTimer.cancel();
		} else if ("Resume".equals(buttonText)) {
			startButton.setText("Pause");
			startTimer();
		}
	}

	private void startTimer() {
		countDownTimer = new Timer();
		countDownTimer.schedule(new CountDownTimerTask(), 0, 1000);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {

	}

	private void setInitialTimer() {
		String inthours = CountDownTimerPage.hourText.getText();
		String intMins = CountDownTimerPage.minuteText.getText();
		String intSeconds = CountDownTimerPage.secondsText.getText();

		DateTime startTime = DateTime.now();

		DateTime endTime = startTime.plusHours(Integer.parseInt(inthours)).plusMinutes(Integer.parseInt(intMins))
				.plus(Integer.parseInt(intSeconds));

		CountDownTimerPage.hourCountDownLabel.setText(CountDownTimerPage.hourText.getText());
		CountDownTimerPage.minuteCountDownLabel.setText(CountDownTimerPage.minuteText.getText());
		CountDownTimerPage.secondsCountDownLabel.setText(CountDownTimerPage.secondsText.getText());

		Activator.setCountDownTime(endTime.getMillis() - startTime.getMillis());
	}
	
	class CountDownTimerTask extends TimerTask {

		@Override
		public void run() {
			Duration duration = new Duration(Activator.getCountDownTime());
			if (duration.getMillis() <= 0) {
				countDownTimer.cancel();
				return;
			}
			
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
			
			Job countDownJob = new CountDownJob("countdown", formattedTime);
			countDownJob.schedule();
		}
		
	}
	
}
