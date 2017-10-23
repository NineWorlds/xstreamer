package us.nineworlds.xstreamer.ia.listeners;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;


import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.xstreamer.ia.forms.AbstractPlayerFormPage;
import us.nineworlds.xstreamer.ia.model.DeploymentTreeNode;

public class UpdateDeploymentButtonSelectionListener implements SelectionListener {

	AbstractPlayerFormPage page;
	
	public UpdateDeploymentButtonSelectionListener(AbstractPlayerFormPage page) {
		this.page = page;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		IStructuredSelection structuredSelection = (IStructuredSelection) page.treeViewer.getSelection();
		if (structuredSelection.getFirstElement() instanceof DeploymentTreeNode) {
			DeploymentTreeNode pilotTreeNode = (DeploymentTreeNode) structuredSelection.getFirstElement();
			Deployment pilot = (Deployment) pilotTreeNode.getValue();
			pilot.setHealth(Integer.parseInt(page.health.getText()));
			pilot.setSpeed(Integer.parseInt(page.speed.getText()));
		}
		
//		if (page.getPlayerModel() != null) {
//			GenerateSquadJob job = new GenerateSquadJob("generateSquad", page.getPlayerModel(), page.playerFileName()
//					, page.squadTemplate());
//			job.schedule();
//		}

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

}
