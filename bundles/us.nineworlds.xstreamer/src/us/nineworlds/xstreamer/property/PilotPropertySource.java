package us.nineworlds.xstreamer.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.github.xws.Pilot;

import us.nineworlds.xstreamer.eventbus.EventBus;
import us.nineworlds.xstreamer.eventbus.GenerateSquadJobEvent;
import us.nineworlds.xstreamer.model.PilotTreeNode;

public class PilotPropertySource implements IPropertySource {

	private Pilot pilot;
	private EventBus eventBus;
	
	public PilotPropertySource(PilotTreeNode pilot) {
		this.pilot = (Pilot) pilot.getValue();
		eventBus = EventBus.getInstance();
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
				new TextPropertyDescriptor("shields", "Shields"),
				new CheckboxPropertyDescriptor("critical", "Critical Damage")
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
		
		if ("critical".equals(id)) {
			return pilot.hasCriticalDamage();
		}
		
		return null;
	}

	private void postGenerateJobEvent() {
		eventBus.post(new GenerateSquadJobEvent());
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
		boolean postJob = false;
		if ("skill".equals(id)) {
			postJob = true;
			pilot.setPilotSkill(newValue);
		}
		
		if ("id".equals(id)) {
			postJob = true;
			pilot.setPilotId(newValue);
			
		}
		
		if ("hull".equals(id)) {
			postJob = true;
			pilot.setHull(Integer.parseInt(newValue));
		}
		
		if ("shields".equals(id)) {
			postJob = true;
			pilot.setShields(Integer.parseInt(newValue));
		}
		
		if ("critical".equals(id) && value instanceof Boolean) {
			pilot.setCriticalDamage(Boolean.valueOf((Boolean) value));
		}
		
		if (postJob) {
			postGenerateJobEvent();
		}
	}

}
