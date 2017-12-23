package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import GUI.GUI;

/**
 * Test class than will test all the evaluation structures (e.g. SpamReader, HamReader, SolutionReader).
 *  
 * @author ES1-2017-IC1-70
 * @version 0.9
 */

class TestDataStructures {
	
	/**
	 * Start the test with a creation of a GUI window and click on the buttons on the lower part of the window.
	 */


	
	
	void init()
	{
		GUI gui =  new GUI(1100, 1100);
		for (int i = 0; i < 3; i++) {
			GUI.getButtonList().get(i).doClick();
		}
		GUI.getButtonList().get(9).doClick();
	}

	/**
	 * Test if SpamReader reads the file spam.log and creates the spamMap correctly with the correct size, corresponding with the number of lines of this file.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void testSpamReader() throws InterruptedException {
		init();
		assertNotNull(GUI.getSpamMap());
		assertEquals(239, GUI.getSpamMap().size());
	}

	/**
	 * Test if HamReader reads the file ham.log and creates the hamMap correctly with the correct size, corresponding with the number of lines of this file.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	void testHamReader() throws InterruptedException {
		init();
		assertNotNull(GUI.getHamMap());
		assertEquals(695, GUI.getHamMap().size());
	}

	/**
	 * Check if the number of rules read from solutions file is 335, corresponding with the number of rule solutions weights.
	 */
	@Test
	void testSolutionReader() {
		init();
		assertNotNull(GUI.getAutomaticRulesWeightList());
		assertEquals(335,GUI.getAutomaticRulesWeightList().getRowCount());

	}
}
