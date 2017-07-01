package us.nineworlds.xstreamer;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class Logger {
	
	static final ILog logger = Activator.getDefault().getLog();
	

	public static void info(String message) {
		log(IStatus.INFO, IStatus.OK, message, null);
	}
	
	public static void error(String message, Throwable exception) {
		log(IStatus.ERROR, IStatus.ERROR, message, exception);
	}
	
	public static void error(String message) {
		log(IStatus.ERROR, IStatus.ERROR, message, null);
	}
		
	private static void log(int severity, int code, String message, Throwable exception) {
		log(createStatus(severity, code, message, exception));
	}
	
	private static IStatus createStatus(int severity, int code, String message, Throwable exception) {
		return new Status(severity, Activator.BUNDLE_ID, code, message, exception);
	}
	
	private static void log(IStatus status) {
		logger.log(status);
	}

}
