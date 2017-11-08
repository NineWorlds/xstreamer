package us.nineworlds.xstreamer.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import com.github.xws.Upgrade;

import us.nineworlds.xstreamer.eventbus.EventBus;
import us.nineworlds.xstreamer.eventbus.GenerateSquadJobEvent;
import us.nineworlds.xstreamer.model.UpgradeTypeTreeNode;

public class UpgradeTypePropertySource implements IPropertySource {

	private Upgrade upgrade;
	private EventBus eventBus;

	public UpgradeTypePropertySource(UpgradeTypeTreeNode type) {
		this.upgrade = type.getUpgrade();
		eventBus = EventBus.getInstance();		
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] { new NumericPropertyDescriptor("quantity", "Quantity"),
				new CheckboxPropertyDescriptor("discarded", "Discarded"),
};
	}

	@Override
	public Object getPropertyValue(Object id) {
		if ("discarded".equals(id)) {
			return upgrade.isDiscarded();
		}
		
		if ("quantity".equals(id)) {
			return upgrade.getQuantity();
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
		boolean postJob = false;
		
		if ("quantity".equals(id) && value instanceof Integer) {
			postJob = true;
			upgrade.setQuantity((Integer) value);
		}
		
		if ("discarded".equals(id) && value instanceof Boolean) {
			postJob = true;
			upgrade.toggleDiscardFlag((Boolean) value);
		}
		
		if (postJob) {
			postGenerateJobEvent();
		}
		
	}
	
	private void postGenerateJobEvent() {
		eventBus.post(new GenerateSquadJobEvent());
	}
	
}
