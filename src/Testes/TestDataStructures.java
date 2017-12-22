package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import GUI.GUI;

class TestDataStructures {

	void init()
	{
		GUI gui =  new GUI(1100, 1100);
		for (int i = 0; i < 10; i++) {
			GUI.getButtonList().get(i).doClick();
		}
	}

	@Test
	void testSpamReader() throws InterruptedException {
		init();
		assertNotNull(GUI.getSpamMap());
		System.out.println("-------------------LI AGORA--------------------------");
		assertEquals(239, GUI.getSpamMap().size());
	}

	@Test
	void testHamReader() throws InterruptedException {
		init();
		assertNotNull(GUI.getHamMap());
		System.out.println("-------------------LI AGORA--------------------------");
		assertEquals(695, GUI.getHamMap().size());
	}


}
