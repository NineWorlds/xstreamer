package us.nineworlds.xstreamer.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;

import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.preferences.widgets.LabelFieldEditor;
import us.nineworlds.xstreamer.preferences.widgets.SpacerFieldEditor;

import org.eclipse.ui.IWorkbench;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class SquadPrefencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public SquadPrefencesPage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("X-Wing Squadron Generation Preferences.");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addField(new DirectoryFieldEditor(PreferenceConstants.TEMPLATE_XSTREAMER_XWING_OUTPUT_DIRECTORY,
				"XStream Output Directory:", getFieldEditorParent()));
		
		addField(new DirectoryFieldEditor(PreferenceConstants.TEMPLATE_XWING_INPUT_DIRECTORY, "Template Directory", getFieldEditorParent()));		
	}

	public void init(IWorkbench workbench) {
		
	}

}