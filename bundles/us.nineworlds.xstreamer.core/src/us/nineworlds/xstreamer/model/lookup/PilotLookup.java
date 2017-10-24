package us.nineworlds.xstreamer.model.lookup;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.guidokessels.ships.Pilot;

import us.nineworlds.xstreamer.core.Activator;

public class PilotLookup {

	private static PilotLookup instance;
	List<Pilot> pilots = new ArrayList<>();

	public static PilotLookup getInstance() {
		if (instance == null) {
			instance = new PilotLookup();
		}
		return instance;
	}

	private PilotLookup() {
		initPilots();
	}

	private void initPilots() {
		Bundle bundle = Platform.getBundle(Activator.DATA_BUNDLE_ID);
		IPath pilotsPath = new Path("pilots/pilots.json");
		URL pilotsUrl = FileLocator.find(bundle, pilotsPath, null);
		InputStream inputStream = null;
		try {
			inputStream = pilotsUrl.openStream();
			ObjectMapper mapper = new ObjectMapper();
			pilots = Arrays.asList(mapper.readValue(inputStream, Pilot[].class));
		} catch (Exception ex) {
			ex.printStackTrace();			
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	public String lookupPilot(String value) {
		for (Pilot pilot : pilots) {
			if (pilot.getXws().equals(value)) {
				return pilot.getName();
			}
		}
		return value;
	}
	
	public Pilot lookupPilotValue(String xwsname) {
		for (Pilot pilot : pilots) {
			if (pilot.getXws().equals(xwsname)) {
				return pilot;
			}
		}
		return null;
	}
	
	public List<Pilot> getPilots() {
		return pilots;
	}
}
