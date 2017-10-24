package us.nineworlds.xstreamer.ia.listeners;

import java.net.URL;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import uky.article.imageviewer.views.ImageView;
import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.xstreamer.ia.core.Activator;
import us.nineworlds.xstreamer.ia.forms.AbstractPlayerFormPage;
import us.nineworlds.xstreamer.ia.lookup.DeploymentsLookup;
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
				
				DeploymentsLookup dbLookup = Activator.getDefault().getDeploymentsLookup();
				Deployment foundDeployment = dbLookup.findDeploymentCard(p.getIaspecname(), p.getFaction().toString());
				if (foundDeployment == null) {
					return;
				}
				
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IViewPart view = page.findView("us.nineworlds.xstreamer.imageview");
				if (view != null) {
					ImageView imageView = (ImageView) view;
					try {
						imageView.imageCanvas.loadImage(new URL(foundDeployment.getImageurl()));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
			
		}
	}

}
