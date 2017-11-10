package us.nineworlds.xstreamer.ia.listeners;

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
import us.nineworlds.xstreamer.ia.model.maps.MapMetaData;

public class LoadMapImageSelectionListener extends AbstractImageSelectionListener implements ISelectionChangedListener {

	private static final String XSTREAMER_SKIRMISH_MAP_IMAGENAME = "xstreamer_skirmish_map.png";

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();
		if (!(selection instanceof IStructuredSelection)) {
			return;
		}

		IStructuredSelection structuredSeleciton = (IStructuredSelection) selection;
		MapMetaData map = (MapMetaData) structuredSeleciton.getFirstElement();
		IViewPart view = findImageViewer();
		if (view == null) {
			return;
		}
		
		loadAndSaveImage(map, view);
	}

	private void loadAndSaveImage(MapMetaData map, IViewPart view) {
		ImageView imageView = (ImageView) view;
		Bundle bundle = Platform.getBundle(IA_DATA_BUNDLE_ID);
		IPath mapImagePath = new Path("images/" + map.getImagePath());
		URL mapImageUrl = FileLocator.find(bundle, mapImagePath, null);
		try {
			loadImage(mapImageUrl.openStream(), imageView);
			saveImage(XSTREAMER_SKIRMISH_MAP_IMAGENAME, imageView, SWT.IMAGE_PNG);
		} catch (IOException e) {
		}
	}
}
