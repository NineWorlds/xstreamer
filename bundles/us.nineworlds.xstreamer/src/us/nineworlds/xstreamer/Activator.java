package us.nineworlds.xstreamer;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;


public class Activator extends AbstractUIPlugin {

	private static long countDownTime;
	private static Activator plugin;

	public Activator() {
		plugin = this;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}
	
	public static Activator getDefault() {
		return plugin;
	}

	public static synchronized long getCountDownTime() {
		return countDownTime;
	}

	public static synchronized void setCountDownTime(long countDownTime) {
		Activator.countDownTime = countDownTime;
	}

}
