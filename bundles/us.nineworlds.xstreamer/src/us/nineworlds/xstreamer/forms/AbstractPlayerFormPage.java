package us.nineworlds.xstreamer.forms;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
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

import us.nineworlds.xstreamer.model.SquadContentProvider;
import us.nineworlds.xstreamer.model.SquadLabelProvider;

public abstract class AbstractPlayerFormPage extends ViewPart {

	protected FormToolkit toolkit;
	protected ScrolledForm form;
	TreeViewer treeViewer;
	
	Label totalShipPoints;
	Text shieldText;
	Text hullText;
	Text pilotSkillText;
	Text pilotId;
	
	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		setPageName();
		
		ColumnLayout layout = new ColumnLayout();
		form.getBody().setLayout(layout);

		createSquadSection();
	
		createShipSection();
		
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
		updateButton.addSelectionListener(new UpdateButtonSelectionListener(this));
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
	
	abstract void setPageName();
	
	abstract void pageContent(Composite parent);
	
	abstract XwsSpec getPlayerModel();
	
	abstract String playerFileName();
	
	abstract String squadTemplate();
	
	public void refreshTree() {
		treeViewer.setInput(getPlayerModel());
	}
	
	

}
