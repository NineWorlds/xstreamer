package uky.article.imageviewer.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import uky.article.imageviewer.views.ImageView;
import uky.article.imageviewer.views.SWTImageCanvas;

public class ImageViewZoomInHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		ImageView imageView = (ImageView) activePart;
		SWTImageCanvas imageCanvas = imageView.imageCanvas;
		if (imageCanvas == null) {
			return null;
		}
		
		imageCanvas.zoomIn();

		return null;
	}

}
