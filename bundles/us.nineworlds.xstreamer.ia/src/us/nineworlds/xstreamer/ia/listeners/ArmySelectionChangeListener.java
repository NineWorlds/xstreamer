package us.nineworlds.xstreamer.ia.listeners;

import java.net.URL;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IViewPart;

import uky.article.imageviewer.views.ImageView;
import us.nineworlds.iadata.command.CommandCard;
import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.xstreamer.Logger;
import us.nineworlds.xstreamer.ia.core.Activator;
import us.nineworlds.xstreamer.ia.lookup.CommandCardLookup;
import us.nineworlds.xstreamer.ia.lookup.DeploymentsLookup;
import us.nineworlds.xstreamer.ia.model.CommandCardTreeNode;
import us.nineworlds.xstreamer.ia.model.DeploymentTreeNode;

public class ArmySelectionChangeListener extends AbstractImageSelectionListener implements ISelectionChangedListener {

	private static final String XSTREAMER_COMMAND_CARD_IMAGE_NAME = "xstreamer_command_card.png";
	private static final String XSTREAMER_DEPLOYMENT_CARD_IMAGE_NAME = "xstreamer_deployment_card.png";

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();
		if (!(selection instanceof IStructuredSelection)) {
			return;
		}
		IStructuredSelection structureSelection = (IStructuredSelection) selection;

		if (structureSelection.getFirstElement() instanceof DeploymentTreeNode) {
			processDeploymentTreeNode(structureSelection);
		}

		if (structureSelection.getFirstElement() instanceof CommandCardTreeNode) {
			processCommandCardTreeNode(structureSelection);
		}

	}

	private void processCommandCardTreeNode(IStructuredSelection structureSelection) {
		CommandCardTreeNode node = (CommandCardTreeNode) structureSelection.getFirstElement();
		CommandCard card = (CommandCard) node.getValue();
		CommandCardLookup cardLookup = Activator.getDefault().getCommandCardLookup();
		CommandCard commandCard = cardLookup.findCommandCard(card.getIaspecname(), card.getFaction().toString());
		if (commandCard != null) {
			IViewPart view = findImageViewer();
			loadImage(commandCard.getImageurl(), view);
			saveImage(XSTREAMER_COMMAND_CARD_IMAGE_NAME, (ImageView) view, SWT.IMAGE_PNG);
		}
	}

	private void processDeploymentTreeNode(IStructuredSelection structureSelection) {
		DeploymentTreeNode deployment = (DeploymentTreeNode) structureSelection.getFirstElement();
		Deployment p = (Deployment) deployment.getValue();

		DeploymentsLookup dbLookup = Activator.getDefault().getDeploymentsLookup();
		Deployment foundDeployment = dbLookup.findDeploymentCard(p.getIaspecname(), p.getFaction().toString());
		if (foundDeployment != null) {
			IViewPart view = findImageViewer();
			loadImage(foundDeployment.getImageurl(), view);
			saveImage(XSTREAMER_DEPLOYMENT_CARD_IMAGE_NAME, (ImageView) view, SWT.IMAGE_PNG);
		}
	}

	private void loadImage(String imageUrl, IViewPart view) {
		if (view != null) {
			ImageView imageView = (ImageView) view;
			try {
				imageView.imageCanvas.loadImage(new URL(imageUrl));
			} catch (Exception ex) {
				Logger.info("Unable to load image");
			}
		}
	}
}
