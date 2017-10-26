package us.nineworlds.xstreamer.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.github.xws.Pilot;
import com.github.xws.Upgrade;

import us.nineworlds.xstreamer.model.PilotTreeNode;
import us.nineworlds.xstreamer.model.UpgradeTypeTreeNode;

public class UpgradeTypePropertySource implements IPropertySource {

	private Upgrade upgrade;

	public UpgradeTypePropertySource(UpgradeTypeTreeNode type) {
		this.upgrade = type.getUpgrade();
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] { new CheckboxPropertyDescriptor("discarded", "Discarded") };
	}

	@Override
	public Object getPropertyValue(Object id) {
		if ("discarded".equals(id)) {
			return upgrade.isDiscarded();
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
		if ("discarded".equals(id) && value instanceof Boolean) {
			upgrade.toggleDiscardFlag(Boolean.valueOf((Boolean) value));
		}
	}

}
