package us.nineworlds.xstreamer;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.guidokessels.ships.Ship;
import com.github.xws.XwsSpec;

import freemarker.template.Configuration;
import us.nineworlds.xstreamer.jobs.GenerateSquadJob;
import us.nineworlds.xstreamer.model.lookup.ShipsLookup;

public class Activator extends AbstractUIPlugin {

	private static XwsSpec player1;
	private static XwsSpec player2;
	private static Scheduler scheduler;
	private static long countDownTime;
	private static Configuration freemarkerConfig;
	private static us.nineworlds.xstreamer.Configuration configuration;
	private static Activator plugin;

	public Activator() {
		plugin = this;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		ObjectMapper mapper = new ObjectMapper();
		InputStream shipInputStream = Activator.class.getResourceAsStream("/xws-data/ships.json");
		List<Ship> shipData = Arrays
				.asList(mapper.readValue(Activator.class.getResourceAsStream("/xws-data/ships.json"), Ship[].class));
		IOUtils.closeQuietly(shipInputStream);
		
		ShipsLookup.newInstance(shipData);

		initFreemarker();

	}
	
	public static Activator getDefault() {
		return plugin;
	}

	public static Scheduler getScheduler() {
		return scheduler;
	}

	public static synchronized long getCountDownTime() {
		return countDownTime;
	}

	public static synchronized void setCountDownTime(long countDownTime) {
		Activator.countDownTime = countDownTime;
	}

	public static XwsSpec getPlayer1() {
		return player1;
	}

	public static void setPlayer1(XwsSpec player1) {
		Activator.player1 = player1;
	}

	public static XwsSpec getPlayer2() {
		return player2;
	}

	public static void setPlayer2(XwsSpec player2) {
		Activator.player2 = player2;
	}

	private static void initFreemarker() throws Exception {
		freemarkerConfig = new Configuration();
		freemarkerConfig.setDirectoryForTemplateLoading(new File("templates"));

//		GenerateSquadJob.createPlayerFile(configuration.getPlayer1OverlayFilePath(), "1", "squadJob1");
//		GenerateSquadJob.createPlayerFile(configuration.getPlayer2OverlayFilePath(), "2", "squadJob2");
	}

	public static Configuration getFreemarkerConfig() {
		return freemarkerConfig;
	}

	public static void setFreemarkerConfig(Configuration freemarkerConfig) {
		Activator.freemarkerConfig = freemarkerConfig;
	}

}
