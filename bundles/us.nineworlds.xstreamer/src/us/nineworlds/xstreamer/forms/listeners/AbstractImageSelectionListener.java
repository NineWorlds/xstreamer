package us.nineworlds.xstreamer.forms.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import uky.article.imageviewer.views.ImageView;
import us.nineworlds.xstreamer.Logger;
import us.nineworlds.xstreamer.preferences.PreferenceConstants;

public class AbstractImageSelectionListener {
	
	protected static final String XWING_DATA_BUNDLE_ID = "us.nineworlds.xstreamer.data";

	protected IViewPart findImageViewer() {
		IWorkbenchPage currentActivePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		return currentActivePage.findView("us.nineworlds.xstreamer.imageview");
	}

	protected void loadImage(InputStream inputStream, IViewPart view) {
		if (view != null) {
			ImageView imageView = (ImageView) view;
			try {
				imageView.imageCanvas.loadImage(inputStream);
			} catch (Exception ex) {
				Logger.info("Unable to load image");
			} finally {
				IOUtils.closeQuietly(inputStream);
			}
		}
	}

	protected void saveImage(String filename, ImageView imageView, int imageType) {
		IPreferenceStore store = us.nineworlds.xstreamer.Activator.getDefault().getPreferenceStore();
		String outputDirectory = store.getString(PreferenceConstants.TEMPLATE_XSTREAMER_XWING_OUTPUT_DIRECTORY);
		if (outputDirectory == null || imageView == null) {
			return;
		}
	
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.data = new ImageData[] { imageView.imageCanvas.getImageData() };
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File(outputDirectory, filename));
			imageLoader.save(outputStream, imageType);
		} catch (FileNotFoundException e) {
			Logger.error("Unable to save image to file: " + filename, e);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
	}

}
