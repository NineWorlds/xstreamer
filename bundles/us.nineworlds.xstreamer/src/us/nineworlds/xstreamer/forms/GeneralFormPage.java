package us.nineworlds.xstreamer.forms;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.ViewPart;

import us.nineworlds.xstreamer.Activator;
import us.nineworlds.xstreamer.forms.listeners.PlayerNameUpdateButtonSelectionListener;
import us.nineworlds.xstreamer.forms.listeners.PlayerScoreUpdateButtonSelectionListener2;
import us.nineworlds.xstreamer.jobs.GenerateDiceResultsJob;
import us.nineworlds.xstreamer.jobs.GenerateSquadJob;
import us.nineworlds.xstreamer.jobs.StringWriterJob;
import us.nineworlds.xstreamer.model.dice.DiceResults;
import us.nineworlds.xstreamer.text.util.TextUtil;

public class GeneralFormPage extends ViewPart {
	
	public static String GENERAL_VIEW_ID = "us.nineworlds.xstreamer.view.general";
		
	public Text firstPlayerName;
	public Text secondPlayerName;
	public Text newsTickerText;
	public Text firstPlayerScore;
	public Text secondPlayerScore;
	
	public Text attackHits;
	public Text attackCrits;
	public Text attackFocus;
	public Text attackMisses;
	
	public Text defenseMisses;
	public Text defenseFocus;
	public Text defenseEvades;

	
	private IPreferenceStore preference;

	private FormToolkit toolkit;
	private ScrolledForm form;
	
	public GeneralFormPage() {
		preference = Activator.getDefault().getPreferenceStore();
	}

	@Override
	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		ColumnLayout layout = new ColumnLayout();
		form.getBody().setLayout(layout);
		
		createPlayerSection();
		createScoringSection();
		createDiceSection();
		createNewsTickerSection();
	}

	private void createPlayerSection() {
		Section playersSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
		playersSection.setText("Player Section");
		playersSection.setExpanded(true);
		playersSection.setDescription("The players that are playing.");
		Composite playerClient = toolkit.createComposite(playersSection);
		GridLayout playerGridLayout = new GridLayout();
		playerGridLayout.numColumns = 2;
		playerClient.setLayout(playerGridLayout);
		
		firstPlayerName = createPlayerNameField(playerClient, "Player 1: ");
		secondPlayerName = createPlayerNameField(playerClient, "Player 2: ");
				
		Button updateButton = toolkit.createButton(playerClient, "Update", SWT.PUSH | SWT.RESIZE);
		GridData updateButtonData = new GridData();
		updateButtonData.horizontalSpan = 2;
		updateButtonData.widthHint = 60;
		updateButtonData.grabExcessHorizontalSpace = true;
		updateButton.setLayoutData(updateButtonData);
		updateButton.addSelectionListener(new PlayerNameUpdateButtonSelectionListener(this));
				
		playersSection.setClient(playerClient);
	}
	
	private void createScoringSection() {
		Section playersSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
		playersSection.setText("Scoreboard Section");
		playersSection.setExpanded(false);
		playersSection.setDescription("Current scores for the game");
		Composite playerClient = toolkit.createComposite(playersSection);
		GridLayout playerGridLayout = new GridLayout();
		playerGridLayout.numColumns = 2;
		playerClient.setLayout(playerGridLayout);
		
		firstPlayerScore = createPlayerNameField(playerClient, "Player 1: ");
		secondPlayerScore = createPlayerNameField(playerClient, "Player 2: ");
				
		Button updateButton = toolkit.createButton(playerClient, "Update", SWT.PUSH | SWT.RESIZE);
		GridData updateButtonData = new GridData();
		updateButtonData.horizontalSpan = 2;
		updateButtonData.widthHint = 60;
		updateButtonData.grabExcessHorizontalSpace = true;
		updateButton.setLayoutData(updateButtonData);
		updateButton.addSelectionListener(new PlayerScoreUpdateButtonSelectionListener2(this));
				
		playersSection.setClient(playerClient);
	}

	
	private Text createPlayerNameField(Composite parent, String label) {
		toolkit.createLabel(parent, label);
		GridData playerAttributs = new GridData();
		playerAttributs.widthHint = 200;
		playerAttributs.grabExcessHorizontalSpace = true;
		
		Text player  = toolkit.createText(parent, "");
		player.setLayoutData(playerAttributs);
		return player; 
	}
	
	private void createNewsTickerSection() {
		Section section = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
		section.setText("News Ticker");
		section.setExpanded(false);
		section.setDescription("News ticker allows for the entry of data to be displayed in a scrolling textfield. Output is written to a file called ticker.txt");
		Composite composite = toolkit.createComposite(section);
		GridLayout gridlayout = new GridLayout();
		gridlayout.numColumns = 2;
		composite.setLayout(gridlayout);
		
		newsTickerText = toolkit.createText(composite, "", SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		newsTickerText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		newsTickerText.setEditable(true);
		GridData newsData = (GridData) newsTickerText.getLayoutData();
		newsData.horizontalSpan = 2;
		newsData.heightHint = 100;
		newsData.minimumHeight = 100;
		
		Button updateButton = toolkit.createButton(composite, "Update", SWT.PUSH | SWT.RESIZE);
		GridData updateButtonData = new GridData();
		updateButtonData.horizontalSpan = 2;
		updateButtonData.widthHint = 60;
		updateButtonData.grabExcessHorizontalSpace = true;
		updateButton.setLayoutData(updateButtonData);
		updateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String news = newsTickerText.getText();
				if (StringUtils.isNotEmpty(news)) {
					StringWriterJob job = new StringWriterJob("newsticker", news, "ticker.txt");
					job.schedule();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		section.setClient(composite);
	}
	
	private void createDiceSection() {
		Section diceSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.DESCRIPTION| Section.TITLE_BAR);
		diceSection.setText("Dice Section");
		diceSection.setExpanded(false);
		diceSection.setDescription("Generate dice results.");
		Composite diceClient = toolkit.createComposite(diceSection);
		GridLayout diceGridLayout = new GridLayout();
		diceGridLayout.numColumns = 8;
		diceClient.setLayout(diceGridLayout);
		
		attackHits = createDiceField(diceClient, "Hits: ");
		TextUtil.addSelectOnFocusToText(attackHits);
		attackCrits = createDiceField(diceClient, "Crits: ");
		TextUtil.addSelectOnFocusToText(attackCrits);
		attackFocus = createDiceField(diceClient, "Focus: ");
		TextUtil.addSelectOnFocusToText(attackFocus);
		attackMisses = createDiceField(diceClient, "Blank: ");
		TextUtil.addSelectOnFocusToText(attackMisses);
				
		defenseEvades = createDiceField(diceClient, "Evades: ");
		TextUtil.addSelectOnFocusToText(defenseEvades);
		
		defenseFocus = createDiceField(diceClient, "Focus: ");
		TextUtil.addSelectOnFocusToText(defenseFocus);
		
		defenseMisses = createDiceField(diceClient, "Blank: ");
		TextUtil.addSelectOnFocusToText(defenseMisses);
		
		Label space = toolkit.createLabel(diceClient, "");
		GridData spacerData = new GridData();
		spacerData.horizontalSpan = 1;
		space.setData(spacerData);
				
		Button updateButton = toolkit.createButton(diceClient, "Update", SWT.PUSH | SWT.RESIZE);
		GridData updateButtonData = new GridData();
		updateButtonData.horizontalSpan = 2;
		updateButtonData.widthHint = 60;
		updateButtonData.grabExcessHorizontalSpace = false;
		updateButton.setLayoutData(updateButtonData);
		updateButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				generateDiceResults();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		Button resetButton = toolkit.createButton(diceClient, "Reset", SWT.PUSH | SWT.RESIZE);
		GridData resetButtonData = new GridData();
		resetButtonData.horizontalSpan = 2;
		resetButtonData.widthHint = 60;
		resetButtonData.grabExcessHorizontalSpace = false;
		resetButton.setLayoutData(updateButtonData);
		resetButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				initDiceResults();
				generateDiceResults();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		initDiceResults();

		diceSection.setClient(diceClient);
	}
	
	private void initDiceResults() {
		attackHits.setText("0");
		attackCrits.setText("0");
		attackFocus.setText("0");
		attackMisses.setText("0");
		defenseEvades.setText("0");
		defenseFocus.setText("0");
		defenseMisses.setText("0");
	}

	private Text createDiceField(Composite parent, String label) {
		toolkit.createLabel(parent, label);
		GridData playerAttributs = new GridData();
		playerAttributs.widthHint = 20;
		playerAttributs.grabExcessHorizontalSpace = false;
		
		Text player  = toolkit.createText(parent, "");
		player.setLayoutData(playerAttributs);
		return player; 
	}


	@Override
	public void setFocus() {
		form.setFocus();
	}
	
	@Override
	public void dispose() {
		toolkit.dispose();
		super.dispose();
	}

	private void generateDiceResults() {
		DiceResults results = new DiceResults();
		if (StringUtils.isNotEmpty(attackCrits.getText())) {
			results.setAttackCrits(Integer.valueOf(attackCrits.getText()));
		}
		if (StringUtils.isNotEmpty(attackHits.getText())) {
			results.setAttackHits(Integer.valueOf(attackHits.getText()));
		}
		if (StringUtils.isNotEmpty(attackFocus.getText())) {
			results.setAttackFocus(Integer.valueOf(attackFocus.getText()));
		}
		if (StringUtils.isNotEmpty(attackMisses.getText())) {
			results.setAttackMisses(Integer.valueOf(attackMisses.getText()));
		}
		if (StringUtils.isNotEmpty(defenseEvades.getText())) {
			results.setDefenseEvade(Integer.valueOf(defenseEvades.getText()));
		}
		if (StringUtils.isNotEmpty(defenseFocus.getText())) {
			results.setDefenseFocus(Integer.valueOf(defenseFocus.getText()));
		}
		if (StringUtils.isNotEmpty(defenseMisses.getText())) {
			results.setDefenseMisses(Integer.valueOf(defenseMisses.getText()));
		}
		GenerateDiceResultsJob job = new GenerateDiceResultsJob("diceresults", results);
		job.schedule();
	}
}
