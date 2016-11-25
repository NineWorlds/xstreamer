package com.github.guidokessels.ships;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pilot {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("unique")
	private boolean unique;
	
	@JsonProperty("ship")
	private String ship;
	
	@JsonProperty("skill")
	private String skill;
		
	@JsonProperty("points")
	private String points;
	
	@JsonProperty("slots")
	private List<String> slots;
	
	@JsonProperty("text")
	private String text;
	
	@JsonProperty("unique")
	public boolean isUnique() {
		return unique;
	}

	@JsonProperty("unique")
	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	@JsonProperty("ship")
	public String getShip() {
		return ship;
	}

	@JsonProperty("ship")
	public void setShip(String ship) {
		this.ship = ship;
	}

	@JsonProperty("skill")
	public String getSkill() {
		return skill;
	}

	@JsonProperty("skill")
	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	@JsonProperty("slots")
	public List<String> getSlots() {
		return slots;
	}

	@JsonProperty("slots")
	public void setSlots(List<String> slots) {
		this.slots = slots;
	}

	@JsonProperty("faction")
	public String getFaction() {
		return faction;
	}

	@JsonProperty("faction")
	public void setFaction(String faction) {
		this.faction = faction;
	}

	@JsonProperty("image")
	private String imagePath;
	
	@JsonProperty("faction")
	private String faction;
	
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
