package us.nineworlds.xstreamer.ia.model.maps;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SkirmishMapsLoader {

	public Maps loadSkirmishMaps(InputStream inputStream) {
		ObjectMapper mapper = new ObjectMapper();
		Maps skirmishMaps = null;
		try {
			skirmishMaps = mapper.readValue(inputStream, Maps.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return skirmishMaps;
	}
	
}
