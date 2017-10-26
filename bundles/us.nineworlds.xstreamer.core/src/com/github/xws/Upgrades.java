
package com.github.xws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({

})
public class Upgrades {
	
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonIgnore
    private List<Upgrade> allUpgrades = new ArrayList<>();

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
        
        if (value instanceof List) {
        	List<String> upgrades = (List<String>) value;
        	for (String upgrade : upgrades) {
        		Upgrade upg = new Upgrade();
        		upg.setType(name);
        		upg.setXwsspecname(upgrade);
        		upg.toggleDiscardFlag(false);
        		allUpgrades.add(upg);
        	}
        }
    }
    
    public Upgrade findUpgrade(String xwsspecname) {
    	for(Upgrade upgrade : allUpgrades) {
    		if (upgrade.getXwsspecname().equals(xwsspecname)) {
    			return upgrade;
    		}
    	}
    	return null;
    }
    
    public void updateUpgrade(Upgrade upgrade) {
    	for(int i = 0; i < allUpgrades.size(); i++) {
    		Upgrade upg = allUpgrades.get(i);
    		if (upg.getXwsspecname().equals(upgrade.getXwsspecname())) {
    			upg.toggleDiscardFlag(upgrade.isDiscarded());
    			return;
    		}
    	}
    	
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Upgrades) == false) {
            return false;
        }
        Upgrades rhs = ((Upgrades) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
