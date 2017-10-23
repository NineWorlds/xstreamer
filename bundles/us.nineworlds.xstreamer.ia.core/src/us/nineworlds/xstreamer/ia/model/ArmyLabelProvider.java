package us.nineworlds.xstreamer.ia.model;

import org.eclipse.jface.viewers.LabelProvider;

import us.nineworlds.iadata.IASpec;
import us.nineworlds.iadata.deployment.Deployment;

public class ArmyLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof IASpec) {
			IASpec xwsspec = (IASpec) element;
			return xwsspec.getArmy().getName();
		}
		
		if (element instanceof Deployment) {
			Deployment deployment = (Deployment) element;
			return deployment.getName() + " " + deployment.getDeploymentCost();
		}
		
		return element.toString();
	}
}
