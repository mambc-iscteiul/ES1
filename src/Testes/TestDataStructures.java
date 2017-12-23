package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import GUI.GUI;

class TestDataStructures {


	
	
	void init()
	{
		GUI gui =  new GUI(1100, 1100);
		for (int i = 0; i < 3; i++) {
			GUI.getButtonList().get(i).doClick();
		}
		GUI.getButtonList().get(9).doClick();
	}
	
	@Test
	void testSpamReader() throws InterruptedException {
		init();
		assertNotNull(GUI.getSpamMap());
		
		assertEquals(239, GUI.getSpamMap().size());
	}

	@Test
	void testHamReader() throws InterruptedException {
		init();
		assertNotNull(GUI.getHamMap());
		
		assertEquals(695, GUI.getHamMap().size());
	}

	@Test
	void testSolutionReader() {
		init();
		assertNotNull(GUI.getAutomaticRulesWeightList());
		assertEquals(335,GUI.getAutomaticRulesWeightList().getRowCount());

	}
}
