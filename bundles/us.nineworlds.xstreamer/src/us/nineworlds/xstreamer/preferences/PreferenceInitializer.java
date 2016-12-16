package us.nineworlds.xstreamer.preferences;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.Bundle;

import us.nineworlds.xstreamer.Activator;

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
		
		Bundle templateBundle = Platform.getBundle(TEMPLATE_BUNDLE_ID);
		Path path = new Path("templates");
		URL url = FileLocator.find(templateBundle, path, null);
		URL realUrl = null;
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		try {
			realUrl = FileLocator.toFileURL(url);
			File templateDirectory = FileUtils.toFile(realUrl);			
			File currentDirectory = new File(new File("").getAbsolutePath());
			store.setDefault(PreferenceConstants.TEMPLATE_XSTREAMER_OUTPUT_DIRECTORY, currentDirectory.getAbsolutePath());
			store.setDefault(PreferenceConstants.TEMPLATE_INPUT_DIRECTORY, templateDirectory.getCanonicalPath().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		store.setDefault(PreferenceConstants.TIMER_FILE, "timer.txt");
		store.setDefault(PreferenceConstants.FIRST_PLAYER_NAME_FILENAME, "player1Name.txt");
		store.setDefault(PreferenceConstants.SECOND_PLAYER_NAME_FILENAME, "player2Name.txt");
		store.setDefault(PreferenceConstants.TIMER_HOUR_DEFAULT, "01");
		store.setDefault(PreferenceConstants.TIMER_MINUTES_DEFAULT, "15");
		store.setDefault(PreferenceConstants.TIMER_SECONDS_DEFAULT, "00");
		
		store.setDefault(PreferenceConstants.RELOAD_PLAYER_FILES_ON_STARTUP, false);
	}

}
