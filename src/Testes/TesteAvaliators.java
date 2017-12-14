package Testes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.File;

import org.junit.jupiter.api.Test;

import Avaliators.FalseNegativeAvaliator;
import Avaliators.FalsePositiveAvaliator;
import GUI.GUI;

class TesteAvaliators {

	GUI gui; 
	FalsePositiveAvaliator positive;
	FalseNegativeAvaliator negative;

	@SuppressWarnings("static-access")
	@Test
	void test() {
		gui = new GUI(100, 100);

		for (int i = 0; i < 3; i++) {
			gui.getLista_de_botoes().get(i).doClick();
			System.out.println(gui.getLista_de_botoes().get(i).getText());
			assertTrue(gui.getLista_de_botoes().get(i).isEnabled());
		}

		for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
			gui.getMapa().put((String) gui.getLista_regras_pesos_manual().getValueAt(i, 0), Double.parseDouble((String) gui.getLista_regras_pesos_manual().getValueAt(i, 1)));
		}

		gui.getMapa().replace("BAYES_00", 50.0);
		gui.getMapa().replace("BAYES_99", -60.0);


		File HAM_test = new File(gui.getHam().getText());
		File SPAM_test = new File(gui.getSpam().getText());

		positive= new FalsePositiveAvaliator(HAM_test);
		negative= new FalseNegativeAvaliator(SPAM_test);
		assertNotNull(negative);
		assertNotNull(positive);

		positive.start();
		try {
			positive.join();
		} catch (InterruptedException e1) {
		}
		assertEquals(692, positive.getFalsePositives());
		negative.start();
		try {
			negative.join();
		} catch (InterruptedException e1) {
		}
		assertEquals(227, negative.getFalseNegatives());

	}
}
