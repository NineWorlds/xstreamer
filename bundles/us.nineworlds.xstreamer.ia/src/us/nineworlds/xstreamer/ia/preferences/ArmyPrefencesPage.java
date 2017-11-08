package us.nineworlds.xstreamer.ia.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.eclipse.ui.IWorkbench;

public class ArmyPrefencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public ArmyPrefencesPage() {
		super(GRID);
		setPreferenceStore(us.nineworlds.xstreamer.ia.Activator.getDefault().getPreferenceStore());
		setDescription("Imperial Assault Generation Preferences");
	}

	@Override
	public void createFieldEditors() {
		addField(new DirectoryFieldEditor(PreferenceConstants.TEMPLATE_XSTREAMER_IA_OUTPUT_DIRECTORY,
				"XStreamer Output Directory:", getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.OBS_REFRESH_SCRIPT, "Automatically refresh browser source.", getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		
	}

}