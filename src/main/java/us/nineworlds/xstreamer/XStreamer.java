package us.nineworlds.xstreamer;

import javax.swing.JFrame;

import org.swixml.jsr296.SwingApplication;

import com.github.xws.XwsSpec;

public class XStreamer extends SwingApplication {
	
	public static XwsSpec player1;
	public static XwsSpec player2;
	
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
