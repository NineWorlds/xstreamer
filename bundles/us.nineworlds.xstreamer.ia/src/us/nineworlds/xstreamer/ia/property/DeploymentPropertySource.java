package us.nineworlds.xstreamer.ia.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.xstreamer.eventbus.EventBus;
import us.nineworlds.xstreamer.ia.model.DeploymentTreeNode;

public class DeploymentPropertySource implements IPropertySource {

	private Deployment deployment;
	private EventBus eventBus;
	
	public DeploymentPropertySource(DeploymentTreeNode deployment) {
		this.deployment = (Deployment) deployment.getValue();
		eventBus = EventBus.getInstance();
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] {
				new TextPropertyDescriptor("health", "Health"),
				new TextPropertyDescriptor("speed", "Speed"),
				new TextPropertyDescriptor("units", "Units"),
				new TextPropertyDescriptor("renforcement", "Unit Cost"),
				new TextPropertyDescriptor("deploymentCost", "Deployment Cost")
		};
	}

	@Override
	public Object getPropertyValue(Object id) {
				
		if ("health".equals(id)) {
			return deployment.getHealth();
		}
		
		if ("speed".equals(id)) {
			return deployment.getSpeed();
		}
		
		if ("units".equals(id)) {
			return deployment.getUnitsInGroup();
		}
		
		if ("renforcement".equals(id)) {
			return deployment.getReenforcementCost();
		}
		
		if ("deploymentCost".equals(id)) {
			return deployment.getDeploymentCost();
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
		if ("health".equals(id)) {
			deployment.setHealth(Integer.valueOf(newValue));
		}
		
		if ("speed".equals(id)) {
			deployment.setSpeed(Integer.valueOf(newValue));
		}
		
		if ("units".equals(id)) {
			deployment.setUnitsInGroup(Integer.valueOf(newValue));
		}
	}

}
