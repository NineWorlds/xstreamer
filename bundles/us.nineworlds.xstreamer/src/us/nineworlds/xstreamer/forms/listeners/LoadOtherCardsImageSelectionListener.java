package us.nineworlds.xstreamer.forms.listeners;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IViewPart;
import org.osgi.framework.Bundle;

import uky.article.imageviewer.views.ImageView;
import us.nineworlds.xstreamer.model.damagedeck.DamageDeck;

public class LoadOtherCardsImageSelectionListener extends AbstractImageSelectionListener implements ISelectionChangedListener {

	private static final String XSTREAMER_DAMAGEDECK_IMAGENAME = "xstreamer_damage_card.png";

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();
		if (!(selection instanceof IStructuredSelection)) {
			return;
		}

		IStructuredSelection structuredSeleciton = (IStructuredSelection) selection;
		
		if (structuredSeleciton.getFirstElement() instanceof DamageDeck) {
			DamageDeck damageDeck = (DamageDeck) structuredSeleciton.getFirstElement();
			IViewPart view = findImageViewer();
			if (view == null) {
				return;
			}			
			loadAndSaveImage(damageDeck, view);
		}
				
	}

	private void loadAndSaveImage(DamageDeck damageDeck, IViewPart view) {
		ImageView imageView = (ImageView) view;
		Bundle bundle = Platform.getBundle(XWING_DATA_BUNDLE_ID);
		IPath mapImagePath = new Path("images/" + damageDeck.getImagePath());
		URL mapImageUrl = FileLocator.find(bundle, mapImagePath, null);
		try {
			loadImage(mapImageUrl.openStream(), imageView);
			saveImage(XSTREAMER_DAMAGEDECK_IMAGENAME, imageView, SWT.IMAGE_PNG);
		} catch (IOException e) {
		}
	}
}
