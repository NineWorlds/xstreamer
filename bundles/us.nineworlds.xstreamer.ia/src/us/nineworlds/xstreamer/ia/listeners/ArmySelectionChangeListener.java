package us.nineworlds.xstreamer.ia.listeners;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.xstreamer.ia.forms.AbstractPlayerFormPage;
import us.nineworlds.xstreamer.ia.model.DeploymentTreeNode;


public class ArmySelectionChangeListener implements ISelectionChangedListener {
	
	AbstractPlayerFormPage page;
	
	public ArmySelectionChangeListener(AbstractPlayerFormPage page) {
		this.page = page;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structureSelection = (IStructuredSelection) selection;
			
			if (structureSelection.getFirstElement() instanceof DeploymentTreeNode) {
				DeploymentTreeNode deployment = (DeploymentTreeNode) structureSelection.getFirstElement();
				Deployment p = (Deployment) deployment.getValue();
				page.squadID.setText("");
				page.health.setText(Integer.toString(p.getHealth()));					
				page.speed.setText(Integer.toString(p.getSpeed()));									
			}
		};
	}

}
