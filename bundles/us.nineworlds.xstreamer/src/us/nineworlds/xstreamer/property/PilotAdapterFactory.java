package us.nineworlds.xstreamer.property;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

import com.github.xws.Pilot;

import us.nineworlds.xstreamer.model.PilotTreeNode;

public class PilotAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IPropertySource.class) {
			return new PilotPropertySource((PilotTreeNode) adaptableObject);
		}
		
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] {IPropertySource.class};
	}

}
