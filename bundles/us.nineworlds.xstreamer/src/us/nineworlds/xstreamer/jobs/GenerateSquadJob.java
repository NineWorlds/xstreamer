package us.nineworlds.xstreamer.jobs;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

import com.github.xws.XwsSpec;

import freemarker.template.Configuration;
import freemarker.template.Template;
import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.model.lookup.PilotLookup;
import us.nineworlds.xstreamer.model.lookup.ShipsLookup;
import us.nineworlds.xstreamer.model.lookup.UpgradeLookup;
import us.nineworlds.xstreamer.preferences.PreferenceConstants;

public class GenerateSquadJob extends Job {

	XwsSpec xwsspec;
	String playerFilename;
	String templateFilename;
	public GenerateSquadJob(String name, XwsSpec model, String preferncePlayerFileName, String templateFileName) {
		super(name);
		xwsspec = model;
		playerFilename = preferncePlayerFileName;
		this.templateFilename = templateFileName;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String templateType = findTemplateType(templateFilename);
		String templateOutputDirectory = preferenceStore.getString(PreferenceConstants.TEMPLATE_XSTREAMER_OUTPUT_DIRECTORY);
		String templateInputDirectory = preferenceStore.getString(PreferenceConstants.TEMPLATE_INPUT_DIRECTORY) + File.separator + "squads" + File.separator + templateType;
		if (StringUtils.isEmpty(templateOutputDirectory) || StringUtils.isEmpty(playerFilename) ||
			StringUtils.isEmpty(templateFilename) || StringUtils.isEmpty(templateInputDirectory)) {
			return Status.CANCEL_STATUS;
		}		

		Writer player1SquadFile = null;
		try {
			Configuration config = us.nineworlds.xstreamer.core.Activator.getDefault().getFreemarkerConfig();
			config.setDirectoryForTemplateLoading(new File(templateInputDirectory));
			
			Path path = Paths.get(templateFilename);
			Template squadTemplate = config.getTemplate(path.getFileName().toString());
			player1SquadFile = new FileWriter(new File(templateOutputDirectory + File.separator + playerFilename));

			Map<String, Object> input = new HashMap<>();

			input.put("xwsspec", xwsspec);
			input.put("allships", ShipsLookup.getInstance().getShips());
			input.put("allupgrades", UpgradeLookup.getInstance().getUpgrades());
			input.put("allpilots", PilotLookup.getInstance().getPilots());

			squadTemplate.process(input, player1SquadFile);
		} catch (Exception e) {
			e.printStackTrace();
			return Status.CANCEL_STATUS;
		} finally {
			IOUtils.closeQuietly(player1SquadFile);			
		}
		return Status.OK_STATUS;
	}
	
	private String findTemplateType(String filenamePath) {
		if (filenamePath.contains("/html/")) {
			return "html";
		}
		return "text";
	}
}
