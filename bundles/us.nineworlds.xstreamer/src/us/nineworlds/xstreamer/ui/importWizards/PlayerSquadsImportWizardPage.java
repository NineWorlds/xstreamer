package us.nineworlds.xstreamer.ui.importWizards;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import us.nineworlds.xstreamer.preferences.widgets.LabelFieldEditor;
import us.nineworlds.xstreamer.preferences.widgets.SpacerFieldEditor;


public class PlayerSquadsImportWizardPage extends WizardPage {
	
	private FileFieldEditor editorPlayer1;
	private FileFieldEditor editorPlayer2;
	
	public PlayerSquadsImportWizardPage(String pageName) {
		super(pageName);
		setDescription("Import the players squad files from the local file system."); //NON-NLS-1
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite fileSelectionArea = new Composite(parent, SWT.NONE);
		GridData fileSelectionData = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL);
		fileSelectionArea.setLayoutData(fileSelectionData);

		GridLayout fileSelectionLayout = new GridLayout();
		fileSelectionLayout.numColumns = 3;
		fileSelectionLayout.makeColumnsEqualWidth = false;
		fileSelectionLayout.marginWidth = 0;
		fileSelectionLayout.marginHeight = 0;
		fileSelectionArea.setLayout(fileSelectionLayout);
		
		LabelFieldEditor player1labelFieldEditor = new LabelFieldEditor("Select Player 1 Squad.", fileSelectionArea);
		player1labelFieldEditor.adjustForNumColumns(3);
		
		setEditorPlayer1(new FileFieldEditor("fileSelect","Select File: ",fileSelectionArea)); //NON-NLS-1 //NON-NLS-2
		String[] extensions = new String[] { "*.json", "*.xws" }; //NON-NLS-1
		getEditorPlayer1().setFileExtensions(extensions);
		
		fileSelectionArea.moveAbove(null);
		
		new SpacerFieldEditor(fileSelectionArea);
		
		LabelFieldEditor player2labelFieldEditor = new LabelFieldEditor("Select Player 2 Squad.", fileSelectionArea);
		player2labelFieldEditor.adjustForNumColumns(3);
		setEditorPlayer2(new FileFieldEditor("fileSelect","Select File: ",fileSelectionArea)); //NON-NLS-1 //NON-NLS-2
		editorPlayer2.setFileExtensions(extensions);
		
		setControl(fileSelectionArea);
	}

	public FileFieldEditor getEditorPlayer1() {
		return editorPlayer1;
	}

	public void setEditorPlayer1(FileFieldEditor editorPlayer1) {
		this.editorPlayer1 = editorPlayer1;
	}

	public FileFieldEditor getEditorPlayer2() {
		return editorPlayer2;
	}

	public void setEditorPlayer2(FileFieldEditor editorPlayer2) {
		this.editorPlayer2 = editorPlayer2;
	}
}
