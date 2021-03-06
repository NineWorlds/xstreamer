package us.nineworlds.xstreamer.ia.property;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

import us.nineworlds.xstreamer.ia.model.CommandCardTreeNode;

public class CommandCardAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IPropertySource.class) {
			return new CommandCardPropertySource((CommandCardTreeNode) adaptableObject);
		}
		
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] {IPropertySource.class};
	}

}
