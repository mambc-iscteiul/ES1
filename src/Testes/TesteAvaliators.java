package Testes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;

import org.junit.jupiter.api.Test;

import Avaliators.FalseNegativeAvaliator;
import Avaliators.FalsePositiveAvaliator;
import GUI.GUI;

class TesteAvaliators {

	GUI gui; 
	FalsePositiveAvaliator falsePositiveAvaliator;
	FalseNegativeAvaliator falseNegativeAvaliator;

	@SuppressWarnings("static-access")
	@Test
	void test() {
		gui = new GUI(100, 100);

		for (int i = 0; i < 3; i++) {
			gui.getButtonList().get(i).doClick();
			System.out.println(gui.getButtonList().get(i).getText());
			assertTrue(gui.getButtonList().get(i).isEnabled());
		}

		for (int i = 0; i < GUI.getManualRulesWeightList().getRowCount(); i++) {
			gui.getRulesMap().put((String) gui.getManualRulesWeightList().getValueAt(i, 0), Double.parseDouble((String) gui.getManualRulesWeightList().getValueAt(i, 1)));
		}

		gui.getRulesMap().replace("BAYES_00", 50.0);
		gui.getRulesMap().replace("BAYES_99", -60.0);


		File HAM_test = new File(gui.getTextFieldHam().getText());
		File SPAM_test = new File(gui.getTextFieldSpam().getText());

		falsePositiveAvaliator= new FalsePositiveAvaliator(HAM_test);
		falseNegativeAvaliator= new FalseNegativeAvaliator(SPAM_test);
		assertNotNull(falseNegativeAvaliator);
		assertNotNull(falsePositiveAvaliator);

		falsePositiveAvaliator.start();
		try {
			falsePositiveAvaliator.join();
		} catch (InterruptedException e1) {
		}
		assertEquals(692, falsePositiveAvaliator.getFalsePositivesCount());
		falseNegativeAvaliator.start();
		try {
			falseNegativeAvaliator.join();
		} catch (InterruptedException e1) {
		}
		assertEquals(227, falseNegativeAvaliator.getFalseNegativesCount());

	}
}
