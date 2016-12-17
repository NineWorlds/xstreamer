package us.nineworlds.xstreamer.jobs;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

import freemarker.template.Configuration;
import freemarker.template.Template;
import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.model.dice.DiceResults;
import us.nineworlds.xstreamer.preferences.PreferenceConstants;

public class GenerateDiceResultsJob extends Job {

	DiceResults diceResults;
	public GenerateDiceResultsJob(String name, DiceResults diceResults) {
		super(name);
		this.diceResults = diceResults;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String templateOutputDirectory = preferenceStore.getString(PreferenceConstants.TEMPLATE_XSTREAMER_OUTPUT_DIRECTORY);
		String templateInputDirectory = preferenceStore.getString(PreferenceConstants.TEMPLATE_INPUT_DIRECTORY);
		if (StringUtils.isEmpty(templateOutputDirectory) || StringUtils.isEmpty(templateInputDirectory)) {
			return Status.CANCEL_STATUS;
		}		

		Writer diceResultsFile = null;
		try {
			Configuration config = us.nineworlds.xstreamer.core.Activator.getDefault().getFreemarkerConfig();
			config.setDirectoryForTemplateLoading(new File(templateInputDirectory));
			Template diceResultsTemplate = config.getTemplate("dice/html/diceResults.ftl");
			diceResultsFile = new FileWriter(new File(templateOutputDirectory + File.separator + "diceresults.html"));

			Map<String, Object> input = new HashMap<>();

			input.put("diceresults", diceResults);

			diceResultsTemplate.process(input, diceResultsFile);
		} catch (Exception e) {
			e.printStackTrace();
			return Status.CANCEL_STATUS;
		} finally {
			IOUtils.closeQuietly(diceResultsFile);			
		}
		return Status.OK_STATUS;
	}
}
