package us.nineworlds.xstreamer.ia.forms;

import java.io.ByteArrayInputStream;
import java.io.StringBufferInputStream;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import us.nineworlds.iadata.IASpec;
import us.nineworlds.iadata.util.IASpecLoader;
import us.nineworlds.xstreamer.ia.listeners.ArmySelectionChangeListener;
import us.nineworlds.xstreamer.ia.listeners.UpdateDeploymentButtonSelectionListener;
import us.nineworlds.xstreamer.ia.model.ArmyContentProvider;
import us.nineworlds.xstreamer.ia.model.ArmyLabelProvider;


public abstract class AbstractPlayerFormPage extends ViewPart {

	protected FormToolkit toolkit;
	protected ScrolledForm form;
	public TreeViewer treeViewer;

	public Label totalArmyPoints;
	public Text health;
	public Text speed;
	public Text squadID;
	public Text importArmyText;

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		setPageName();

		ColumnLayout layout = new ColumnLayout();
		form.getBody().setLayout(layout);

		createArmySection();
		createDeploymentSection();
		createImportSquadSection();

		pageContent(parent);
	}

	private void createDeploymentSection() {
		Section deploymentSection = toolkit.createSection(form.getBody(),
				Section.TWISTIE | Section.DESCRIPTION | Section.TITLE_BAR);
		deploymentSection.setText("Deployment Details");

		deploymentSection.setExpanded(true);
		deploymentSection.setDescription("Health and Speed.");
		Composite shipClient = toolkit.createComposite(deploymentSection);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.makeColumnsEqualWidth = false;
		shipClient.setLayout(gridLayout);
		GridData playerSquadPointsData = new GridData();
		playerSquadPointsData.grabExcessHorizontalSpace = true;
		playerSquadPointsData.horizontalSpan = 2;

		totalArmyPoints = toolkit.createLabel(shipClient, "Points: ");
		totalArmyPoints.setLayoutData(playerSquadPointsData);

		squadID = createField(shipClient, "Squad ID: ");
		health = createField(shipClient, "Health: ");
		speed = createField(shipClient, "Speed: ");

		deploymentSection.setClient(shipClient);

		Button updateButton = toolkit.createButton(shipClient, "Update", SWT.PUSH);
		updateButton.addSelectionListener(new UpdateDeploymentButtonSelectionListener(this));
	}

	private Text createField(Composite client, String labelName) {
		toolkit.createLabel(client, labelName);
		Text field = toolkit.createText(client, "");
		GridData textData = new GridData();
		textData.minimumWidth = 30;
		textData.widthHint = 30;
		field.setLayoutData(textData);
		return field;
	}

	private void createArmySection() {
		Section squadSection = toolkit.createSection(form.getBody(),
				Section.TWISTIE | Section.DESCRIPTION | Section.TITLE_BAR);
		String points = "";
		squadSection.setText("Army - Total Points " + points);

		squadSection.setExpanded(true);
		squadSection.setDescription("This contains the player's army");

		Composite squadComposite = toolkit.createComposite(squadSection);
		GridLayout squadGridLayout = new GridLayout();
		squadGridLayout.numColumns = 3;

		squadComposite.setLayout(squadGridLayout);

		treeViewer = new TreeViewer(squadComposite, SWT.WRAP | SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		treeViewer.setContentProvider(new ArmyContentProvider());
		treeViewer.setLabelProvider(new ArmyLabelProvider());
		treeViewer.setInput(getPlayerModel());
		treeViewer.addSelectionChangedListener(new ArmySelectionChangeListener(this));

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
				Section.TWISTIE | Section.DESCRIPTION | Section.TITLE_BAR);
		section.setText("Import Army");
		section.setExpanded(false);
		section.setDescription("Cut and paste the IASpec army data in the text box, then press Import.");
		Composite composite = toolkit.createComposite(section);
		GridLayout gridlayout = new GridLayout();
		gridlayout.numColumns = 2;
		composite.setLayout(gridlayout);

		importArmyText = toolkit.createText(composite, "", SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		importArmyText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		importArmyText.setEditable(true);
		GridData newsData = (GridData) importArmyText.getLayoutData();
		newsData.horizontalSpan = 2;
		newsData.heightHint = 200;
		newsData.minimumHeight = 200;
		
		importArmyText.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				Point size = importArmyText.getSize();

				if (size.x > 300) {
					importArmyText.setSize(300, size.y);
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
				String json = importArmyText.getText();
				if (StringUtils.isNotEmpty(json)) {
					IASpecLoader iaspecFile = new IASpecLoader();
					try {
						IASpec iaspec = iaspecFile.load(new ByteArrayInputStream(json.getBytes()));
						resetPlayerModel(iaspec);
						refreshTree();
//						GenerateSquadJob job = new GenerateSquadJob("importxws", getPlayerModel(), playerFileName(),
//								squadTemplate());
//						job.schedule();
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
				importArmyText.setText("");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		section.setClient(composite);
	}

	abstract void setPageName();

	abstract void pageContent(Composite parent);

	abstract IASpec getPlayerModel();

	abstract String playerFileName();

	abstract String armyTemplate();

	abstract void resetPlayerModel(IASpec model);

	public void refreshTree() {
		treeViewer.setInput(getPlayerModel());
	}

}
