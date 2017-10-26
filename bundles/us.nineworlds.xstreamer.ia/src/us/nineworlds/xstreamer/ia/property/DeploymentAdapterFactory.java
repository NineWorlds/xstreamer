package us.nineworlds.xstreamer.ia.property;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

import us.nineworlds.xstreamer.ia.model.DeploymentTreeNode;

public class DeploymentAdapterFactory implements IAdapterFactory {


	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IPropertySource.class) {
			return new DeploymentPropertySource((DeploymentTreeNode) adaptableObject);
		}
		
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] {IPropertySource.class};
	}

}
