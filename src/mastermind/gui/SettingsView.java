package mastermind.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTextField;

import mastermind.interfaces.INotifiable;

public class SettingsView extends JPanel {

	/**
	 * Generate Serial Version ID
	 */
	private static final long serialVersionUID = 27122833485072815L;
	
	// GUI Variables
	private JPanel settingsWindow;
	private JButton next;
	private JLabel modeLabel;
	private JLabel codeMakerLabel;
	private JLabel codeBreakerLabel;
	private JLabel compDifficultyLabel;
	private JLabel guessIntervalLabel;
	private JLabel numGuessesLabel;
	private JCheckBox humanCodemaker;
	private JCheckBox compCodemaker;
	private JCheckBox humanCodebreaker;
	private JCheckBox compCodebreaker;
	private JComboBox modeSelect;
	private JComboBox compCodebreakerSelect;
	private JSlider guessIntervalSlider;
	private JTextField numGuessesField;
	
	public SettingsView(final INotifiable mainFrame) {
		super();
		settingsWindow = new JPanel();
		
		this.setLayout(new BorderLayout());
		settingsWindow.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		modeLabel = new JLabel("Game Mode");
		c.gridx = 0;
		c.gridy = 0;
		settingsWindow.add(modeLabel, c);
		
		codeMakerLabel = new JLabel("Code Maker");
		c.gridx = 0;
		c.gridy = 1;
		settingsWindow.add(codeMakerLabel, c);
		
		codeBreakerLabel = new JLabel("Code Breaker");
		c.gridx = 0;
		c.gridy = 2;
		settingsWindow.add(codeBreakerLabel, c);
		
		compDifficultyLabel = new JLabel("Computer Difficulty");
		c.gridx = 2;
		c.gridy = 3;
		settingsWindow.add(compDifficultyLabel, c);
		
		guessIntervalLabel = new JLabel("Guess Interval");
		c.gridx = 2;
		c.gridy = 4;
		settingsWindow.add(guessIntervalLabel, c);
		
		numGuessesLabel = new JLabel("Number of guesses per game");
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		settingsWindow.add(numGuessesLabel, c);
		
		humanCodemaker = new JCheckBox("Human");
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		settingsWindow.add(humanCodemaker, c);
		
		compCodemaker = new JCheckBox("Computer");
		c.gridx = 2;
		c.gridy = 1;
		settingsWindow.add(compCodemaker, c);
		
		humanCodebreaker = new JCheckBox("Human");
		c.gridx = 1;
		c.gridy = 2;
		settingsWindow.add(humanCodebreaker, c);
		
		compCodebreaker = new JCheckBox("Computer");
		c.gridx = 2;
		c.gridy = 2;
		settingsWindow.add(compCodebreaker, c);
		
		String[] modes = {"Novice", "Expert"};
		modeSelect = new JComboBox(modes);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		settingsWindow.add(modeSelect, c);
		
		String[] compDifficulty = {"Random"};
		compCodebreakerSelect = new JComboBox(compDifficulty);
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		settingsWindow.add(compCodebreakerSelect, c);
		
		guessIntervalSlider = new JSlider();
		c.gridx = 3;
		c.gridy = 4;
		settingsWindow.add(guessIntervalSlider, c);
		
		numGuessesField = new JTextField();
		c.gridx = 2;
		c.gridy = 5;
		settingsWindow.add(numGuessesField, c);
		
		
		next = new JButton("NEXT");
		this.add(next, BorderLayout.SOUTH);
		this.add(settingsWindow, BorderLayout.CENTER);

		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.Notify();
			}

		});
	}
}
