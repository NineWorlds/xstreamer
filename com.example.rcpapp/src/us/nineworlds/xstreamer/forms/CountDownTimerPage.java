package us.nineworlds.xstreamer.forms;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import com.example.rcpapp.Activator;

import us.nineworlds.xstreamer.preferences.PreferenceConstants;

public class CountDownTimerPage extends ViewPart {
	private FormToolkit toolkit;
	private ScrolledForm form;
	private Button resetButton;
	
	public static Label hourCountDownLabel;
	public static Label minuteCountDownLabel;
	public static Label secondsCountDownLabel;
	
	public static Text hourText;
	public static Text minuteText;
	public static Text secondsText;
	
	private IPreferenceStore preference;
	
	public CountDownTimerPage() {
		preference = Activator.getDefault().getPreferenceStore();
	}

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		ColumnLayout layout = new ColumnLayout();
		form.getBody().setLayout(layout);
		
		Section timer = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
		timer.setText("Timer Section");
		timer.setExpanded(true);
		timer.setDescription("Set the intial count down timer value.");
		Composite timerClient = toolkit.createComposite(timer);
		GridLayout timerGridLayout = new GridLayout();
		timerGridLayout.numColumns = 6;
		timerClient.setLayout(timerGridLayout);
		toolkit.createLabel(timerClient, "Match Length: ");
		
		hourText = toolkit.createText(timerClient, preference.getString(PreferenceConstants.TIMER_HOUR_DEFAULT));
		hourText.setLayoutData(createTimerTextPadding());
		
		toolkit.createLabel(timerClient, ":");		
		minuteText = toolkit.createText(timerClient, preference.getString(PreferenceConstants.TIMER_MINUTES_DEFAULT));
		minuteText.setLayoutData(createTimerTextPadding());
		
		toolkit.createLabel(timerClient, ":");
		secondsText = toolkit.createText(timerClient, preference.getString(PreferenceConstants.TIMER_SECONDS_DEFAULT));
		secondsText.setLayoutData(createTimerTextPadding());
		
		toolkit.createLabel(timerClient, " ");
		
		resetButton = toolkit.createButton(timerClient, "Reset", SWT.PUSH | SWT.RESIZE);
		Button startPauseButton = toolkit.createButton(timerClient, "Start", SWT.PUSH | SWT.RESIZE);

		GridData resetButtonData = new GridData();
		resetButtonData.horizontalSpan = 2;
		resetButtonData.widthHint = 60;
		resetButton.setLayoutData(resetButtonData);		
		resetButton.addSelectionListener(new RestTimerListener(startPauseButton));
		
		toolkit.createLabel(timerClient, " ");
		
		GridData startPauseButtonData = new GridData();
		startPauseButtonData.horizontalSpan = 2;
		startPauseButtonData.widthHint = 70;
		startPauseButton.setLayoutData(startPauseButtonData);
		startPauseButton.addSelectionListener(new StartPauseButtonListener(startPauseButton));
		
		timer.setClient(timerClient);
		
		createCountDownSection();

	}

	private void createCountDownSection() {
		Section countDown = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
		countDown.setText("Count Down Section");
		countDown.setExpanded(true);
		countDown.setDescription("Time left in the match.");
		Composite countDownClient = toolkit.createComposite(countDown);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5;
		gridLayout.makeColumnsEqualWidth = false;
		countDownClient.setLayout(gridLayout);
		hourCountDownLabel = toolkit.createLabel(countDownClient, "00");
		toolkit.createLabel(countDownClient, ":");
		minuteCountDownLabel = toolkit.createLabel(countDownClient, "00");
		toolkit.createLabel(countDownClient, ":");
		secondsCountDownLabel = toolkit.createLabel(countDownClient, "00");
		countDown.setClient(countDownClient);
	}

	private GridData createTimerTextPadding() {
		GridData gridData = new GridData(SWT.CENTER);
		gridData.widthHint = 25;
		return gridData;
	}

	@Override
	public void setFocus() {
		form.setFocus();
	}
	
	@Override
	public void dispose() {
		toolkit.dispose();
		super.dispose();
	}
}
