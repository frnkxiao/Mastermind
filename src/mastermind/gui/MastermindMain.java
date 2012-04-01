package mastermind.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import mastermind.core.Code;
import mastermind.core.PlayList;
import mastermind.core.controller.GameController;
import mastermind.interfaces.Observable;
import mastermind.interfaces.Observer;

public class MastermindMain implements Observer{
	
	//MODELS AND CONTROLLERS
	private PlayList dataBackend;
	private GameController controller;
	private Code secretCode;
	
	//GUI VARIABLES
	private JFrame mainWindow;
	private MastermindBoard board;
	
	public MastermindMain(GameController controller, PlayList model, Code secretCode){
		setLookAndFeel();
		
		this.dataBackend = model;
		this.controller = controller;
		this.secretCode = secretCode;
		
		mainWindow = new JFrame("Mastermind");//Set the JFrame with the window title
		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLayout(new BorderLayout());
		
		
		//Register after initializing everything
		this.register();
		board = new MastermindBoard(model);
		
		mainWindow.add(board, BorderLayout.CENTER);
		mainWindow.add(this.generateOptions(), BorderLayout.EAST);
		
		
		mainWindow.setSize(800, 600);
		mainWindow.setVisible(true);
	}

	@Override
	public void register() {
		dataBackend.register(this);
	}

	@Override
	public void notifyChange() {
		
	}
	
	private JPanel generateOptions(){
		JPanel options = new JPanel(new GridBagLayout());
		JPanel checkPanel = new JPanel(new FlowLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		
		JButton submit = new JButton("Submit");
		JButton undo = new JButton("Undo");
		JCheckBox computer = new JCheckBox("Computer Code Breaker");
		JCheckBox logging = new JCheckBox("logging");
		
		checkPanel.add(computer);
		checkPanel.add(logging);
		buttonPanel.add(submit);
		buttonPanel.add(undo);
		
		
		//Setup the gridbag layout options
		
		GridBagConstraints c = new GridBagConstraints();
		
		//Check Panel Settings
		c.gridx = 0;
		c.gridy = 0;
		
		options.add(checkPanel, c);

		//Button Panel Settings
		c.gridx = 0;
		c.gridy = 1;
		
		options.add(buttonPanel, c);
		
		return options;
	}
	
	/**
	 * Code to automatically detect the look and feel from the system. If the system
	 * doesn't have a native look and feel it tries the Nimbus Look and Feel which looks
	 * awesome. If none of them work... then default to do nothing.
	 */
	private static void setLookAndFeel(){
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				// AWESOMENESS GOING ON HERE!!
				
				//FOR TESTING Use this as an example to change the color scheme
				//see: http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/_nimbusDefaults.html#primary
//				float[] HSB = Color.RGBtoHSB(192, 194, 196, null);
//				System.out.println(HSB[0] + " " + HSB[1] + " " + HSB[2]);
//				UIManager.put("control", Color.getHSBColor(HSB[0], HSB[1], HSB[2]));
				
				//Set the color for progress bar
				Color windowsGreen =Color.getHSBColor(0.3647343f, 0.96279067f, 0.84313726f);
				
				UIManager.put("nimbusOrange", windowsGreen); //Change the color used for the progress bar
				
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					
					break;
				}
			}
		} catch (Exception ex) {
			// Ignore and continue
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
				// System.out.println(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				// e.printStackTrace();
				// Ignore again... wow what's installed in here o_0?
			}
		}
	}
	
	public static void main(String[] args){
		new MastermindMain(null, new PlayList(), null);
	}

}
