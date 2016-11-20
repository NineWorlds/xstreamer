package us.nineworlds.xstreamer.ui.importWizards;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import com.example.rcpapp.Activator;
import com.github.xws.XwsSpec;

import us.nineworlds.xstreamer.model.ImportPlayerFile;

public class PlayerSquads extends Wizard implements IImportWizard {
	
	PlayerSquadsImportWizardPage mainPage;

	public PlayerSquads() {
		super();
	}

	public boolean performFinish() {
		String player1File = mainPage.getEditorPlayer1().getStringValue();
		String player2File = mainPage.getEditorPlayer2().getStringValue();
		
		if (StringUtils.isEmpty(player1File) || StringUtils.isEmpty(player2File)) {
			return false;
		}
		
		ImportPlayerFile importPlayerFile = new ImportPlayerFile();
		try {
			XwsSpec player1Xws = importPlayerFile.load(player1File);
			XwsSpec player2Xws = importPlayerFile.load(player2File);
			Activator.setPlayer1(player1Xws);
			Activator.setPlayer2(player2Xws);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
        return true;
	}
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("XStreamer File Import Wizard"); //NON-NLS-1
		setNeedsProgressMonitor(false);
		mainPage = new PlayerSquadsImportWizardPage("Import Player Squads"); //NON-NLS-1
	}
	
    public void addPages() {
        super.addPages(); 
        addPage(mainPage);        
    }

}
