package Testes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;

import org.junit.jupiter.api.Test;

import Avaliators.FalseNegativeAvaliator;
import Avaliators.FalsePositiveAvaliator;
import GUI.GUI;
import Launch.Launch;
/**
 * This class is the Tester class of the Threads which will, independently, calculate the false positives and negatives.
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */

class TesteEvaluatorThreads {

	Launch launchTester= new Launch();
	FalsePositiveAvaliator falsePositiveAvaliator;
	FalseNegativeAvaliator falseNegativeAvaliator;

	/**
	 * This test will call the FileChooser to sort which files are to be worked on.
	 * Firstly it will import a DefaultTableModel with the designated rules and creates a mapping 
	 * with those rules, the mapping will create ,for all rules,a 0.0 weight.
	 * It will replace 2 of those rules with values.
	 * Those rules will generate an exact number of false positives an negative which were previously 
	 * calculated  by hand. 
	 */
	
	@SuppressWarnings("static-access")
	@Test
	void testEvaluators() {
		launchTester.main(null);
		for (int i = 0; i < 3; i++) {
			GUI.getButtonList().get(i).doClick();
			System.out.println(GUI.getButtonList().get(i).getText());
			assertTrue(GUI.getButtonList().get(i).isEnabled());
		}

		for (int i = 0; i < GUI.getManualRulesWeightList().getRowCount(); i++) {
			GUI.getRulesMap().put((String) GUI.getManualRulesWeightList().getValueAt(i, 0), Double.parseDouble((String) GUI.getManualRulesWeightList().getValueAt(i, 1)));
		}

		GUI.getRulesMap().replace("BAYES_00", 6.0);
		GUI.getRulesMap().replace("BAYES_99", -6.0);

		File HAM_test = new File(GUI.getTextFieldHam().getText());
		File SPAM_test = new File(GUI.getTextFieldSpam().getText());

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

		assertNotNull(falseNegativeAvaliator.getRuleMap());
		assertNotNull(falsePositiveAvaliator.getRulesMap());
	}
}
