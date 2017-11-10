package us.nineworlds.xstreamer.ia.forms;

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
import us.nineworlds.xstreamer.ia.listeners.LoadMapImageSelectionListener;
import us.nineworlds.xstreamer.ia.lookup.SkirmishMapsLookup;


public class MapsFormPage extends ViewPart {


	protected FormToolkit toolkit;
	protected ScrolledForm form;
	private EventBus eventBus;
	protected ListViewer listViewer;
	
	public MapsFormPage() {
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
		mapSection.setText("Skirmish Maps");

		mapSection.setExpanded(true);
		mapSection.setDescription("A list of available skirmish maps.");

		Composite mapComposite = toolkit.createComposite(mapSection);
		GridLayout squadGridLayout = new GridLayout();
		squadGridLayout.numColumns = 3;

		mapComposite.setLayout(squadGridLayout);

		listViewer = new ListViewer(mapComposite, SWT.WRAP | SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		listViewer.add(SkirmishMapsLookup.getInstance().getMaps().toArray());
		listViewer.addSelectionChangedListener(new LoadMapImageSelectionListener());

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
}
