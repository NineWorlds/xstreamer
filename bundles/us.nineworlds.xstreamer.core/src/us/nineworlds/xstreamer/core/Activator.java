package us.nineworlds.xstreamer.core;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.guidokessels.ships.Ship;
import com.github.guidokessels.ships.Upgrades;
import com.github.xws.XwsSpec;

import freemarker.template.Configuration;
import us.nineworlds.xstreamer.model.lookup.ShipsLookup;
import us.nineworlds.xstreamer.model.lookup.UpgradeLookup;

public class Activator implements BundleActivator {
	
	public static final String DATA_BUNDLE_ID = "us.nineworlds.xstreamer.data";

	private XwsSpec player1;
	private XwsSpec player2;
	
	private static BundleContext context;
	private static Configuration freemarkerConfig;
	private static Activator plugin;
	

	static BundleContext getContext() {
		return context;
	}
	
	public Activator() {
		plugin = this;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		Bundle dataBundle = Platform.getBundle(DATA_BUNDLE_ID);
		IPath shipsPath = new Path("ships/ships.json");
		IPath upgradesPath = new Path("upgrades/upgrades.json");
		
		URL shipsUrl = FileLocator.find(dataBundle, shipsPath, null);
		URL upgradesUrl = FileLocator.find(dataBundle, upgradesPath, null);
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream shipInputStream = shipsUrl.openStream();
		List<Ship> shipData = Arrays
				.asList(mapper.readValue(shipInputStream, Ship[].class));
		IOUtils.closeQuietly(shipInputStream);
		
		ShipsLookup.newInstance(shipData);
		
		InputStream upgradesInputStream = upgradesUrl.openStream();
		List<Upgrades> upgradeData = Arrays.asList(mapper.readValue(upgradesInputStream, Upgrades[].class));
		IOUtils.closeQuietly(upgradesInputStream);
		
		UpgradeLookup.newInstance(upgradeData);
		
		initFreemarker();

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}
	
	private void initFreemarker() throws Exception {
		freemarkerConfig = new Configuration();
	}

	public Configuration getFreemarkerConfig() {
		return freemarkerConfig;
	}

	public void setFreemarkerConfig(Configuration freemarkerConfig) {
		Activator.freemarkerConfig = freemarkerConfig;
	}
	
	public XwsSpec getPlayer1() {
		return player1;
	}

	public void setPlayer1(XwsSpec player1) {
		this.player1 = player1;
	}

	public XwsSpec getPlayer2() {
		return player2;
	}

	public void setPlayer2(XwsSpec player2) {
		this.player2 = player2;
	}
	
	public static Activator getDefault() {
		return plugin;
	}

}
