package us.nineworlds.xstreamer.forms.listeners;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import com.github.xws.Pilot;

import us.nineworlds.xstreamer.forms.AbstractPlayerFormPage;
import us.nineworlds.xstreamer.model.PilotTreeNode;

public class SquadSelectionChangeListener implements ISelectionChangedListener {
	
	AbstractPlayerFormPage page;
	
	public SquadSelectionChangeListener(AbstractPlayerFormPage page) {
		this.page = page;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structureSelection = (IStructuredSelection) selection;
			
			if (structureSelection.getFirstElement() instanceof PilotTreeNode) {
				PilotTreeNode pilot = (PilotTreeNode) structureSelection.getFirstElement();
				Pilot p = (Pilot) pilot.getValue();
				page.pilotId.setText(p.getPilotId() != null ? p.getPilotId() : "");
				page.pilotSkillText.setText(p.getPilotSkill() != null ? p.getPilotSkill() : "");
				if (p.getHull() != null) {
					page.hullText.setText(Integer.toString(p.getHull()));					
				} else {
					page.hullText.setText("");
				}
				if (p.getShields() != null) {
					page.shieldText.setText(Integer.toString(p.getShields()));					
				} else {
					page.shieldText.setText("");
				}
				page.totalShipPoints.setText("Points: " + Integer.toString(p.getPoints()));
				page.totalShipPoints.getParent().requestLayout();
			}
		};
	}

}
