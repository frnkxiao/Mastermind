package mastermind.core;

import mastermind.core.codebreaker.ComputerGuessBehavior;
import mastermind.core.codemaker.ICodemaker;
import mastermind.core.modes.IGameMode;

/**
 * The SettingsSelected concrete state class represents the state in which the settings for the game have been set.
 * In this state, setting the secret code is supported.
 * 
 * @author Andrew Church
 *
 */
public class SettingsSelected implements IGameState {

	private GameModel theModel;

	public SettingsSelected(GameModel model) {
		if (null == model)
			throw new IllegalArgumentException();

		this.theModel = model;
	}

	@Override
	public void setSecretCode(Code c) {

		if (null == c)
			throw new IllegalArgumentException();

		theModel.setSecretCode(c);
	}

	@Override
	public void setSettings(int gameGuesses, ICodemaker codeMaker,
			IGameMode mode, ComputerGuessBehavior guessStrategy,
			int guessInterval) {
		throw new IllegalStateException();
	}

	@Override
	public void submitGuess(ColorPeg[] code) {
		throw new IllegalStateException();
	}

}
