package us.nineworlds.xstreamer.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.example.rcpapp.Activator;

import us.nineworlds.xstreamer.preferences.widgets.LabelFieldEditor;
import us.nineworlds.xstreamer.preferences.widgets.SpacerFieldEditor;

public class TimerPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public TimerPreferencePage() {
		super(FieldEditorPreferencePage.GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Specify the default starting values for the count down timer.");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		addField(new DirectoryFieldEditor(PreferenceConstants.TIMER_FILE_PATH, 
				"Timer output directory:", getFieldEditorParent()));
		
		addField(new StringFieldEditor(PreferenceConstants.TIMER_FILE, "Filename:", getFieldEditorParent()));
		
		addField(new SpacerFieldEditor(getFieldEditorParent()));
		
		addField(new LabelFieldEditor("Default Timer Settings", getFieldEditorParent()));
		
		StringFieldEditor hoursField = new StringFieldEditor(PreferenceConstants.TIMER_HOUR_DEFAULT, "Hour(s):", getFieldEditorParent());
		hoursField.setTextLimit(2);
		addField(hoursField);
		
		StringFieldEditor minsField = new StringFieldEditor(PreferenceConstants.TIMER_MINUTES_DEFAULT, "Minutes:", getFieldEditorParent());
		minsField.setTextLimit(2);
		addField(minsField);
		
		StringFieldEditor secField = new StringFieldEditor(PreferenceConstants.TIMER_SECONDS_DEFAULT, "Seconds:", getFieldEditorParent());
		secField.setTextLimit(2);
		addField(secField);
	}
}
