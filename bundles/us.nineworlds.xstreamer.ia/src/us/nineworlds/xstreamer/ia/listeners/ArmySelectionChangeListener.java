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
import us.nineworlds.iadata.command.CommandCard;
import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.xstreamer.ia.core.Activator;
import us.nineworlds.xstreamer.ia.forms.AbstractPlayerFormPage;
import us.nineworlds.xstreamer.ia.lookup.CommandCardLookup;
import us.nineworlds.xstreamer.ia.lookup.DeploymentsLookup;
import us.nineworlds.xstreamer.ia.model.CommandCardTreeNode;
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
				
				DeploymentsLookup dbLookup = Activator.getDefault().getDeploymentsLookup();
				Deployment foundDeployment = dbLookup.findDeploymentCard(p.getIaspecname(), p.getFaction().toString());
				if (foundDeployment == null) {
					return;
				}
				
				IViewPart view = findImageViewer();
				loadImage(foundDeployment.getImageurl(), view);
			}
			
			if (structureSelection.getFirstElement() instanceof CommandCardTreeNode) {
				CommandCardTreeNode node = (CommandCardTreeNode) structureSelection.getFirstElement();
				CommandCard card = (CommandCard) node.getValue();
				CommandCardLookup cardLookup = Activator.getDefault().getCommandCardLookup();
				CommandCard commandCard = cardLookup.findCommandCard(card.getIaspecname(), card.getFaction().toString());
				if (commandCard == null) {
					return;
				}
				
				IViewPart view = findImageViewer();
				loadImage(commandCard.getImageurl(), view);				
			}
			
		}
	}

	private IViewPart findImageViewer() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = page.findView("us.nineworlds.xstreamer.imageview");
		return view;
	}

	private void loadImage(String imageUrl, IViewPart view) {
		if (view != null) {
			ImageView imageView = (ImageView) view;
			try {
				imageView.imageCanvas.loadImage(new URL(imageUrl));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
