package us.nineworlds.xstreamer.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.guidokessels.ships.Ship;
import com.github.guidokessels.ships.Upgrades;
import com.github.xws.XwsSpec;

import freemarker.template.Configuration;
import us.nineworlds.xstreamer.model.cards.ExtraMetaCardsModel;
import us.nineworlds.xstreamer.model.conditions.Conditions;
import us.nineworlds.xstreamer.model.damagedeck.DamageDeck;
import us.nineworlds.xstreamer.model.lookup.ShipsLookup;
import us.nineworlds.xstreamer.model.lookup.UpgradeLookup;
import us.nineworlds.xstreamer.model.template.SquadTemplateModel;
import us.nineworlds.xstreamer.model.template.Templates;

public class Activator implements BundleActivator {
	
	public static final String DATA_BUNDLE_ID = "us.nineworlds.xstreamer.data";

	private XwsSpec player1;
	private XwsSpec player2;
	private ExtraMetaCardsModel extraCardsModel = new ExtraMetaCardsModel();
	
	private List<SquadTemplateModel> squadTemplates;
	
	private static BundleContext context;
	private static Configuration freemarkerConfig;
	private static Activator plugin;
	

	static BundleContext getContext() {
		return context;
	}
	
	public Activator() {
		plugin = this;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		Bundle dataBundle = Platform.getBundle(DATA_BUNDLE_ID);
		IPath shipsPath = new Path("ships/ships.json");
		IPath upgradesPath = new Path("upgrades/upgrades.json");
		
		URL shipsUrl = FileLocator.find(dataBundle, shipsPath, null);
		
		ObjectMapper mapper = loadShips(shipsUrl);
		
		loadUpgrades(mapper);
		
		loadExtraCards(mapper);
		
		initFreemarker();
		
		loadSquadTemplates();

	}
	
	private void loadExtraCards(ObjectMapper mapper)
			throws IOException, JsonParseException, JsonMappingException {
		
		Bundle dataBundle = Platform.getBundle(DATA_BUNDLE_ID);
		IPath conditionsPath = new Path("conditions/conditions.json");
		IPath damageDeckCorePath = new Path("damageDeck/damage-deck-core.json");
		IPath damageDeckCoreTFAPath = new Path("damageDeck/damage-deck-core-tfa.json");
		URL conditionCards = FileLocator.find(dataBundle, conditionsPath, null);
		URL damageDeckCore = FileLocator.find(dataBundle, damageDeckCorePath, null);
		URL damageDeckCoreTFA = FileLocator.find(dataBundle, damageDeckCoreTFAPath, null);
		InputStream conditionsInputStream = conditionCards.openStream();
		InputStream damageDeckCoreInputStream = damageDeckCore.openStream();
		InputStream damageDeckCoreTFAInputStream = damageDeckCoreTFA.openStream();
		
		List<Conditions> conditions = Arrays.asList(mapper.readValue(conditionsInputStream, Conditions[].class));
		List<DamageDeck> damageDeckCoreList = Arrays.asList(mapper.readValue(damageDeckCoreInputStream, DamageDeck[].class));
		List<DamageDeck> damageDeckCoreTFAList = Arrays.asList(mapper.readValue(damageDeckCoreTFAInputStream, DamageDeck[].class));
		IOUtils.closeQuietly(conditionsInputStream);
		IOUtils.closeQuietly(damageDeckCoreTFAInputStream);
		IOUtils.closeQuietly(damageDeckCoreInputStream);
		
		Map<String, Object> miscellaneousCards = extraCardsModel.getMiscellaneousCards();
		miscellaneousCards.put("Conditions", conditions);
		miscellaneousCards.put("Damage Deck TFA ", damageDeckCoreTFAList);
		miscellaneousCards.put("Damage Deck Original", damageDeckCoreList);	
		
		extraCardsModel.setMiscellaneousCards(miscellaneousCards);
	}


	private void loadUpgrades(ObjectMapper mapper)
			throws IOException, JsonParseException, JsonMappingException {
		Bundle dataBundle = Platform.getBundle(DATA_BUNDLE_ID);
		IPath upgradesPath = new Path("upgrades/upgrades.json");
		URL upgradesUrl = FileLocator.find(dataBundle, upgradesPath, null);
		InputStream upgradesInputStream = upgradesUrl.openStream();
		List<Upgrades> upgradeData = Arrays.asList(mapper.readValue(upgradesInputStream, Upgrades[].class));
		IOUtils.closeQuietly(upgradesInputStream);
		
		UpgradeLookup.newInstance(upgradeData);
	}

	private ObjectMapper loadShips(URL shipsUrl) throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		InputStream shipInputStream = shipsUrl.openStream();
		List<Ship> shipData = Arrays
				.asList(mapper.readValue(shipInputStream, Ship[].class));
		IOUtils.closeQuietly(shipInputStream);
		
		ShipsLookup.newInstance(shipData);
		return mapper;
	}

	@Override
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
	
	private void loadSquadTemplates() {
		
		Bundle templateBundle = Platform.getBundle("us.nineworlds.xstreamer.templates");
		IPath templatePath = new Path("templates/squads/templates.xml");
		
		try {
			File templateFile = FileLocator.getBundleFile(templateBundle);
			File squadTemplatesFile = new File(templateFile, templatePath.toString());
			XmlMapper mapper = new XmlMapper();
			
			Templates templates = mapper.readValue(squadTemplatesFile, Templates.class);
			squadTemplates = templates.getTemplates();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	public List<SquadTemplateModel> getSquadTemplates() {
		return squadTemplates;
	}

	public void setSquadTemplates(List<SquadTemplateModel> squadTemplates) {
		this.squadTemplates = squadTemplates;
	}

	public ExtraMetaCardsModel getExtraCardsModel() {
		return extraCardsModel;
	}

	public void setExtraCardsModel(ExtraMetaCardsModel extraCardsModel) {
		this.extraCardsModel = extraCardsModel;
	}

}
