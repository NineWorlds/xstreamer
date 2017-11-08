package us.nineworlds.xstreamer.ia.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import uky.article.imageviewer.views.ImageView;
import us.nineworlds.iadata.command.CommandCard;
import us.nineworlds.iadata.deployment.Deployment;
import us.nineworlds.xstreamer.Logger;
import us.nineworlds.xstreamer.ia.core.Activator;
import us.nineworlds.xstreamer.ia.forms.AbstractPlayerFormPage;
import us.nineworlds.xstreamer.ia.lookup.CommandCardLookup;
import us.nineworlds.xstreamer.ia.lookup.DeploymentsLookup;
import us.nineworlds.xstreamer.ia.model.CommandCardTreeNode;
import us.nineworlds.xstreamer.ia.model.DeploymentTreeNode;
import us.nineworlds.xstreamer.ia.preferences.PreferenceConstants;


public class ArmySelectionChangeListener implements ISelectionChangedListener {
	
	private static final String XSTREAMER_COMMAND_CARD_IMAGE_NAME = "xstreamer_command_card.png";
	private static final String XSTREAMER_DEPLOYMENT_CARD_IMAGE_NAME = "xstreamer_deployment_card.png";
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
				saveImage(XSTREAMER_DEPLOYMENT_CARD_IMAGE_NAME, (ImageView) view);
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
				saveImage(XSTREAMER_COMMAND_CARD_IMAGE_NAME, (ImageView) view);
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
	
	private void saveImage(String filename, ImageView imageView) {
		IPreferenceStore store = us.nineworlds.xstreamer.ia.Activator.getDefault().getPreferenceStore();
		String outputDirectory = store.getString(PreferenceConstants.TEMPLATE_XSTREAMER_IA_OUTPUT_DIRECTORY);
		if (outputDirectory == null || imageView == null) {
			return;
		}

		ImageLoader imageLoader = new ImageLoader();
		imageLoader.data = new ImageData[] { imageView.imageCanvas.getImageData() };		
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File(outputDirectory, filename));
			imageLoader.save(outputStream, SWT.IMAGE_PNG);
		} catch (FileNotFoundException e) {
			Logger.error("Unable to save image to file: " + filename, e);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}				
	}

}
