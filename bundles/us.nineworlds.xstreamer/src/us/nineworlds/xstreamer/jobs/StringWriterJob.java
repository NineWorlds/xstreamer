package us.nineworlds.xstreamer.jobs;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.preferences.PreferenceConstants;

public class StringWriterJob extends Job {

	String playerFilename;
	String content;
	public StringWriterJob(String jobname, String content, String playerFilename) {
		super(jobname);
		this.content = content;
		this.playerFilename = playerFilename;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String templateOutputDirectory = preferenceStore.getString(PreferenceConstants.TEMPLATE_XSTREAMER_OUTPUT_DIRECTORY);
		if (StringUtils.isEmpty(content) || StringUtils.isEmpty(playerFilename)) {
			return Status.CANCEL_STATUS;
		}
		
		try {
			FileUtils.writeStringToFile(new File(templateOutputDirectory + File.separator + playerFilename), content);
		} catch (Exception e) {
			e.printStackTrace();
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}
}
