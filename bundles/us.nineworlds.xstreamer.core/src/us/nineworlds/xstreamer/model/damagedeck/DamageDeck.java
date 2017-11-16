package us.nineworlds.xstreamer.model.damagedeck;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DamageDeck {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("amount")
	private int quantity;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("text")
	private String text;
	
	@JsonProperty("image")
	private String imagePath;
	
	@JsonProperty("section")
	private String section;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	public String toString() {
		return name;
	}
	
}
