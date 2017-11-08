package us.nineworlds.xstreamer.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;

import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.preferences.widgets.LabelFieldEditor;
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

public class GeneralPrefencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public GeneralPrefencesPage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("General Player Preferences.");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	@Override
	public void createFieldEditors() {
		new LabelFieldEditor("Player Filenames", getFieldEditorParent()).adjustForNumColumns(3);
		
		addField(new DirectoryFieldEditor(PreferenceConstants.XSTREAMER_GENERAL_OUTPUT_DIRECTORY, "Output Directory for Player Files:", getFieldEditorParent()));
		
		addField(new StringFieldEditor(PreferenceConstants.FIRST_PLAYER_NAME_FILENAME, "Player 1 Filename:",
				getFieldEditorParent()));
		addField(new StringFieldEditor(PreferenceConstants.SECOND_PLAYER_NAME_FILENAME, "Player 2 Filename:",
				getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		
	}

}