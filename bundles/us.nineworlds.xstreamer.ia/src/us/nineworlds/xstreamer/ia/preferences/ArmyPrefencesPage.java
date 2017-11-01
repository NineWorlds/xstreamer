package us.nineworlds.xstreamer.ia.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;

import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.preferences.widgets.LabelFieldEditor;
import us.nineworlds.xstreamer.preferences.widgets.SpacerFieldEditor;

import org.eclipse.ui.IWorkbench;

public class ArmyPrefencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ArmyPrefencesPage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Imperial Assault Generation Preferences");
	}

	public void createFieldEditors() {
		addField(new DirectoryFieldEditor(PreferenceConstants.TEMPLATE_XSTREAMER_IA_OUTPUT_DIRECTORY,
				"XStreamer Output Directory:", getFieldEditorParent()));				
	}

	public void init(IWorkbench workbench) {
		
	}

}