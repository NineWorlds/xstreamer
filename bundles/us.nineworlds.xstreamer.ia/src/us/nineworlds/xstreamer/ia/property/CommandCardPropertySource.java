package us.nineworlds.xstreamer.ia.property;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.github.xws.Pilot;
import com.github.xws.Upgrade;

import us.nineworlds.iadata.command.CommandCard;
import us.nineworlds.xstreamer.ia.model.CommandCardTreeNode;
import us.nineworlds.xstreamer.model.PilotTreeNode;
import us.nineworlds.xstreamer.model.UpgradeTypeTreeNode;
import us.nineworlds.xstreamer.property.NumericPropertyDescriptor;

public class CommandCardPropertySource implements IPropertySource {

	private CommandCard commandCard;

	public CommandCardPropertySource(CommandCardTreeNode card) {
		this.commandCard = (CommandCard) card.getValue();
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] { new NumericPropertyDescriptor("cost", "Cost"),
				new NumericPropertyDescriptor("limit", "Limit")};
	}

	@Override
	public Object getPropertyValue(Object id) {
		if ("cost".equals(id)) {
			return commandCard.getCost();
		}
		
		if ("limit".equals(id)) {
			return commandCard.getLimit();
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
		if ("cost".equals(id)) {
			commandCard.setCost((Integer) value);
		}
		
		if ("limit".equals(id)) {
			commandCard.setLimit((Integer) value);
		}		
	}

}
