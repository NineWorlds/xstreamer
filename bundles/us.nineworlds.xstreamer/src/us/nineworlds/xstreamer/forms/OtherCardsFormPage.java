package us.nineworlds.xstreamer.forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import us.nineworlds.xstreamer.eventbus.EventBus;
import us.nineworlds.xstreamer.forms.listeners.LoadOtherCardsImageSelectionListener;
import us.nineworlds.xstreamer.jface.listviewer.XStreamerListViewer;


public class OtherCardsFormPage extends ViewPart {


	protected FormToolkit toolkit;
	protected ScrolledForm form;
	private EventBus eventBus;
	protected XStreamerListViewer listViewer;
	
	public OtherCardsFormPage() {
		super();
		eventBus = EventBus.getInstance();
		eventBus.register(this);		
	}

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.setText("Skirmish Maps");

		ColumnLayout layout = new ColumnLayout();
		form.getBody().setLayout(layout);

		createMapSection();
		
		getSite().setSelectionProvider(listViewer);
	}

	private void createMapSection() {
		Section mapSection = toolkit.createSection(form.getBody(),
				ExpandableComposite.TWISTIE | Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
		mapSection.setText("Conditions and Damage Decks");

		mapSection.setExpanded(true);
		mapSection.setDescription("This section shows a list of Damage Decks, and Condition cards. Use the tool bar to toggle which deck is shown.");

		Composite mapComposite = toolkit.createComposite(mapSection);
		GridLayout squadGridLayout = new GridLayout();
		squadGridLayout.numColumns = 3;

		mapComposite.setLayout(squadGridLayout);

		listViewer = new XStreamerListViewer(mapComposite, SWT.WRAP | SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		Map<String, Object> miscellaneousCards = us.nineworlds.xstreamer.core.Activator.getDefault().getExtraCardsModel().getMiscellaneousCards();
		List<Object> damageCardsTFA = (List<Object>) miscellaneousCards.get("Damage Deck TFA");
		listViewer.add(damageCardsTFA.toArray());
		listViewer.addSelectionChangedListener(new LoadOtherCardsImageSelectionListener());

		GridData mapSize = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 5);
		mapSize.minimumHeight = 300;
		mapSize.heightHint = 300;
		
		listViewer.getList().setLayoutData(mapSize);
		mapSection.setClient(mapComposite);
	}

	@Override
	public void setFocus() {
		form.setFocus();
	}
	
	public void loadCardList(List<Object> cards) {
		int itemCount = listViewer.getList().getItemCount();
		List<Object> objectsToRemove = new ArrayList();
		for (int i = 0; i < itemCount; i++) {
			Object item = listViewer.getElementAt(i);
			objectsToRemove.add(item);
		}
		listViewer.remove(objectsToRemove.toArray());
		listViewer.add(cards.toArray());
	}
}
