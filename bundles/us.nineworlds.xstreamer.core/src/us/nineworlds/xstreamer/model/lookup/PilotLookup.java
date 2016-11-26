package us.nineworlds.xstreamer.model.lookup;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.guidokessels.ships.Pilot;

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
		InputStream inputStream = null;
		try {
			inputStream = this.getClass().getResourceAsStream("/xws-data/pilots.json");
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
	
	public List<Pilot> getPilots() {
		return pilots;
	}
}
