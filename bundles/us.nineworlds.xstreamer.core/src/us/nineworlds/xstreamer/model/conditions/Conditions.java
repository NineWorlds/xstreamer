package us.nineworlds.xstreamer.model.conditions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Conditions {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("text")
	private String text;
	
	@JsonProperty("xws")
	private String xwsName;
	
	@JsonProperty("unique")
	private boolean unique;
	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("image")
	private String imagePath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getXwsName() {
		return xwsName;
	}

	public void setXwsName(String xwsName) {
		this.xwsName = xwsName;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String toString() {
		return name;
	}
}
