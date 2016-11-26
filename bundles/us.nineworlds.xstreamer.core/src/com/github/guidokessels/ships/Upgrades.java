package com.github.guidokessels.ships;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Upgrades {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("slot")
	private String slot;
	
	@JsonProperty("points")
	private Integer points;
	
	@JsonProperty("attack")
	private Integer attack;
	
	@JsonProperty("range")
	private String range;
	
	@JsonProperty("text")
	private String text;
	
	@JsonProperty("image")
	private String imagePath;
	
	@JsonProperty("xws")
	private String xws;

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("slot")
	public String getSlot() {
		return slot;
	}

	@JsonProperty("slot")
	public void setSlot(String slot) {
		this.slot = slot;
	}

	@JsonProperty("points")
	public Integer getPoints() {
		return points;
	}

	@JsonProperty("points")
	public void setPoints(Integer points) {
		this.points = points;
	}

	@JsonProperty("attack")
	public Integer getAttack() {
		return attack;
	}

	@JsonProperty("attack")
	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	@JsonProperty("range")
	public String getRange() {
		return range;
	}

	@JsonProperty("range")
	public void setRange(String range) {
		this.range = range;
	}

	@JsonProperty("text")
	public String getText() {
		return text;
	}

	@JsonProperty("text")
	public void setText(String text) {
		this.text = text;
	}

	@JsonProperty("image")
	public String getImagePath() {
		return imagePath;
	}

	@JsonProperty("image")
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@JsonProperty("xws")
	public String getXws() {
		return xws;
	}

	@JsonProperty("xws")
	public void setXws(String xws) {
		this.xws = xws;
	}
	
}
