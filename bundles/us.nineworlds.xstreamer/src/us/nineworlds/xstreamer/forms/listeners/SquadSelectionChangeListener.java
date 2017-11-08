package us.nineworlds.xstreamer.forms.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

import com.github.xws.Pilot;
import com.github.xws.Upgrade;

import uky.article.imageviewer.views.ImageView;
import us.nineworlds.xstreamer.Logger;
import us.nineworlds.xstreamer.forms.AbstractPlayerFormPage;
import us.nineworlds.xstreamer.model.PilotTreeNode;
import us.nineworlds.xstreamer.model.UpgradeTypeTreeNode;
import us.nineworlds.xstreamer.model.lookup.PilotLookup;
import us.nineworlds.xstreamer.model.lookup.UpgradeLookup;

public class SquadSelectionChangeListener implements ISelectionChangedListener {
	
	private static final String XWING_DATA_BUNDLE_ID = "us.nineworlds.xstreamer.data";
	private static final String IMAGEVIEW_BUNDLE_ID = "us.nineworlds.xstreamer.imageview";
	private static final String XSTREAMER_XWING_PILOT_IMAGE_NAME = "xstreamer_xwing_pilot.png";
	private static final String XSTREAMER_XWING_UPGRADE_CARD_IMAGE_NAME = "xstreamer_xwing_upgrade_card.png";
	AbstractPlayerFormPage page;
	
	public SquadSelectionChangeListener(AbstractPlayerFormPage page) {
		this.page = page;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structureSelection = (IStructuredSelection) selection;
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IViewPart view = activePage.findView(IMAGEVIEW_BUNDLE_ID);
			Bundle dataBundle = Platform.getBundle(XWING_DATA_BUNDLE_ID);
			
			if (structureSelection.getFirstElement() instanceof PilotTreeNode) {
				PilotTreeNode pilot = (PilotTreeNode) structureSelection.getFirstElement();
				Pilot p = (Pilot) pilot.getValue();
				
				if (view != null) {
					loadPilotImage(view, dataBundle, p);
				}				
			} else if (structureSelection instanceof TreeSelection) {
				try {
					UpgradeTypeTreeNode upgradeNode = (UpgradeTypeTreeNode) structureSelection.getFirstElement();
					loadUpgradeImage(view, dataBundle, (Upgrade)upgradeNode.getValue());
				} catch (ClassCastException ex) {
				}
			}
		}
	}
	
	private void loadUpgradeImage(IViewPart view, Bundle dataBundle, Upgrade upg) {
		com.github.guidokessels.ships.Upgrades upgrade = UpgradeLookup.getInstance().findUpgrade(upg.getXwsspecname());
		if (upgrade == null) {
			return;
		}
		ImageView imageView = (ImageView) view;					
		IPath upgradeImage = new Path("images/" + upgrade.getImagePath());
		URL upgradeImageUrl = FileLocator.find(dataBundle, upgradeImage, null);
		
		loadImage(imageView, upgradeImageUrl);
		saveImage(XSTREAMER_XWING_UPGRADE_CARD_IMAGE_NAME, imageView);
	}


	private void loadPilotImage(IViewPart view, Bundle dataBundle, Pilot p) {
		com.github.guidokessels.ships.Pilot lookupPilot = PilotLookup.getInstance().lookupPilotValue(p.getXwsName());
		ImageView imageView = (ImageView) view;					
		IPath pilotImage = new Path("images/" + lookupPilot.getImagePath());
		URL pilotImageUrl = FileLocator.find(dataBundle, pilotImage, null);
		
		loadImage(imageView, pilotImageUrl);
		saveImage(XSTREAMER_XWING_PILOT_IMAGE_NAME, imageView);
	}

	private void loadImage(ImageView imageView, URL pilotImageUrl) {
		try {
			imageView.imageCanvas.loadImage(pilotImageUrl.openStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void saveImage(String filename, ImageView imageView) {
		IPreferenceStore store = us.nineworlds.xstreamer.Activator.getDefault().getPreferenceStore();
		String outputDirectory = store.getString(us.nineworlds.xstreamer.preferences.PreferenceConstants.TEMPLATE_XSTREAMER_XWING_OUTPUT_DIRECTORY);
		if (outputDirectory == null || imageView == null) {
			return;
		}
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.data = new ImageData[] { imageView.imageCanvas.getImageData() };		
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File(outputDirectory, filename));
			imageLoader.save(outputStream, SWT.IMAGE_PNG);
		} catch (IOException e) {
			Logger.error("Unable to save image to file: " + filename, e);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
	}
}
