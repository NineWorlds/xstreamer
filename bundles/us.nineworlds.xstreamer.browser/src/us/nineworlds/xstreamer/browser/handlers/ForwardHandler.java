package us.nineworlds.xstreamer.browser.handlers;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import us.nineworlds.xstreamer.browser.BrowserView;

public class ForwardHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		BrowserView browserView = (BrowserView) activePart;
		Browser browser = browserView.getBrowser();
		if (browser.isForwardEnabled()) {
			browser.forward();
		}
		return null;
	}
}
