package us.nineworlds.xstreamer.ia.property;

import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.github.xws.Pilot;
import com.github.xws.Upgrade;

import us.nineworlds.iadata.command.CommandCard;
import us.nineworlds.xstreamer.eventbus.EventBus;
import us.nineworlds.xstreamer.ia.events.GenerateArmyEvent;
import us.nineworlds.xstreamer.ia.model.CommandCardTreeNode;
import us.nineworlds.xstreamer.ia.model.vendoroptions.CommandCardVendorOptions;
import us.nineworlds.xstreamer.model.PilotTreeNode;
import us.nineworlds.xstreamer.model.UpgradeTypeTreeNode;
import us.nineworlds.xstreamer.property.CheckboxPropertyDescriptor;
import us.nineworlds.xstreamer.property.NumericPropertyDescriptor;

public class CommandCardPropertySource implements IPropertySource {

	private CommandCard commandCard;
	private EventBus eventBus;

	public CommandCardPropertySource(CommandCardTreeNode card) {
		this.commandCard = (CommandCard) card.getValue();
		eventBus = EventBus.getInstance();
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[] { new NumericPropertyDescriptor("cost", "Cost"),
				new NumericPropertyDescriptor("limit", "Limit"),
				new CheckboxPropertyDescriptor("discarded", "Discarded")};
	}

	@Override
	public Object getPropertyValue(Object id) {
		Map<String, Object> vendorOptions = commandCard.getVendorOptions();
		CommandCardVendorOptions options = (CommandCardVendorOptions) vendorOptions.get("xstreamerOptions");
		
		if ("cost".equals(id)) {
			return commandCard.getCost();
		}
		
		if ("limit".equals(id)) {
			return commandCard.getLimit();
		}
		
		if ("discarded".equals(id)) {
			return options.isDiscarded();
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
		Map<String, Object> vendorOptions = commandCard.getVendorOptions();
		CommandCardVendorOptions options = (CommandCardVendorOptions) vendorOptions.get("xstreamerOptions");
		
		boolean postEvent = false;
		if ("cost".equals(id)) {
			postEvent = true;
			commandCard.setCost((Integer) value);
		}
		
		if ("limit".equals(id)) {
			postEvent = true;
			commandCard.setLimit((Integer) value);
		}
		
		if ("discarded".equals(id)) {
			postEvent = true;
			options.setDiscarded((Boolean) value);
		}
		
		if (postEvent) {
			eventBus.post(new GenerateArmyEvent());
		}
	}

}
