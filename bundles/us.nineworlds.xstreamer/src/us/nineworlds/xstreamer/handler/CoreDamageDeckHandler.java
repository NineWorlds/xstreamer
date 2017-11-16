package us.nineworlds.xstreamer.handler;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.handlers.HandlerUtil;

import us.nineworlds.xstreamer.forms.OtherCardsFormPage;

public class CoreDamageDeckHandler extends AbstractHandler implements IHandler {
	
	private static final String DONATE_URL = "https://nineworlds.github.io/xstreamer/";

	public CoreDamageDeckHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
				
		IWorkbench workbench = HandlerUtil.getActiveWorkbenchWindow(event).getWorkbench();
		
		IViewPart view = workbench.getActiveWorkbenchWindow().getActivePage().findView("us.nineworlds.xstreamer.view.othercards");
				
		if (view == null) {
			return null;
		}
		
		OtherCardsFormPage page = (OtherCardsFormPage) view;
		
		Map<String, Object> miscellaneousCards = us.nineworlds.xstreamer.core.Activator.getDefault().getExtraCardsModel().getMiscellaneousCards();
		
		List<Object> damageDeck = (List<Object>) miscellaneousCards.get("Damage Deck Original");
		
		page.loadCardList(damageDeck);
		
		return null;
	}

}
