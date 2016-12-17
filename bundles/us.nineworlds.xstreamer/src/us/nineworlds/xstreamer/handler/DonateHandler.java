package us.nineworlds.xstreamer.handler;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class DonateHandler extends AbstractHandler implements IHandler {
	
	private static final String DONATE_URL = "https://kingargyle.github.io/xstreamer/";

	public DonateHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbench workbench = HandlerUtil.getActiveWorkbenchWindow(event).getWorkbench();
		URL url;
		try {
			url = new URL(DONATE_URL);
			workbench.getBrowserSupport().getExternalBrowser().openURL(url);
		} catch (Exception e) {
		}
		return null;
	}

}
