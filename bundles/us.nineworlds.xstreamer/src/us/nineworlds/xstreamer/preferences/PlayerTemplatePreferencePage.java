package us.nineworlds.xstreamer.preferences;

import java.io.File;
import java.util.List;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.model.template.SquadTemplateModel;

public class PlayerTemplatePreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	
	List<SquadTemplateModel> squadTemplates;
	
	FileFieldEditor templateFile;
	
	String templatesDirectory;
	

	public PlayerTemplatePreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Player 1 Squad Template Selection");
	}


	@Override
	public void init(IWorkbench workbench) {
		squadTemplates = us.nineworlds.xstreamer.core.Activator.getDefault().getSquadTemplates();
	}


	@Override
	protected Control createContents(Composite parent) {
		Composite page = new Composite(parent, SWT.NONE);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		page.setLayout(gridLayout);
		
		createFileEditorForPlayerTemplate(page, PreferenceConstants.TEMPLATE_FIRST_PLAYER_FILE, "Player 1 Template: ");
		
		createSquadTemplatesTable(page);

		return page;
	}


	protected void createFileEditorForPlayerTemplate(Composite page, String playerTemplateFile, String labelText) {
		String template = getPreferenceStore().getString(playerTemplateFile);
		templateFile = new FileFieldEditor(playerTemplateFile, labelText, page);
		if (template != null && template != "") {
			templateFile.setStringValue(template);
		}
	}
	
	@Override
	protected void performApply() {
		super.performApply();
		templateFile.store();
	}
	
	@Override
	public boolean performOk() {
		templateFile.store();
		return super.performOk();
	}


	protected void createSquadTemplatesTable(Composite page) {
		Label squadTemplateLabel = new Label(page, SWT.NONE);
		squadTemplateLabel.setText("Squad Templates");
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 1;
		gridData.verticalAlignment = SWT.TOP;
		squadTemplateLabel.setLayoutData(gridData);
		
		TableViewer tv = new TableViewer(page, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
		
		tv.addSelectionChangedListener(new ISelectionChangedListener() {
		    public void selectionChanged(final SelectionChangedEvent event) {
		        IStructuredSelection selection = (IStructuredSelection)event.getSelection();
		        String templateDirectory = getPreferenceStore().getString(PreferenceConstants.TEMPLATE_INPUT_DIRECTORY);
		        templateDirectory = templateDirectory.replace("templates/", "");
		        templateFile.setEmptyStringAllowed(false);
		        SquadTemplateModel model  = (SquadTemplateModel) selection.getFirstElement();
		        String fullPath = templateDirectory + File.separator + model.getPath() + model.getFilename();
				templateFile.setStringValue(fullPath);
				templateFile.setFilterPath(new File(fullPath));
		    }
		});
		
		final Table table = tv.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.LEFT);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Name");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.LEFT);
		tblclmnNewColumn_1.setWidth(200);
		tblclmnNewColumn_1.setText("Description");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.LEFT);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("Type");
		
		tv.setLabelProvider(new SquadTemplateLabelProvider());
		tv.setContentProvider(new SquadTemplateContentProvider());
		tv.setInput(this.squadTemplates);
	}
	
	public class SquadTemplateContentProvider implements IStructuredContentProvider {

		@Override
		public Object[] getElements(Object inputElement) {
			return squadTemplates.toArray();
		}		
	}
	
	public class SquadTemplateLabelProvider extends LabelProvider implements ITableLabelProvider  {

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			SquadTemplateModel model = (SquadTemplateModel) element;
			
			switch (columnIndex) {
			case 0 : 
				return model.getName();
			case 1 : 
				return model.getDescription();
			case 2 :
				return model.getFormat();
			default:
				return "unknown index " + columnIndex;
			}
		}
	
	}


}
