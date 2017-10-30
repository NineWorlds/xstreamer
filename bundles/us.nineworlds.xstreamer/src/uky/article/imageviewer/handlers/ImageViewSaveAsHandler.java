package uky.article.imageviewer.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import uky.article.imageviewer.views.ImageView;
import uky.article.imageviewer.views.SWTImageCanvas;

public class ImageViewSaveAsHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		ImageView imageView = (ImageView) activePart;
		SWTImageCanvas imageCanvas = imageView.imageCanvas;
		if (imageCanvas == null) {
			return null;
		}

		Shell shell = HandlerUtil.getActiveShell(event);

		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setFilterExtensions(new String[] { "*.png", "*.*" });
		dialog.setFilterNames(new String[] { "PNG Files", "All Files" });
		String fileSelected = dialog.open();

		if (fileSelected != null) {
			ImageLoader imageLoader = new ImageLoader();
			imageLoader.data = new ImageData[] { imageCanvas.getImageData() };

			System.out.println("Selected file: " + fileSelected);
			imageLoader.save(fileSelected, SWT.IMAGE_PNG);
		}

		return null;
	}

}
