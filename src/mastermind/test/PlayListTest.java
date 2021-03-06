/**
 * 
 */
package mastermind.test;

import static org.junit.Assert.*;

import java.io.IOException;

import mastermind.core.Code;
import mastermind.core.ColorPeg;
import mastermind.core.GameModel;
import mastermind.core.PlayList;
import mastermind.core.commands.PlayCommand;
import mastermind.core.commands.ProvideFeedbackCommand;
import mastermind.core.commands.SubmitGuessCommand;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Andres J Ruiz(ajr2546@rit.edu)
 * 
 */
public class PlayListTest {

	private PlayList play;

	private Code testCode1;
	private Code testCode2;
	private Code testCode3;

	private Code secret;
	private GameModel theGame;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		play = new PlayList();

		testCode1 = new Code(new ColorPeg[] { ColorPeg.RED, ColorPeg.BLUE,
				ColorPeg.GREEN, ColorPeg.RED });
		testCode2 = new Code(new ColorPeg[] { ColorPeg.RED, ColorPeg.YELLOW,
				ColorPeg.RED, ColorPeg.PURPLE });
		testCode3 = new Code(new ColorPeg[] { ColorPeg.PURPLE, ColorPeg.YELLOW,
				ColorPeg.BLUE, ColorPeg.PURPLE });

		secret = new Code(new ColorPeg[] { ColorPeg.PURPLE, ColorPeg.YELLOW,
				ColorPeg.BLUE, ColorPeg.PURPLE });
		theGame = new GameModel();
	}

	@Test
	public void emptyPlayList() {
		assertEquals("PlayList not working for empty model",
				play.getLastPlayIndex(), 0);
	}

	@Test
	public void manualAddPlayList() {
		// Manually add a new guess into the system
		play.addNewCode(testCode1);

		assertEquals(
				"PlayList is not updating it's index when a new code is added.",
				play.getLastPlayIndex(), 0);
		assertEquals("Code is not there!", play.getLatestMove().getCode(),
				testCode1);
		assertEquals("Where did that feedback come from!", play.getLatestMove()
				.getFeedback(), null);
	}

	@Test
	public void commandAddPlayList() {
		PlayCommand p = new PlayCommand();

		try {
			p.add(new SubmitGuessCommand(play, testCode1.getPegs()));
			p.add(new SubmitGuessCommand(play, testCode2.getPegs()));
			p.add(new SubmitGuessCommand(play, testCode3.getPegs()));
		} catch (IOException io) {
			fail();
		}

		p.execute();

		assertEquals(
				"PlayList is not updating it's index when a new code is added.",
				play.getLastPlayIndex(), 2);
	}

	@Test
	public void commandAddPlayListPlusFeedback() {
		PlayCommand p = new PlayCommand();

		try {
			p.add(new SubmitGuessCommand(play, testCode1.getPegs()));
			p.add(new ProvideFeedbackCommand(this.theGame, play, secret
					.getPegs()));
		} catch (IOException io) {
			fail();
		}

		p.execute();

		assertEquals(
				"PlayList is not updating it's index when a new code is added.",
				play.getLastPlayIndex(), 0);
		assertFalse(play.getLatestMove().getFeedback() == null);
		assertFalse(play.getMove(0).getFeedback() == null);

		System.out.println(play.getMove(0));
	}

}
