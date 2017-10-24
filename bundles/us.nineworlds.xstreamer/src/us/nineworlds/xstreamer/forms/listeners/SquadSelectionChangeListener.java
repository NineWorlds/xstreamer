package us.nineworlds.xstreamer.forms.listeners;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

import com.github.xws.Pilot;
import com.github.xws.Upgrades;

import uky.article.imageviewer.views.ImageView;
import us.nineworlds.xstreamer.forms.AbstractPlayerFormPage;
import us.nineworlds.xstreamer.model.PilotTreeNode;
import us.nineworlds.xstreamer.model.UpgradeTreeNode;
import us.nineworlds.xstreamer.model.UpgradeTypeTreeNode;
import us.nineworlds.xstreamer.model.lookup.PilotLookup;
import us.nineworlds.xstreamer.model.lookup.UpgradeLookup;

public class SquadSelectionChangeListener implements ISelectionChangedListener {
	
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
			IViewPart view = activePage.findView("us.nineworlds.xstreamer.imageview");
			Bundle dataBundle = Platform.getBundle("us.nineworlds.xstreamer.data");
			
			if (structureSelection.getFirstElement() instanceof PilotTreeNode) {
				PilotTreeNode pilot = (PilotTreeNode) structureSelection.getFirstElement();
				Pilot p = (Pilot) pilot.getValue();
				page.pilotId.setText(p.getPilotId() != null ? p.getPilotId() : "");
				page.pilotSkillText.setText(p.getPilotSkill() != null ? p.getPilotSkill() : "");
				if (p.getHull() != null) {
					page.hullText.setText(Integer.toString(p.getHull()));					
				} else {
					page.hullText.setText("");
				}
				if (p.getShields() != null) {
					page.shieldText.setText(Integer.toString(p.getShields()));					
				} else {
					page.shieldText.setText("");
				}
				page.totalShipPoints.setText("Points: " + Integer.toString(p.getPoints()));
				page.totalShipPoints.getParent().requestLayout();
				
				if (view != null) {
					loadPilotImage(view, dataBundle, p);
				}				
			} else if (structureSelection instanceof TreeSelection) {
				try {
					UpgradeTypeTreeNode upgradeNode = (UpgradeTypeTreeNode) structureSelection.getFirstElement();
					loadUpgradeImage(view, dataBundle, (String)upgradeNode.getValue());
				} catch (ClassCastException ex) {
					
				}
				
			}
		};
	}
	
	private void loadUpgradeImage(IViewPart view, Bundle dataBundle, String xwsupgradeName) {
		com.github.guidokessels.ships.Upgrades upgrade = UpgradeLookup.getInstance().findShip(xwsupgradeName);
		if (upgrade == null) {
			return;
		}
		ImageView imageView = (ImageView) view;					
		IPath pilotImage = new Path("images/" + upgrade.getImagePath());
		URL pilotImageUrl = FileLocator.find(dataBundle, pilotImage, null);
		
		loadImage(imageView, pilotImageUrl);
	}


	private void loadPilotImage(IViewPart view, Bundle dataBundle, Pilot p) {
		com.github.guidokessels.ships.Pilot lookupPilot = PilotLookup.getInstance().lookupPilotValue(p.getXwsName());
		ImageView imageView = (ImageView) view;					
		IPath pilotImage = new Path("images/" + lookupPilot.getImagePath());
		URL pilotImageUrl = FileLocator.find(dataBundle, pilotImage, null);
		
		loadImage(imageView, pilotImageUrl);
	}

	private void loadImage(ImageView imageView, URL pilotImageUrl) {
		try {
			imageView.imageCanvas.loadImage(pilotImageUrl.openStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
