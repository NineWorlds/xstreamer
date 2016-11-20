package us.nineworlds.xstreamer.ui.importWizards;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.example.rcpapp.Activator;
import com.github.xws.XwsSpec;

import us.nineworlds.xstreamer.forms.FirstPlayerPage;
import us.nineworlds.xstreamer.forms.SecondPlayerPage;
import us.nineworlds.xstreamer.jobs.GenerateSquadJob;
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
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			FirstPlayerPage firstPlayer = (FirstPlayerPage) page.findView(FirstPlayerPage.FIRST_PLAYER_VIEW);
			firstPlayer.refreshTree();
			SecondPlayerPage secondPlayer = (SecondPlayerPage) page.findView(SecondPlayerPage.SECOND_PLAYER_VIEW);
			secondPlayer.refreshTree();
			
			GenerateSquadJob job1 = new GenerateSquadJob("player1Job", firstPlayer.getPlayerModel(), firstPlayer.playerFileName(), firstPlayer.squadTemplate());
			job1.schedule();
			
			GenerateSquadJob job2 = new GenerateSquadJob("player2Job", secondPlayer.getPlayerModel(), secondPlayer.playerFileName(), secondPlayer.squadTemplate());
			job2.schedule();
			
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
