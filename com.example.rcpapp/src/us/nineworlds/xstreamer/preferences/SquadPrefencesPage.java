package us.nineworlds.xstreamer.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;

import us.nineworlds.xstreamer.Activator;

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
		setDescription("Player Squadron generation preferences.");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addField(new DirectoryFieldEditor(PreferenceConstants.TEMPLATE_XSTREAMER_OUTPUT_DIRECTORY,
				"XStream Output Directory:", getFieldEditorParent()));
		
		addField(new DirectoryFieldEditor(PreferenceConstants.TEMPLATE_INPUT_DIRECTORY, "Template Directory", getFieldEditorParent()));

		addField(new StringFieldEditor(PreferenceConstants.FIRST_PLAYER_FILENAME, "First Player File:",
				getFieldEditorParent()));
		addField(new StringFieldEditor(PreferenceConstants.SECOND_PLAYER_FILENAME, "Second Player File:",
				getFieldEditorParent()));
		FileFieldEditor firstPlayer = new FileFieldEditor(PreferenceConstants.TEMPLATE_FIRST_PLAYER_FILE,
				"First Player Squad Template:", getFieldEditorParent());
		firstPlayer.setFileExtensions(new String[] { "*.ftl" });
		addField(firstPlayer);
		FileFieldEditor secondPlayer = new FileFieldEditor(PreferenceConstants.TEMPLATE_SECOND_PLAYER_FILE,
				"Second Player Squad Template:", getFieldEditorParent());
		secondPlayer.setFileExtensions(new String[] { "*.ftl" });
		addField(secondPlayer);

//		addField(new BooleanFieldEditor(PreferenceConstants.RELOAD_PLAYER_FILES_ON_STARTUP, "&Reload squadrons on restart",
//				getFieldEditorParent()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}