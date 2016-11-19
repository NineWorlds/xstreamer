
package com.github.xws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * X-Wing Squadron Format Schema
 * <p>
 * A squadron for the X-Wing Miniatures Game in app-independent format for sharing, saving and moving between apps.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "version",
    "name",
    "points",
    "faction",
    "description",
    "obstacles",
    "pilots",
    "vendor"
})
public class XwsSpec {

    /**
     * The version of the XWS spec used to create this data
     * (Required)
     * 
     */
    @JsonProperty("version")
    @JsonPropertyDescription("")
    private String version;
    /**
     * The name of the squadron.
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("")
    private String name;
    /**
     * The total points spent creating this squadron.
     * 
     */
    @JsonProperty("points")
    @JsonPropertyDescription("")
    private Integer points;
    /**
     * The faction this squadron belongs to.
     * (Required)
     * 
     */
    @JsonProperty("faction")
    @JsonPropertyDescription("")
    private XwsSpec.Faction faction;
    /**
     * A description of this squadron.
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("")
    private String description;
    /**
     * Array of three Strings, each being an identifier for the obstacle chosen for tournament use.
     * 
     */
    @JsonProperty("obstacles")
    @JsonPropertyDescription("")
    private List<String> obstacles = new ArrayList<String>();
    /**
     * The members of this squadron.
     * (Required)
     * 
     */
    @JsonProperty("pilots")
    @JsonPropertyDescription("")
    private List<Pilot> pilots = new ArrayList<Pilot>();
    /**
     * An extensible object containing app-specific data. Developers should put extra data here under their own namespace.
     * 
     */
    @JsonProperty("vendor")
    @JsonPropertyDescription("")
    private Vendor_ vendor;

    /**
     * The version of the XWS spec used to create this data
     * (Required)
     * 
     * @return
     *     The version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * The version of the XWS spec used to create this data
     * (Required)
     * 
     * @param version
     *     The version
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * The name of the squadron.
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * The name of the squadron.
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
     * The faction this squadron belongs to.
     * (Required)
     * 
     * @return
     *     The faction
     */
    @JsonProperty("faction")
    public XwsSpec.Faction getFaction() {
        return faction;
    }

    /**
     * The faction this squadron belongs to.
     * (Required)
     * 
     * @param faction
     *     The faction
     */
    @JsonProperty("faction")
    public void setFaction(XwsSpec.Faction faction) {
        this.faction = faction;
    }

    /**
     * A description of this squadron.
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * A description of this squadron.
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Array of three Strings, each being an identifier for the obstacle chosen for tournament use.
     * 
     * @return
     *     The obstacles
     */
    @JsonProperty("obstacles")
    public List<String> getObstacles() {
        return obstacles;
    }

    /**
     * Array of three Strings, each being an identifier for the obstacle chosen for tournament use.
     * 
     * @param obstacles
     *     The obstacles
     */
    @JsonProperty("obstacles")
    public void setObstacles(List<String> obstacles) {
        this.obstacles = obstacles;
    }

    /**
     * The members of this squadron.
     * (Required)
     * 
     * @return
     *     The pilots
     */
    @JsonProperty("pilots")
    public List<Pilot> getPilots() {
        return pilots;
    }

    /**
     * The members of this squadron.
     * (Required)
     * 
     * @param pilots
     *     The pilots
     */
    @JsonProperty("pilots")
    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }

    /**
     * An extensible object containing app-specific data. Developers should put extra data here under their own namespace.
     * 
     * @return
     *     The vendor
     */
    @JsonProperty("vendor")
    public Vendor_ getVendor() {
        return vendor;
    }

    /**
     * An extensible object containing app-specific data. Developers should put extra data here under their own namespace.
     * 
     * @param vendor
     *     The vendor
     */
    @JsonProperty("vendor")
    public void setVendor(Vendor_ vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(version).append(name).append(points).append(faction).append(description).append(obstacles).append(pilots).append(vendor).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof XwsSpec) == false) {
            return false;
        }
        XwsSpec rhs = ((XwsSpec) other);
        return new EqualsBuilder().append(version, rhs.version).append(name, rhs.name).append(points, rhs.points).append(faction, rhs.faction).append(description, rhs.description).append(obstacles, rhs.obstacles).append(pilots, rhs.pilots).append(vendor, rhs.vendor).isEquals();
    }

    public enum Faction {

        REBEL("rebel"),
        IMPERIAL("imperial"),
        SCUM("scum");
        private final String value;
        private final static Map<String, XwsSpec.Faction> CONSTANTS = new HashMap<String, XwsSpec.Faction>();

        static {
            for (XwsSpec.Faction c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Faction(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static XwsSpec.Faction fromValue(String value) {
            XwsSpec.Faction constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
