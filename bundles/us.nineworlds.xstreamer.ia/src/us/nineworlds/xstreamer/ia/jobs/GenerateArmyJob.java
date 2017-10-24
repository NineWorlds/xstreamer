package us.nineworlds.xstreamer.ia.jobs;

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
import us.nineworlds.iadata.IASpec;
import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.model.lookup.PilotLookup;
import us.nineworlds.xstreamer.model.lookup.ShipsLookup;
import us.nineworlds.xstreamer.model.lookup.UpgradeLookup;
import us.nineworlds.xstreamer.preferences.PreferenceConstants;

public class GenerateArmyJob extends Job {

	IASpec iaspec;
	String playerFilename;
	String templateFilename;
	public GenerateArmyJob(String name, IASpec model, String preferncePlayerFileName, String templateFileName) {
		super(name);
		iaspec = model;
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

		Writer playerArmyFile = null;
		try {
			Configuration config = us.nineworlds.xstreamer.core.Activator.getDefault().getFreemarkerConfig();
			config.setDirectoryForTemplateLoading(new File(templateInputDirectory));
			
			Path path = Paths.get(templateFilename);
			Template armyTemplate = config.getTemplate(path.getFileName().toString());
			playerArmyFile = new FileWriter(new File(templateOutputDirectory + File.separator + playerFilename));

			Map<String, Object> input = new HashMap<>();

			input.put("iaspec", iaspec);
			input.put("allDeployments", us.nineworlds.xstreamer.ia.core.Activator.getDefault().getDeploymentsLookup().getDeployments());
			input.put("allCommandCards", us.nineworlds.xstreamer.ia.core.Activator.getDefault().getCommandCardLookup().getCommandCards());

			armyTemplate.process(input, playerArmyFile);
		} catch (Exception e) {
			e.printStackTrace();
			return Status.CANCEL_STATUS;
		} finally {
			IOUtils.closeQuietly(playerArmyFile);			
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
