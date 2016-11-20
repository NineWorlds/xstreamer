package us.nineworlds.xstreamer.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import us.nineworlds.xstreamer.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		store.setDefault(PreferenceConstants.TIMER_FILE, "timer.txt");
		store.setDefault(PreferenceConstants.TIMER_HOUR_DEFAULT, "01");
		store.setDefault(PreferenceConstants.TIMER_MINUTES_DEFAULT, "15");
		store.setDefault(PreferenceConstants.TIMER_SECONDS_DEFAULT, "00");
		
		store.setDefault(PreferenceConstants.RELOAD_PLAYER_FILES_ON_STARTUP, false);
	}

}
