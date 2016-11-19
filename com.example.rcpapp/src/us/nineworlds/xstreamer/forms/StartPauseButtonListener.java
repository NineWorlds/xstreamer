package us.nineworlds.xstreamer.forms;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.joda.time.DateTime;

import com.example.rcpapp.Activator;

import us.nineworlds.xstreamer.jobs.CountDownJob;

public class StartPauseButtonListener implements SelectionListener {

	Button startButton;
	
	CountDownJob timerJob;
	
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
			timerJob.stop();
		} else if ("Resume".equals(buttonText)) {
			startButton.setText("Pause");
			startTimer();
		}		
	}

	private void startTimer() {
		timerJob = new CountDownJob("timer");
		timerJob.schedule(1000);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		
	}
	
	 private void setInitialTimer() {
	      String inthours = CountDownTimerPage.hourText.getText();
	      String intMins = CountDownTimerPage.minuteText.getText();
	      String intSeconds = CountDownTimerPage.secondsText.getText();
	      
	      DateTime startTime = DateTime.now();
	      
	      DateTime endTime = startTime.plusHours(Integer.parseInt(inthours)).plusMinutes(Integer.parseInt(intMins)).plus(Integer.parseInt(intSeconds));
	      
	      CountDownTimerPage.hourCountDownLabel.setText(CountDownTimerPage.hourText.getText());
	      CountDownTimerPage.minuteCountDownLabel.setText(CountDownTimerPage.minuteText.getText());
	      CountDownTimerPage.secondsCountDownLabel.setText(CountDownTimerPage.secondsText.getText());
	      
	      Activator.setCountDownTime(endTime.getMillis() - startTime.getMillis());
	   }
}
