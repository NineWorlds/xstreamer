package us.nineworlds.xstreamer.ia.preferences;

import java.io.File;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import us.nineworlds.xstreamer.ia.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {
	
	public static final String TEMPLATE_BUNDLE_ID = "us.nineworlds.xstreamer.templates";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		File currentDirectory = new File(new File("").getAbsolutePath());
		store.setDefault(PreferenceConstants.TEMPLATE_XSTREAMER_IA_OUTPUT_DIRECTORY, currentDirectory.getAbsolutePath());
		
		store.setDefault(PreferenceConstants.OBS_REFRESH_SCRIPT, true);					
	}

}
