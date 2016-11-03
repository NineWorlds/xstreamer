package us.nineworlds.xstreamer;

import java.awt.Container;

import javax.swing.JFrame;

import org.swixml.jsr296.SwingApplication;

public class XStreamer extends SwingApplication {
	
	public static void main(String[] args) throws Exception {
		SwingApplication.launch(XStreamer.class, args);
	}

	@Override
	protected void startup() {
		try {
			JFrame jframe = render(new XStreamerFrame(), "xml/layout/main-layout.xml");
			show(jframe);
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
	}
}
