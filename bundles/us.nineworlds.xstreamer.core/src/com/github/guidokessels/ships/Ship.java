
package com.github.guidokessels.ships;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "faction",
    "attack",
    "agility",
    "hull",
    "shields",
    "actions",
    "maneuvers",
    "size",
    "xws",
    "energy",
    "epic_points",
    "maneuvers_energy"
})
public class Ship {

    @JsonProperty("name")
    private String name;
    @JsonProperty("faction")
    private List<String> faction = new ArrayList<String>();
    @JsonProperty("attack")
    private Integer attack;
    @JsonProperty("agility")
    private Integer agility;
    @JsonProperty("hull")
    private Integer hull;
    @JsonProperty("shields")
    private Integer shields;
    @JsonProperty("actions")
    private List<String> actions = new ArrayList<String>();
    @JsonProperty("maneuvers")
    private List<List<Integer>> maneuvers = new ArrayList<List<Integer>>();
    @JsonProperty("size")
    private String size;
    @JsonProperty("xws")
    private String xws;
    @JsonProperty("energy")
    private Integer energy;
    @JsonProperty("epic_points")
    private Integer epicPoints;
    @JsonProperty("maneuvers_energy")
    private List<List<Integer>> maneuversEnergy = new ArrayList<List<Integer>>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
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
     * @return
     *     The faction
     */
    @JsonProperty("faction")
    public List<String> getFaction() {
        return faction;
    }

    /**
     * 
     * @param faction
     *     The faction
     */
    @JsonProperty("faction")
    public void setFaction(List<String> faction) {
        this.faction = faction;
    }

    /**
     * 
     * @return
     *     The attack
     */
    @JsonProperty("attack")
    public Integer getAttack() {
        return attack;
    }

    /**
     * 
     * @param attack
     *     The attack
     */
    @JsonProperty("attack")
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    /**
     * 
     * @return
     *     The agility
     */
    @JsonProperty("agility")
    public Integer getAgility() {
        return agility;
    }

    /**
     * 
     * @param agility
     *     The agility
     */
    @JsonProperty("agility")
    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    /**
     * 
     * @return
     *     The hull
     */
    @JsonProperty("hull")
    public Integer getHull() {
        return hull;
    }

    /**
     * 
     * @param hull
     *     The hull
     */
    @JsonProperty("hull")
    public void setHull(Integer hull) {
        this.hull = hull;
    }

    /**
     * 
     * @return
     *     The shields
     */
    @JsonProperty("shields")
    public Integer getShields() {
        return shields;
    }

    /**
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
     *     The actions
     */
    @JsonProperty("actions")
    public List<String> getActions() {
        return actions;
    }

    /**
     * 
     * @param actions
     *     The actions
     */
    @JsonProperty("actions")
    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    /**
     * 
     * @return
     *     The maneuvers
     */
    @JsonProperty("maneuvers")
    public List<List<Integer>> getManeuvers() {
        return maneuvers;
    }

    /**
     * 
     * @param maneuvers
     *     The maneuvers
     */
    @JsonProperty("maneuvers")
    public void setManeuvers(List<List<Integer>> maneuvers) {
        this.maneuvers = maneuvers;
    }

    /**
     * 
     * @return
     *     The size
     */
    @JsonProperty("size")
    public String getSize() {
        return size;
    }

    /**
     * 
     * @param size
     *     The size
     */
    @JsonProperty("size")
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * 
     * @return
     *     The xws
     */
    @JsonProperty("xws")
    public String getXws() {
        return xws;
    }

    /**
     * 
     * @param xws
     *     The xws
     */
    @JsonProperty("xws")
    public void setXws(String xws) {
        this.xws = xws;
    }

    /**
     * 
     * @return
     *     The energy
     */
    @JsonProperty("energy")
    public Integer getEnergy() {
        return energy;
    }

    /**
     * 
     * @param energy
     *     The energy
     */
    @JsonProperty("energy")
    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    /**
     * 
     * @return
     *     The epicPoints
     */
    @JsonProperty("epic_points")
    public Integer getEpicPoints() {
        return epicPoints;
    }

    /**
     * 
     * @param epicPoints
     *     The epic_points
     */
    @JsonProperty("epic_points")
    public void setEpicPoints(Integer epicPoints) {
        this.epicPoints = epicPoints;
    }

    /**
     * 
     * @return
     *     The maneuversEnergy
     */
    @JsonProperty("maneuvers_energy")
    public List<List<Integer>> getManeuversEnergy() {
        return maneuversEnergy;
    }

    /**
     * 
     * @param maneuversEnergy
     *     The maneuvers_energy
     */
    @JsonProperty("maneuvers_energy")
    public void setManeuversEnergy(List<List<Integer>> maneuversEnergy) {
        this.maneuversEnergy = maneuversEnergy;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(faction).append(attack).append(agility).append(hull).append(shields).append(actions).append(maneuvers).append(size).append(xws).append(energy).append(epicPoints).append(maneuversEnergy).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Ship) == false) {
            return false;
        }
        Ship rhs = ((Ship) other);
        return new EqualsBuilder().append(name, rhs.name).append(faction, rhs.faction).append(attack, rhs.attack).append(agility, rhs.agility).append(hull, rhs.hull).append(shields, rhs.shields).append(actions, rhs.actions).append(maneuvers, rhs.maneuvers).append(size, rhs.size).append(xws, rhs.xws).append(energy, rhs.energy).append(epicPoints, rhs.epicPoints).append(maneuversEnergy, rhs.maneuversEnergy).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
