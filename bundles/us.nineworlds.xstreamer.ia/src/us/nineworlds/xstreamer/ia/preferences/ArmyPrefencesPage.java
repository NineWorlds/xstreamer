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

	public void createFieldEditors() {
		addField(new DirectoryFieldEditor(PreferenceConstants.TEMPLATE_XSTREAMER_IA_OUTPUT_DIRECTORY,
				"XStreamer Output Directory:", getFieldEditorParent()));				
	}

	public void init(IWorkbench workbench) {
		
	}

}