package us.nineworlds.xstreamer.ia.model.maps;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Maps {

	@JsonProperty("maps")
	private List<MapMetaData> maps;

	public List<MapMetaData> getMaps() {
		return maps;
	}

	public void setMaps(List<MapMetaData> maps) {
		this.maps = maps;
	}
}
