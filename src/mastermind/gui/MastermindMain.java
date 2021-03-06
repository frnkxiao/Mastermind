package mastermind.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mastermind.core.ColorPeg;
import mastermind.core.GameModel;
import mastermind.core.PlayList;
import mastermind.core.controller.IGameController;
import mastermind.interfaces.Observer;

public class MastermindMain extends JPanel implements Observer {

	/**
	 * Generated serial version uid
	 */
	private static final long serialVersionUID = 5305973473048673792L;
	// MODELS AND CONTROLLERS
	private PlayList dataBackend;
	private GameModel currentGame;
	private IGameController controller;
	private boolean newGameSelected;
	
	// Make this static to make sure no one tries to make more than one popup
	private static AtomicInteger popupCount;
	private boolean gameIsOver;
	
	// GUI VARIABLES
	private MastermindBoard board;
	private JButton submit;
	private JButton undo;
	private JButton newGame;
	private JCheckBox logging;

	public MastermindMain(IGameController controller) {
		this.dataBackend = controller.getPlaylist();
		this.currentGame = controller.getGameModel();
		this.controller = controller;
		this.newGameSelected = false;
		this.gameIsOver = false;

		this.setLayout(new BorderLayout());

		board = new MastermindBoard(this.dataBackend);
		JScrollPane boardContainer = new JScrollPane(board);

		this.add(boardContainer, BorderLayout.CENTER);
		this.add(this.generateOptions(), BorderLayout.EAST);

		// Register after initializing everything
		this.register();

		MastermindMain.popupCount = new AtomicInteger(0);

		if (null != this.currentGame.getGuessStrategy())
			this.controller.startAI();
		
		//If the codebreaker is ai disable the submit button
		if(this.currentGame.isCodeBreakerAI()){
			submit.setEnabled(false);
		}
		
		//If the logging was selected before, mark it as enabled
		
		if(this.currentGame.isLoggingEnabled()){
			logging.setSelected(true);
		}
		
		//System.out.println("MainView Created()");
	}

	@Override
	public void register() {
		this.dataBackend.register(this);
		this.currentGame.register(this);
	}

	@Override
	public void notifyChange() {
		// Revalidate the internal board
		board.invalidate();
		board.revalidate();

		// Check if the game is over
		if (currentGame.isGameOver()) {
			board.gameIsOver();
			submit.setEnabled(false);
			undo.setEnabled(false);
			showWinningMessage(currentGame.getWinningMessage());
		}
	}

	/**
	 * Show a pop-up window saying that the game is over
	 * 
	 * @param winningMessage
	 */
	private synchronized void showWinningMessage(String winningMessage) {
		System.out.println("Called");
		if (!gameIsOver && MastermindMain.popupCount.getAndAdd(1) == 0) {
			JOptionPane.showMessageDialog(this, winningMessage);
			gameIsOver = true;
		}
	}

	/**
	 * Return a panel with the options in it, simply to separate the logic into
	 * consise parts
	 * 
	 * @return Built JPanel
	 */
	private JPanel generateOptions() {
		JPanel options = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());

		submit = new JButton("Submit");

		// Action Listener to submit the code
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Input Validation
				ColorPeg[] code = board.getLastGuess();
				try {
					controller.submitGuess(code);
				} catch (IllegalArgumentException ex) {
					JOptionPane
							.showMessageDialog(MastermindMain.this,
									"The Code you tried to submit is not valid, please try again");
				}
			}

		});

		undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (board.isBeingEdited()) {
					board.clearLastRow();
					notifyChange();
				} else {
					controller.undoCommand();
				}
			}
		});

		newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentGame.triggerNewGame();
			}

		});

		logging = new JCheckBox("Logging Enabled");

		logging.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JCheckBox l = (JCheckBox) e.getSource();
				boolean returnValue = LogfilePickerGenerator
						.generateAndShow(MastermindMain.this);

				l.setSelected(returnValue);

			}
		});

		// Setup the gridbag layout options
		GridBagConstraints c = new GridBagConstraints();

		// Check Panel Settings
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 40, 0);

		options.add(logging, c);

		// Button Panel Settings
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);

		buttonPanel.add(submit);
		buttonPanel.add(undo);
		options.add(buttonPanel, c);

		c.gridy = 2;

		options.add(newGame, c);

		return options;
	}

	/**
	 * Returns the this view
	 * 
	 * @return Main View
	 */
	public JPanel getView() {
		return this;
	}

	/**
	 * Is this view done, and no longer needed
	 * 
	 * @return Do we need to dispose of this view
	 */
	public boolean isDone() {
		return newGameSelected;
	}

	/**
	 * Dispose of the window, to avoid any problems with leaking memory
	 */
	public void dispose() {
		this.invalidate();
		gameIsOver = true;
	}

}
