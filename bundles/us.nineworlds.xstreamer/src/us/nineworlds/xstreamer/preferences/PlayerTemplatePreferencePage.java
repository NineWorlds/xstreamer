package us.nineworlds.xstreamer.preferences;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import us.nineworlds.xstreamer.Activator;

public class PlayerTemplatePreferencePage extends AbstractPlayerTemplatesPreferencePage implements IWorkbenchPreferencePage {
	
	@Override
	protected Control createContents(Composite parent) {
		super.createContents(parent);
		Composite page = new Composite(parent, SWT.NONE);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		page.setLayout(gridLayout);
				
		createSquadTemplatesTable(page);

		return page;
	}	

	@Override
	protected void createFieldEditors() {
		addField(new StringFieldEditor(PreferenceConstants.FIRST_PLAYER_SQUAD_FILENAME, "Squad Filename:",
				getFieldEditorParent()));
		createFileEditorForPlayerTemplate(PreferenceConstants.TEMPLATE_FIRST_PLAYER_FILE, "Template Filename: ");
	}


	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Use this to set the squad file names and the overlay template to use for the first player.");	
		squadTemplates = us.nineworlds.xstreamer.core.Activator.getDefault().getSquadTemplates();		
	}


}
