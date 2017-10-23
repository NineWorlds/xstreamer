package us.nineworlds.xstreamer.ia.core;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import us.nineworlds.iadata.CommandCardDB;
import us.nineworlds.iadata.DeploymentsDB;
import us.nineworlds.iadata.util.CommandCardsDBLoader;
import us.nineworlds.iadata.util.DepoymentsDBLoader;
import us.nineworlds.xstreamer.ia.lookup.CommandCardLookup;
import us.nineworlds.xstreamer.ia.lookup.DeploymentsLookup;


/**
 * The activator class controls the plug-in life cycle
 */
public class Activator implements BundleActivator {
	
	private CommandCardLookup commandCardLookup;
	private DeploymentsLookup deploymentsLookup;

	// The plug-in ID
	public static final String PLUGIN_ID = "us.nineworlds.xstreamer.ia.core"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	static BundleContext getContext() {
		return context;
	}
	private static BundleContext context;
	
	public Activator() {
		plugin = this;
	}
	
	public void start(BundleContext context) throws Exception {
		Activator.context = context;
		
		Bundle dataBundle = Platform.getBundle(us.nineworlds.xstreamer.ia.data.Activator.DATA_BUNDLE_ID);
		IPath deploymentsPath = new Path("deployments/deployments.json");
		IPath commandCardsPath = new Path("commandCards/commandcards.json");
		
		URL deploymentsUrl = FileLocator.find(dataBundle, deploymentsPath, null);
		URL commandCardsUrl = FileLocator.find(dataBundle, commandCardsPath, null);
		
		DeploymentsDB deploymentsDB = new DepoymentsDBLoader().load(deploymentsUrl);
		CommandCardDB commandCardsDB = new CommandCardsDBLoader().load(commandCardsUrl);
		
		commandCardLookup = CommandCardLookup.newInstance(commandCardsDB);
		deploymentsLookup = DeploymentsLookup.newInstance(deploymentsDB);		
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
	}

	public static Activator getDefault() {
		return plugin;
	}
	
	public CommandCardLookup getCommandCardLookup() {
		return commandCardLookup;
	}

	public DeploymentsLookup getDeploymentsLookup() {
		return deploymentsLookup;
	}
	
}
