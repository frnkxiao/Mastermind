package mastermind.core;

import mastermind.interfaces.Observable;
import mastermind.interfaces.Observer;

/**
 * 
 * @author Andrew Church
 * 
 * Model class to represent the state of the game
 *
 */
public class GameModel extends Observable
{
	private GameWinner winner;
	private Boolean loggingEnabled;
	
	public GameModel()
	{
		this.winner = null;
		this.loggingEnabled = false;
	}
	
	/**
	 * Get the winner of the current game
	 * @return
	 */
	public GameWinner getWinner()
	{
		return this.winner;
	}
	
	/**
	 * Return a boolean to indicate whether the game is over
	 * @return
	 */
	public Boolean isGameOver()
	{
		if(null == winner)
			return false;
		else
			return true;
	}
	
	public Boolean isLoggingEnabled()
	{
		return this.loggingEnabled;
	}
	
	/**
	 * Enables logging and notifies observers
	 */
	public void enableLogging()
	{
		this.loggingEnabled = true;
		super.dataChanged();
	}
	
	/**
	 * Sets the winner of the game and notifies observers
	 * @param theWinner
	 */
	public void declareWinner(GameWinner theWinner)
	{
		if(null == theWinner)
			throw new IllegalArgumentException("You must supply a valid winner to declare");
		
		this.winner = theWinner;
		super.dataChanged();
	}
	
	/**
	 * Set the object up for a new game
	 */
	public void newGame()
	{
		this.winner = null;
	}

	@Override
	public void register(Observer object) {
		
		if(null == object)
			throw new IllegalArgumentException("Must supply a valid observer");
		
		this.observers.add(object);
		super.dataChanged();
	}
}