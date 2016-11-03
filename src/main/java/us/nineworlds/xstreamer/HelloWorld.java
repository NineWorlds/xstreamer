package us.nineworlds.xstreamer;

import org.swixml.jsr296.SwingApplication;

import javax.swing.*;

public class HelloWorld extends SwingApplication {


	/** Makes the class bootable */
	public static void main(String[] args) throws Exception {
		SwingApplication.launch(HelloWorld.class, args);
	}

	@Override
	protected void startup() {
		// TODO Auto-generated method stub
		try {
			JFrame frame = render(new HelloWorldJFrame(), "xml/helloworld.xml");
			show(frame);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exit();
		}
	}
}