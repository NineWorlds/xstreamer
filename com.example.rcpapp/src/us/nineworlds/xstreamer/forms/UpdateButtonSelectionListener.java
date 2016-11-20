package us.nineworlds.xstreamer.forms;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.github.xws.Pilot;

import us.nineworlds.xstreamer.jobs.GenerateSquadJob;
import us.nineworlds.xstreamer.model.PilotTreeNode;

public class UpdateButtonSelectionListener implements SelectionListener {

	AbstractPlayerFormPage page;
	
	public UpdateButtonSelectionListener(AbstractPlayerFormPage page) {
		this.page = page;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		IStructuredSelection structuredSelection = (IStructuredSelection) page.treeViewer.getSelection();
		if (structuredSelection.getFirstElement() instanceof PilotTreeNode) {
			PilotTreeNode pilotTreeNode = (PilotTreeNode) structuredSelection.getFirstElement();
			Pilot pilot = (Pilot) pilotTreeNode.getValue();
			pilot.setHull(Integer.parseInt(page.hullText.getText()));
			pilot.setShields(Integer.parseInt(page.shieldText.getText()));			
		}
		
		if (page.getPlayerModel() != null) {
			GenerateSquadJob job = new GenerateSquadJob("generateSquad", page.getPlayerModel(), page.playerFileName()
					, page.squadTemplate());
			job.schedule();
		}

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

}
