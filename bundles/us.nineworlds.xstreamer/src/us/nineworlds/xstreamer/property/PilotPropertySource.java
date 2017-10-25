package us.nineworlds.xstreamer.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.github.xws.Pilot;

import us.nineworlds.xstreamer.model.PilotTreeNode;

public class PilotPropertySource implements IPropertySource {

	private Pilot pilot;
	
	public PilotPropertySource(PilotTreeNode pilot) {
		this.pilot = (Pilot) pilot.getValue();
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] {
				new TextPropertyDescriptor("skill", "Skill"),
				new TextPropertyDescriptor("id", "Unique ID"),
				new TextPropertyDescriptor("hull", "Hull"),
				new TextPropertyDescriptor("shields", "Shields")
		};
	}

	@Override
	public Object getPropertyValue(Object id) {
		if ("skill".equals(id)) {
			return pilot.getPilotSkill() == null || pilot.getPilotSkill().isEmpty() ? " " : pilot.getPilotSkill();
		}
		
		if ("id".equals(id)) {
			return pilot.getPilotId() == null || pilot.getPilotId().isEmpty() ? " " : pilot.getPilotId();
		}
		
		if ("hull".equals(id)) {
			return pilot.getHull().toString();
		}
		
		if ("shields".equals(id)) {
			return pilot.getShields().toString();
		}
		
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		String newValue = (String) value;
		if ("skill".equals(id)) {
			pilot.setPilotSkill(newValue);
		}
		
		if ("id".equals(id)) {
			pilot.setPilotId(newValue);
			
		}
		
		if ("hull".equals(id)) {
			pilot.setHull(Integer.parseInt(newValue));
		}
		
		if ("shields".equals(id)) {
			pilot.setShields(Integer.parseInt(newValue));
		}
	}

}
