package us.nineworlds.xstreamer.forms;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import com.github.xws.Pilot;
import com.github.xws.XwsSpec;

import us.nineworlds.xstreamer.eventbus.EventBus;
import us.nineworlds.xstreamer.eventbus.EventHandler;
import us.nineworlds.xstreamer.eventbus.GenerateSquadJobEvent;
import us.nineworlds.xstreamer.forms.listeners.SquadSelectionChangeListener;
import us.nineworlds.xstreamer.jobs.GenerateSquadJob;
import us.nineworlds.xstreamer.model.ImportPlayerFile;
import us.nineworlds.xstreamer.model.SquadContentProvider;
import us.nineworlds.xstreamer.model.SquadLabelProvider;
import us.nineworlds.xstreamer.model.lookup.PilotLookup;

public abstract class AbstractPlayerFormPage extends ViewPart {

	protected FormToolkit toolkit;
	protected ScrolledForm form;
	TreeViewer treeViewer;

	public Text importSquadText;
	private EventBus eventBus;
	
	public AbstractPlayerFormPage() {
		super();
		
		eventBus = EventBus.getInstance();
		eventBus.register(this);
	}

	@EventHandler
	public void updateJob(GenerateSquadJobEvent event) {
		GenerateSquadJob job = new GenerateSquadJob("generateSquad", getPlayerModel(), playerFileName(), squadTemplate());
		job.schedule();
	}

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		setPageName();

		ColumnLayout layout = new ColumnLayout();
		form.getBody().setLayout(layout);

		createSquadSection();
		createImportSquadSection();
		
		getSite().setSelectionProvider(treeViewer);

		pageContent(parent);
	}

	private void createSquadSection() {
		Section squadSection = toolkit.createSection(form.getBody(),
				ExpandableComposite.TWISTIE | Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
		XwsSpec xwsModel = getPlayerModel();
		String points = xwsModel != null ? Integer.toString(xwsModel.getPoints()) : "";
		squadSection.setText("Squad - Total Points " + points);

		squadSection.setExpanded(true);
		squadSection.setDescription("This contains the player's squadron");

		Composite squadComposite = toolkit.createComposite(squadSection);
		GridLayout squadGridLayout = new GridLayout();
		squadGridLayout.numColumns = 3;

		squadComposite.setLayout(squadGridLayout);

		treeViewer = new TreeViewer(squadComposite, SWT.WRAP | SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		treeViewer.setContentProvider(new SquadContentProvider());
		treeViewer.setLabelProvider(new SquadLabelProvider());
		treeViewer.setInput(getPlayerModel());
		treeViewer.addSelectionChangedListener(new SquadSelectionChangeListener(this));

		GridData treeSize = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 5);
		treeSize.minimumHeight = 300;
		treeSize.heightHint = 300;

		treeViewer.getTree().setLayoutData(treeSize);
		Tree tree = treeViewer.getTree();
		tree.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				Point size = tree.getSize();

				if (size.x > 300) {
					tree.setSize(300, size.y);
				}
			}
		});

		squadSection.setClient(squadComposite);
	}

	private void createImportSquadSection() {
		Section section = toolkit.createSection(form.getBody(),
				ExpandableComposite.TWISTIE | Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
		section.setText("Import Squad");
		section.setExpanded(false);
		section.setDescription("Cut and paste the XWS squad data in the text box, then press Import.");
		Composite composite = toolkit.createComposite(section);
		GridLayout gridlayout = new GridLayout();
		gridlayout.numColumns = 2;
		composite.setLayout(gridlayout);

		importSquadText = toolkit.createText(composite, "", SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		importSquadText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		importSquadText.setEditable(true);
		GridData newsData = (GridData) importSquadText.getLayoutData();
		newsData.horizontalSpan = 2;
		newsData.heightHint = 200;
		newsData.minimumHeight = 200;
		
		importSquadText.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				Point size = importSquadText.getSize();

				if (size.x > 300) {
					importSquadText.setSize(300, size.y);
				}
			}
		});


		Button updateButton = toolkit.createButton(composite, "Import", SWT.PUSH | SWT.RESIZE);
		GridData updateButtonData = new GridData();
		updateButtonData.horizontalSpan = 1;
		updateButtonData.widthHint = 60;
		updateButtonData.grabExcessHorizontalSpace = false;
		updateButton.setLayoutData(updateButtonData);
		updateButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String json = importSquadText.getText();
				if (StringUtils.isNotEmpty(json)) {
					ImportPlayerFile xwsFile = new ImportPlayerFile();
					try {
						XwsSpec parseXws = xwsFile.parseXws(json);
						
						List<Pilot> pilots = parseXws.getPilots();
						PilotLookup pilotLookup = PilotLookup.getInstance();
						for (Pilot pilot : pilots) {
							com.github.guidokessels.ships.Pilot actualPilot = pilotLookup.lookupPilotValue(pilot.getXwsName());
							if (actualPilot != null) {
								Integer skill = Integer.valueOf(actualPilot.getSkill());
								com.github.xws.Upgrades upgrades = pilot.getUpgrades();
								if (upgrades != null && upgrades.findUpgrade("veteraninstincts") != null) {
									skill = skill + 2;
								}
								pilot.setPilotSkill(Integer.toString(skill));
							}
						}
						
						resetPlayerModel(parseXws);
						refreshTree();
						
						eventBus.post(new GenerateSquadJobEvent());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		Button clearButton = toolkit.createButton(composite, "Clear", SWT.PUSH | SWT.RESIZE);
		GridData clearButtonData = new GridData();
		clearButtonData.horizontalSpan = 1;
		clearButtonData.widthHint = 60;
		clearButton.setLayoutData(clearButtonData);
		clearButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				importSquadText.setText("");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		section.setClient(composite);
	}

	abstract void setPageName();

	abstract void pageContent(Composite parent);

	abstract XwsSpec getPlayerModel();

	abstract String playerFileName();

	abstract String squadTemplate();

	abstract void resetPlayerModel(XwsSpec model);

	public void refreshTree() {
		treeViewer.setInput(getPlayerModel());
	}

}
