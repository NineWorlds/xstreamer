package us.nineworlds.xstreamer.forms;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

public abstract class AbstractPlayerFormPage extends ViewPart {

	protected FormToolkit toolkit;
	protected ScrolledForm form;
	TreeViewer treeViewer;
	
	Label hullLabel;
	Label shieldLabel;
	Text shieldText;
	
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
		
		Label playerSquadPoints = toolkit.createLabel(shipClient, "Total Squad Points: ");
		playerSquadPoints.setLayoutData(playerSquadPointsData);
		
		hullLabel = toolkit.createLabel(shipClient, "Hull: ");
		Text hullText = toolkit.createText(shipClient, "");
		GridData textData = new GridData();
		textData.minimumWidth = 30;
		hullText.setLayoutData(textData);
		
		shieldLabel = toolkit.createLabel(shipClient, "Shields: ");
		Text shieldText = toolkit.createText(shipClient, "");
		GridData shieldData = new GridData();
		textData.minimumWidth = 30;
		shieldText.setLayoutData(shieldData);
		shipSection.setClient(shipClient);
		
		toolkit.createButton(shipClient, "Update", SWT.PUSH);
	}

	private void createSquadSection() {
		Section squadSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
		squadSection.setText("Squad");
		
		squadSection.setExpanded(true);
		squadSection.setDescription("This contains the player's squadron");
		
		Composite squadComposite = toolkit.createComposite(squadSection);
		GridLayout squadGridLayout = new GridLayout();
		squadGridLayout.numColumns = 1;
		
		squadComposite.setLayout(squadGridLayout);
			
		treeViewer = new TreeViewer(squadComposite, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		treeViewer.getTree().setSize(290,  260);
		
		squadSection.setClient(squadComposite);
	}
	
	abstract void setPageName();
	
	abstract void pageContent(Composite parent);

}
