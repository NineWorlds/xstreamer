package us.nineworlds.xstreamer.preferences;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import us.nineworlds.xstreamer.Logger;
import us.nineworlds.xstreamer.core.Activator;
import us.nineworlds.xstreamer.model.template.SquadTemplateModel;

public abstract class AbstractPlayerTemplatesPreferencePage extends FieldEditorPreferencePage {

	protected List<SquadTemplateModel> squadTemplates;
	FileFieldEditor templateFile;
	String templatesDirectory;

	public AbstractPlayerTemplatesPreferencePage() {
		super();
	}

	public AbstractPlayerTemplatesPreferencePage(int style) {
		super(style);
	}

	public AbstractPlayerTemplatesPreferencePage(String title, int style) {
		super(title, style);
	}

	public AbstractPlayerTemplatesPreferencePage(String title, ImageDescriptor image, int style) {
		super(title, image, style);
	}

	protected void createFileEditorForPlayerTemplate(String playerTemplateFile, String labelText) {		
		Composite parent = getFieldEditorParent();		
		templateFile = new FileFieldEditor(playerTemplateFile, labelText, parent);
		addField(templateFile);
	}

	protected void createSquadTemplatesTable(Composite page) {
		Label squadTemplateLabel = new Label(page, SWT.NONE);
		squadTemplateLabel.setText("Squad Templates");
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;		
		gridData.verticalAlignment = SWT.LEFT;
		squadTemplateLabel.setLayoutData(gridData);
		
		Composite tableViewerContainer = new Composite(page, SWT.NONE);
		tableViewerContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tableViewerContainer.setLayout(new FillLayout());
		
		TableViewer tv = new TableViewer(tableViewerContainer, SWT.FILL | SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
				
		tv.addSelectionChangedListener(new ISelectionChangedListener() {
		    public void selectionChanged(final SelectionChangedEvent event) {
		        IStructuredSelection selection = (IStructuredSelection)event.getSelection();
		        String templateDirectory = getPreferenceStore().getString(PreferenceConstants.TEMPLATE_XWING_INPUT_DIRECTORY);
		        templateFile.setEmptyStringAllowed(false);
		        SquadTemplateModel model  = (SquadTemplateModel) selection.getFirstElement();
		        String fullPath = templateDirectory + File.separator + model.getPath() + model.getFilename();
		        Logger.info("Template Full Path: " + fullPath);
		        
				templateFile.setStringValue(fullPath);
				templateFile.setFilterPath(new File(fullPath));
		    }
		});
		
		final Table table = tv.getTable();
		  AutoResizeTableLayout layout = new AutoResizeTableLayout(table);
		  table.setLayout(layout);
		  layout.addColumnData(new ColumnWeightData(100));
		  layout.addColumnData(new ColumnWeightData(200));
		  layout.addColumnData(new ColumnWeightData(50));

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.LEFT);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Name");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.LEFT);
		tblclmnNewColumn_1.setWidth(300);
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
	
	@Override
	public void setErrorMessage(String newMessage) {
		if (newMessage != null) {
			Logger.error(newMessage + "\nTemplateFile = " + templateFile.getStringValue());
		}
		
		super.setErrorMessage(newMessage);
	}

}