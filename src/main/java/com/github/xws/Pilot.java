
package com.github.xws;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import us.nineworlds.xstreamer.model.lookup.PilotLookup;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "multisection_id",
    "points",
    "name",
    "ship",
    "hull",
    "shields",
    "upgrades",
    "vendor"
})
public class Pilot {
   

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
     * An extensible object containing app-specific data. Developers should put extra data here under their own namespace.
     * 
     */
    @JsonProperty("vendor")
    @JsonPropertyDescription("")
    private Vendor vendor;

    /**
     * 
     * @return
     *     The multisectionId
     */
    @JsonProperty("multisection_id")
    public Integer getMultisectionId() {
        return multisectionId;
    }

    /**
     * 
     * @param multisectionId
     *     The multisection_id
     */
    @JsonProperty("multisection_id")
    public void setMultisectionId(Integer multisectionId) {
        this.multisectionId = multisectionId;
    }

    /**
     * The total points spent creating this squadron.
     * 
     * @return
     *     The points
     */
    @JsonProperty("points")
    public Integer getPoints() {
        return points;
    }

    /**
     * The total points spent creating this squadron.
     * 
     * @param points
     *     The points
     */
    @JsonProperty("points")
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The name
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
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The ship
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
     *     The ship
     */
    @JsonProperty("ship")
    public void setShip(String ship) {
        this.ship = ship;
    }

    /**
     * Hull value for the ship
     * 
     * @return
     *     The hull
     */
    @JsonProperty("hull")
    public Integer getHull() {
        return hull;
    }

    /**
     * Hull value for the ship
     * 
     * @param hull
     *     The hull
     */
    @JsonProperty("hull")
    public void setHull(Integer hull) {
        this.hull = hull;
    }

    /**
     * Shield value for the ship
     * 
     * @return
     *     The shields
     */
    @JsonProperty("shields")
    public Integer getShields() {
        return shields;
    }

    /**
     * Shield value for the ship
     * 
     * @param shields
     *     The shields
     */
    @JsonProperty("shields")
    public void setShields(Integer shields) {
        this.shields = shields;
    }

    /**
     * 
     * @return
     *     The upgrades
     */
    @JsonProperty("upgrades")
    public Upgrades getUpgrades() {
        return upgrades;
    }

    /**
     * 
     * @param upgrades
     *     The upgrades
     */
    @JsonProperty("upgrades")
    public void setUpgrades(Upgrades upgrades) {
        this.upgrades = upgrades;
    }

    /**
     * An extensible object containing app-specific data. Developers should put extra data here under their own namespace.
     * 
     * @return
     *     The vendor
     */
    @JsonProperty("vendor")
    public Vendor getVendor() {
        return vendor;
    }

    /**
     * An extensible object containing app-specific data. Developers should put extra data here under their own namespace.
     * 
     * @param vendor
     *     The vendor
     */
    @JsonProperty("vendor")
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(multisectionId).append(points).append(name).append(ship).append(hull).append(shields).append(upgrades).append(vendor).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Pilot) == false) {
            return false;
        }
        Pilot rhs = ((Pilot) other);
        return new EqualsBuilder().append(multisectionId, rhs.multisectionId).append(points, rhs.points).append(name, rhs.name).append(ship, rhs.ship).append(hull, rhs.hull).append(shields, rhs.shields).append(upgrades, rhs.upgrades).append(vendor, rhs.vendor).isEquals();
    }

}
