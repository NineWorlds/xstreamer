package us.nineworlds.xstreamer.forms;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import us.nineworlds.xstreamer.Activator;

public class GeneralFormPage extends ViewPart {
	
	public static String GENERAL_VIEW_ID = "us.nineworlds.xstreamer.view.general";
		
	public Text firstPlayerName;
	public Text secondPlayerName;
	
	private IPreferenceStore preference;

	private FormToolkit toolkit;
	private ScrolledForm form;
	
	public GeneralFormPage() {
		preference = Activator.getDefault().getPreferenceStore();
	}

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		ColumnLayout layout = new ColumnLayout();
		form.getBody().setLayout(layout);
		
		Section playersSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
		playersSection.setText("Timer Section");
		playersSection.setExpanded(true);
		playersSection.setDescription("The players that are playing.");
		Composite playerClient = toolkit.createComposite(playersSection);
		GridLayout playerGridLayout = new GridLayout();
		playerGridLayout.numColumns = 2;
		playerClient.setLayout(playerGridLayout);
		
		firstPlayerName = createPlayerNameField(playerClient, "Player 1: ");
		secondPlayerName = createPlayerNameField(playerClient, "Player 2: ");
				
		Button updateButton = toolkit.createButton(playerClient, "Update", SWT.PUSH | SWT.RESIZE);
		GridData updateButtonData = new GridData();
		updateButtonData.horizontalSpan = 2;
		updateButtonData.widthHint = 60;
		updateButtonData.grabExcessHorizontalSpace = true;
		updateButton.setLayoutData(updateButtonData);
		updateButton.addSelectionListener(new PlayerNameUpdateButtonSelectionListener(this));
				
		playersSection.setClient(playerClient);		

	}
	
	private Text createPlayerNameField(Composite parent, String label) {
		toolkit.createLabel(parent, label);
		GridData playerAttributs = new GridData();
		playerAttributs.widthHint = 200;
		playerAttributs.grabExcessHorizontalSpace = true;
		
		Text player  = toolkit.createText(parent, "");
		player.setLayoutData(playerAttributs);
		return player; 
	}

	@Override
	public void setFocus() {
		form.setFocus();
	}
	
	@Override
	public void dispose() {
		toolkit.dispose();
		super.dispose();
	}
}
