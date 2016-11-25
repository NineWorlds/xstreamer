package com.github.xws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.guidokessels.ships.Ship;

import us.nineworlds.xstreamer.model.lookup.PilotLookup;
import us.nineworlds.xstreamer.model.lookup.ShipsLookup;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "multisection_id", "points", "name", "ship", "hull", "shields", "upgrades", "vendor" })
public class Pilot {

	private ShipsLookup shipsLookup = ShipsLookup.getInstance();

	private boolean shieldsLoaded = false;
	private boolean hullLoaded = false;
	
	private String xwsname;

	@JsonProperty("multisection_id")
	private Integer multisectionId;
	/**
	 * The total points spent creating this squadron.
	 * 
	 */
	@JsonProperty("points")
	@JsonPropertyDescription("")
	private Integer points;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("name")
	private String name;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("ship")
	private String ship;
	/**
	 * Hull value for the ship
	 * 
	 */
	@JsonProperty("hull")
	@JsonPropertyDescription("")
	private Integer hull;
	/**
	 * Shield value for the ship
	 * 
	 */
	@JsonProperty("shields")
	@JsonPropertyDescription("")
	private Integer shields;

	@JsonProperty("upgrades")
	private Upgrades upgrades;
	/**
	 * An extensible object containing app-specific data. Developers should put
	 * extra data here under their own namespace.
	 * 
	 */
	@JsonProperty("vendor")
	@JsonPropertyDescription("")
	private Vendor vendor;

	private String pilotId;
	
	private String pilotSkill;

	/**
	 * 
	 * @return The multisectionId
	 */
	@JsonProperty("multisection_id")
	public Integer getMultisectionId() {
		return multisectionId;
	}

	/**
	 * 
	 * @param multisectionId
	 *            The multisection_id
	 */
	@JsonProperty("multisection_id")
	public void setMultisectionId(Integer multisectionId) {
		this.multisectionId = multisectionId;
	}

	/**
	 * The total points spent creating this squadron.
	 * 
	 * @return The points
	 */
	@JsonProperty("points")
	public Integer getPoints() {
		return points;
	}

	/**
	 * The total points spent creating this squadron.
	 * 
	 * @param points
	 *            The points
	 */
	@JsonProperty("points")
	public void setPoints(Integer points) {
		this.points = points;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return PilotLookup.getInstance().lookupPilot(name);
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
		this.xwsname = name;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @return The ship
	 */
	@JsonProperty("ship")
	public String getShip() {
		return ship;
	}

	/**
	 * 
	 * (Required)
	 * 
	 * @param ship
	 *            The ship
	 */
	@JsonProperty("ship")
	public void setShip(String ship) {
		this.ship = ship;
	}

	/**
	 * Hull value for the ship
	 * 
	 * @return The hull
	 */
	@JsonProperty("hull")
	public Integer getHull() {
		if (!hullLoaded) {
			Ship foundShip = shipsLookup.findShip(ship);
			if (foundShip != null) {
				hull = foundShip.getHull();
				hullLoaded = true;
			}
		}
		return hull;
	}

	/**
	 * Hull value for the ship
	 * 
	 * @param hull
	 *            The hull
	 */
	@JsonProperty("hull")
	public void setHull(Integer hull) {
		this.hull = hull;
	}

	/**
	 * Shield value for the ship
	 * 
	 * @return The shields
	 */
	@JsonProperty("shields")
	public Integer getShields() {
		if (!shieldsLoaded) {
			Ship foundShip = shipsLookup.findShip(ship);
			if (foundShip != null) {
				shields = foundShip.getShields();
				shieldsLoaded = true;
			}
		}
		return shields;
	}

	/**
	 * Shield value for the ship
	 * 
	 * @param shields
	 *            The shields
	 */
	@JsonProperty("shields")
	public void setShields(Integer shields) {
		this.shields = shields;
	}

	/**
	 * 
	 * @return The upgrades
	 */
	@JsonProperty("upgrades")
	public Upgrades getUpgrades() {
		return upgrades;
	}

	/**
	 * 
	 * @param upgrades
	 *            The upgrades
	 */
	@JsonProperty("upgrades")
	public void setUpgrades(Upgrades upgrades) {
		this.upgrades = upgrades;
	}

	/**
	 * An extensible object containing app-specific data. Developers should put
	 * extra data here under their own namespace.
	 * 
	 * @return The vendor
	 */
	@JsonProperty("vendor")
	public Vendor getVendor() {
		return vendor;
	}

	/**
	 * An extensible object containing app-specific data. Developers should put
	 * extra data here under their own namespace.
	 * 
	 * @param vendor
	 *            The vendor
	 */
	@JsonProperty("vendor")
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getPilotId() {
		return pilotId;
	}

	public void setPilotId(String pilotId) {
		this.pilotId = pilotId;
	}

	public String getPilotSkill() {
		return pilotSkill;
	}

	public void setPilotSkill(String pilotSkill) {
		this.pilotSkill = pilotSkill;
	}
	
	public String getXwsName() {
		return xwsname;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hull == null) ? 0 : hull.hashCode());
		result = prime * result + (hullLoaded ? 1231 : 1237);
		result = prime * result + ((multisectionId == null) ? 0 : multisectionId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pilotId == null) ? 0 : pilotId.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((shields == null) ? 0 : shields.hashCode());
		result = prime * result + (shieldsLoaded ? 1231 : 1237);
		result = prime * result + ((ship == null) ? 0 : ship.hashCode());
		result = prime * result + ((shipsLookup == null) ? 0 : shipsLookup.hashCode());
		result = prime * result + ((upgrades == null) ? 0 : upgrades.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pilot other = (Pilot) obj;
		if (hull == null) {
			if (other.hull != null)
				return false;
		} else if (!hull.equals(other.hull))
			return false;
		if (hullLoaded != other.hullLoaded)
			return false;
		if (multisectionId == null) {
			if (other.multisectionId != null)
				return false;
		} else if (!multisectionId.equals(other.multisectionId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pilotId == null) {
			if (other.pilotId != null)
				return false;
		} else if (!pilotId.equals(other.pilotId))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (shields == null) {
			if (other.shields != null)
				return false;
		} else if (!shields.equals(other.shields))
			return false;
		if (shieldsLoaded != other.shieldsLoaded)
			return false;
		if (ship == null) {
			if (other.ship != null)
				return false;
		} else if (!ship.equals(other.ship))
			return false;
		if (shipsLookup == null) {
			if (other.shipsLookup != null)
				return false;
		} else if (!shipsLookup.equals(other.shipsLookup))
			return false;
		if (upgrades == null) {
			if (other.upgrades != null)
				return false;
		} else if (!upgrades.equals(other.upgrades))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}

}
