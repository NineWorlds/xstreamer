package us.nineworlds.xstreamer.ia.perspective;

import java.io.PrintStream;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);

		MessageConsole myConsole = new MessageConsole("Console", null); 
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { myConsole });

		MessageConsoleStream stream = myConsole.newMessageStream();

		PrintStream myS = new PrintStream(stream);
		System.setOut(myS); // link standard output stream to the console
		System.setErr(myS); // link error output stream to the console
	}
	
}
