package us.nineworlds.xstreamer.forms;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import com.github.xws.XwsSpec;

import us.nineworlds.xstreamer.forms.listeners.SquadSelectionChangeListener;
import us.nineworlds.xstreamer.jobs.GenerateSquadJob;
import us.nineworlds.xstreamer.jobs.StringWriterJob;
import us.nineworlds.xstreamer.model.ImportPlayerFile;
import us.nineworlds.xstreamer.model.SquadContentProvider;
import us.nineworlds.xstreamer.model.SquadLabelProvider;

public abstract class AbstractPlayerFormPage extends ViewPart {

	protected FormToolkit toolkit;
	protected ScrolledForm form;
	TreeViewer treeViewer;
	
	public Label totalShipPoints;
	public Text shieldText;
	public Text hullText;
	public Text pilotSkillText;
	public Text pilotId;
	public Text importSquadText;
	
	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		setPageName();
		
		ColumnLayout layout = new ColumnLayout();
		form.getBody().setLayout(layout);

		createSquadSection();
		createShipSection();
		createImportSquadSection();
		
		pageContent(parent);
	}

	private void createShipSection() {
		Section shipSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
		shipSection.setText("Ship and Squad Details");

		shipSection.setExpanded(true);
		shipSection.setDescription("Shields, Hull, and other ship data.");
		Composite shipClient = toolkit.createComposite(shipSection);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.makeColumnsEqualWidth = false;
		shipClient.setLayout(gridLayout);
		GridData playerSquadPointsData = new GridData();
		playerSquadPointsData.grabExcessHorizontalSpace = true;
		playerSquadPointsData.horizontalSpan = 2;
		
		totalShipPoints = toolkit.createLabel(shipClient, "Points: ");
		totalShipPoints.setLayoutData(playerSquadPointsData);
		
		pilotSkillText = createField(shipClient, "Skill: ");
		pilotId = createField(shipClient, "Unique Id: ");
		hullText = createField(shipClient, "Hull: ");
		shieldText = createField(shipClient, "Shields: ");
		
		shipSection.setClient(shipClient);
		
		Button updateButton = toolkit.createButton(shipClient, "Update", SWT.PUSH);
		updateButton.addSelectionListener(new UpdatePilotButtonSelectionListener(this));
	}
	
	private Text createField(Composite client, String labelName) {
		toolkit.createLabel(client, labelName);
		Text field  = toolkit.createText(client, "");
		GridData textData = new GridData();
		textData.minimumWidth = 30;
		textData.widthHint = 30;
		field.setLayoutData(textData);
		return field;
	}

	private void createSquadSection() {
		Section squadSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
		XwsSpec xwsModel = getPlayerModel();
		String points = xwsModel != null ? Integer.toString(xwsModel.getPoints()) : "";
		squadSection.setText("Squad - Total Points " + points);
		
		squadSection.setExpanded(true);
		squadSection.setDescription("This contains the player's squadron");
		
		Composite squadComposite = toolkit.createComposite(squadSection);
		GridLayout squadGridLayout = new GridLayout();
		squadGridLayout.numColumns = 3;
		
		squadComposite.setLayout(squadGridLayout);
			
		treeViewer = new TreeViewer(squadComposite, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		treeViewer.setContentProvider(new SquadContentProvider());
		treeViewer.setLabelProvider(new SquadLabelProvider());
		treeViewer.setInput(getPlayerModel());
		treeViewer.addSelectionChangedListener(new SquadSelectionChangeListener(this));
		
		GridData treeSize = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 5);
		treeSize.minimumHeight = 300;
		treeSize.heightHint = 300;
		
		treeViewer.getTree().setLayoutData(treeSize);
		
		squadSection.setClient(squadComposite);
	}
	
	private void createImportSquadSection() {
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
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
		newsData.heightHint = 100;
		newsData.minimumHeight = 100;
		
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
						resetPlayerModel(parseXws);
						refreshTree();
						GenerateSquadJob job = new GenerateSquadJob("importxws", getPlayerModel(), playerFileName(), squadTemplate());
						job.schedule();
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
