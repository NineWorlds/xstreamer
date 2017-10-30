package uky.article.imageviewer.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import uky.article.imageviewer.views.ImageView;
import uky.article.imageviewer.views.SWTImageCanvas;

public class ImageViewRotateHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		ImageView imageView = (ImageView) activePart;
		SWTImageCanvas imageCanvas = imageView.imageCanvas;
		if (imageCanvas == null) {
			return null;
		}
		
		/* rotate image anti-clockwise */
		ImageData src = imageCanvas.getImageData();
		
		PaletteData srcPal = src.palette;
		PaletteData destPal;
		ImageData dest;
		/* construct a new ImageData */
		if (srcPal.isDirect) {
			destPal = new PaletteData(srcPal.redMask, srcPal.greenMask, srcPal.blueMask);
		} else {
			destPal = new PaletteData(srcPal.getRGBs());
		}
		dest = new ImageData(src.height, src.width, src.depth, destPal);
		/* rotate by rearranging the pixels */
		for (int i = 0; i < src.width; i++) {
			for (int j = 0; j < src.height; j++) {
				int pixel = src.getPixel(i, j);
				dest.setPixel(j, src.width - 1 - i, pixel);
			}
		}
		imageCanvas.setImageData(dest);

		return null;
	}

}
