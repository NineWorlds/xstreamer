package us.nineworlds.xstreamer.ia.jobs;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osgi.framework.Bundle;

import freemarker.template.Configuration;
import freemarker.template.Template;
import us.nineworlds.iadata.IASpec;
import us.nineworlds.xstreamer.ia.core.Activator;

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
		IPreferenceStore iaPreferenceStore = us.nineworlds.xstreamer.ia.Activator.getDefault().getPreferenceStore();		
		boolean obsRefreshFlag = iaPreferenceStore.getBoolean(us.nineworlds.xstreamer.ia.preferences.PreferenceConstants.OBS_REFRESH_SCRIPT);

		String templateInputDirectory = templateInputDirectory();		
		String templateOutputDirectory = iaPreferenceStore.getString(us.nineworlds.xstreamer.ia.preferences.PreferenceConstants.TEMPLATE_XSTREAMER_IA_OUTPUT_DIRECTORY);
		
		if (StringUtils.isEmpty(templateOutputDirectory) || StringUtils.isEmpty(playerFilename) ||
			StringUtils.isEmpty(templateFilename) || StringUtils.isEmpty(templateInputDirectory)) {
			return Status.CANCEL_STATUS;
		}		

		return writeArmy(obsRefreshFlag, templateInputDirectory, templateOutputDirectory);
	}

	private IStatus writeArmy(boolean obsRefreshFlag, String templateInputDirectory, String templateOutputDirectory) {
		Writer playerArmyFile = null;
		try {
			Configuration config = us.nineworlds.xstreamer.core.Activator.getDefault().getFreemarkerConfig();
			config.setDirectoryForTemplateLoading(new File(templateInputDirectory));
			
			java.nio.file.Path jpath = java.nio.file.Paths.get(templateFilename);
			Template armyTemplate = config.getTemplate(jpath.getFileName().toString());
			playerArmyFile = new FileWriter(new File(templateOutputDirectory + File.separator + playerFilename));

			Map<String, Object> input = new HashMap<>();

			input.put("iaspec", iaspec);
			input.put("allDeployments", Activator.getDefault().getDeploymentsLookup().getDeployments());
			input.put("allCommandCards", Activator.getDefault().getCommandCardLookup().getCommandCards());
			input.put("obs_refresh", obsRefreshFlag);

			armyTemplate.process(input, playerArmyFile);
		} catch (Exception e) {
			e.printStackTrace();
			return Status.CANCEL_STATUS;
		} finally {
			IOUtils.closeQuietly(playerArmyFile);			
		}
		return Status.OK_STATUS;
	}

	private String templateInputDirectory() {
		Bundle templateBundle = Platform.getBundle("us.nineworlds.xstreamer.ia.data");
		Path path = new Path("templates");
		URL url = FileLocator.find(templateBundle, path, null);
		URL realUrl = null;
		String templateInputDirectory = null;
		try {
			realUrl = FileLocator.toFileURL(url);
			File templateDirectory = FileUtils.toFile(realUrl);			
			templateInputDirectory = templateDirectory.getCanonicalPath().toString() + File.separator + "army" + File.separator + "html";
		} catch (Exception ex) {
			
		}
		return templateInputDirectory;
	}
	
}
