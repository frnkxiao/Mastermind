package mastermind.test;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import mastermind.core.Code;
import mastermind.core.ColorPeg;



public class CodeTest {
	private Code test1;
	private Code test2;
	
	@Before 
	public void setUp() throws Exception{
		ColorPeg[] colors = new ColorPeg[4];
		colors[0] = ColorPeg.BLUE;
		colors[1] = ColorPeg.YELLOW;
		colors[2] = ColorPeg.GREEN;
		colors[3] = ColorPeg.RED;
		test1 = new Code(colors);
		test2 = new Code();
		System.out.println("spacedicks");
	}
	
	@Test
	public void testGetPegs(){
		ColorPeg[] testcolors = test1.getPegs();
		assert(testcolors[0].getColor() == Color.BLUE);
		assert(testcolors[1].getColor() == Color.YELLOW);
		assert(testcolors[2].getColor() == Color.GREEN);
		assert(testcolors[3].getColor() == Color.RED);
	}
	
	@Test
	public void testSetPegs(){
		ColorPeg cpeg = ColorPeg.GREEN;
		test2.setPegs(0, cpeg);
		ColorPeg[] testcode = test2.getPegs();
		assert(testcode[0].getColor() == Color.GREEN);
		}
	
	@Test
	public void testToString(){
		assert(test1.toString().equals("Blue Yellow Green Red "));
	}
}
