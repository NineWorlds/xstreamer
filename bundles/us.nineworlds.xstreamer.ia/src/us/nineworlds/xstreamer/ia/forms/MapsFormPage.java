package us.nineworlds.xstreamer.ia.forms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.internal.runtime.MetaDataKeeper;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

import uky.article.imageviewer.views.ImageView;
import us.nineworlds.xstreamer.Logger;
import us.nineworlds.xstreamer.eventbus.EventBus;
import us.nineworlds.xstreamer.ia.lookup.SkirmishMapsLookup;
import us.nineworlds.xstreamer.ia.model.maps.MapMetaData;
import us.nineworlds.xstreamer.ia.preferences.PreferenceConstants;


public class MapsFormPage extends ViewPart {

	protected FormToolkit toolkit;
	protected ScrolledForm form;
	private EventBus eventBus;
	protected ListViewer listViewer;
	
	public MapsFormPage() {
		super();
		eventBus = EventBus.getInstance();
		eventBus.register(this);		
	}

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.setText("Skirmish Maps");

		ColumnLayout layout = new ColumnLayout();
		form.getBody().setLayout(layout);

		createMapSection();
		
		getSite().setSelectionProvider(listViewer);
	}

	private void createMapSection() {
		Section mapSection = toolkit.createSection(form.getBody(),
				ExpandableComposite.TWISTIE | Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
		mapSection.setText("Skirmish Maps");

		mapSection.setExpanded(true);
		mapSection.setDescription("A list of available skirmish maps.");

		Composite mapComposite = toolkit.createComposite(mapSection);
		GridLayout squadGridLayout = new GridLayout();
		squadGridLayout.numColumns = 3;

		mapComposite.setLayout(squadGridLayout);

		listViewer = new ListViewer(mapComposite, SWT.WRAP | SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		listViewer.add(SkirmishMapsLookup.getInstance().getMaps().toArray());
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection structuredSeleciton = (IStructuredSelection) event.getSelection();
					MapMetaData map = (MapMetaData) structuredSeleciton.getFirstElement();
					IViewPart view = findImageViewer();
					if (view != null) {
						ImageView imageView = (ImageView) view;
						Bundle bundle = Platform.getBundle("us.nineworlds.xstreamer.ia.data");
						IPath mapImagePath = new Path("images/" + map.getImagePath());
						URL mapImageUrl = FileLocator.find(bundle, mapImagePath, null);
						try {
							loadImage(mapImageUrl.openStream(), imageView);
							saveImage("xstreamer_skirmish_map.jpg", imageView);
						} catch (IOException e) {
						}
					}
				}
			}
			
			private IViewPart findImageViewer() {
				IWorkbenchPage currentActivePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				return currentActivePage.findView("us.nineworlds.xstreamer.imageview");
			}
			
			private void loadImage(InputStream inputStream, IViewPart view) {
				if (view != null) {
					ImageView imageView = (ImageView) view;
					try {
						imageView.imageCanvas.loadImage(inputStream);
					} catch (Exception ex) {
						Logger.info("Unable to load image");
					} finally {
						IOUtils.closeQuietly(inputStream);
					}
				}
			}
			
			private void saveImage(String filename, ImageView imageView) {
				IPreferenceStore store = us.nineworlds.xstreamer.ia.Activator.getDefault().getPreferenceStore();
				String outputDirectory = store.getString(PreferenceConstants.TEMPLATE_XSTREAMER_IA_OUTPUT_DIRECTORY);
				if (outputDirectory == null || imageView == null) {
					return;
				}

				ImageLoader imageLoader = new ImageLoader();
				imageLoader.data = new ImageData[] { imageView.imageCanvas.getImageData() };		
				FileOutputStream outputStream = null;
				try {
					outputStream = new FileOutputStream(new File(outputDirectory, filename));
					imageLoader.save(outputStream, SWT.IMAGE_PNG);
				} catch (FileNotFoundException e) {
					Logger.error("Unable to save image to file: " + filename, e);
				} finally {
					IOUtils.closeQuietly(outputStream);
				}				
			}
			
			
		});

		GridData mapSize = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 5);
		mapSize.minimumHeight = 300;
		mapSize.heightHint = 300;
		
		listViewer.getList().setLayoutData(mapSize);
		mapSection.setClient(mapComposite);
	}

	@Override
	public void setFocus() {
		form.setFocus();
	}

}
