package us.nineworlds.xstreamer.ia.model.maps;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MapMetaData {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("imagePath")
	private String imagePath;
	
	@JsonProperty("fullImagePath")
	private String fullSizeImagePath;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getFullSizeImagePath() {
		return fullSizeImagePath;
	}

	public void setFullSizeImagePath(String fullSizeImagePath) {
		this.fullSizeImagePath = fullSizeImagePath;
	}
	
	public String toString() {
		return name;
	}
}
