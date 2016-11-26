package us.nineworlds.xstreamer.model;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xws.XwsSpec;

public class ImportPlayerFile {

	public XwsSpec load(String importFile) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(importFile), XwsSpec.class);
	}
}
