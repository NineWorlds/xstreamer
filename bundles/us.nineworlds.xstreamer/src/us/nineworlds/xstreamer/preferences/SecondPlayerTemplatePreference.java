package us.nineworlds.xstreamer.preferences;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class SecondPlayerTemplatePreference extends PlayerTemplatePreferencePage {

	public SecondPlayerTemplatePreference() {
		super();
		setDescription("Player 2 Squad Template Selection");
	}
	
	@Override
	public Composite createContents(Composite parent) {
		Composite page = new Composite(parent, SWT.NONE);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		page.setLayout(gridLayout);
		
		createFileEditorForPlayerTemplate(page, PreferenceConstants.TEMPLATE_SECOND_PLAYER_FILE, "Player 2 Template: ");
		
		createSquadTemplatesTable(page);

		return page;
	}

}
