package us.nineworlds.xstreamer.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Configuration {
	
	private PropertiesConfiguration properties;
	private static Configuration config;
	
	public static Configuration getInstance() {
		if (config == null) {
			config = new Configuration();
		}
		return config;
	}

	private Configuration() {
		
		try {
			properties = new PropertiesConfiguration("config/xstreamer.properties");
		} catch (ConfigurationException e) {
			System.err.println("Failed to load xstreamer.properties file.");
			System.exit(0);
		}
	}
	
	public String getPlayer1OverlayFilePath() {
		return properties.getString("xstreamer.squad.player1.file");
	}
	
	public String getPlayer2OverlayFilePath() {
		return properties.getString("xstreamer.squad.player2.file");
	}
	
	public String getPlayer1XWSFilePath() {
		return properties.getString("xstreamer.squad.player1.xws");
	}
	
	public String getPlayer2XWSFilePath() {
		return properties.getString("xstreamer.squad.player2.xws");
	}
	
	public String getTimerFilePath() {
		return properties.getString("xstreamer.timer.file");
	}
	
	public String player1SquadTemplateFilePath() {
	   return properties.getString("xstreamer.template.player1.squad");
	}
	
	public String player2SquadTemplateFilePath() {
	   return properties.getString("xstreamer.template.player2.squad");
	}
}
