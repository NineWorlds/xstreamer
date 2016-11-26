package us.nineworlds.xstreamer.model;

import org.eclipse.jface.viewers.LabelProvider;

import com.github.xws.Pilot;
import com.github.xws.XwsSpec;

public class SquadLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof XwsSpec) {
			XwsSpec xwsspec = (XwsSpec) element;
			return xwsspec.getName();
		}
		
		if (element instanceof Pilot) {
			Pilot pilot = (Pilot) element;
			return pilot.getName() + " " + pilot.getPoints();
		}
		
		return element.toString();
	}
}
