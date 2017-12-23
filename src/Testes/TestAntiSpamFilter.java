package Testes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.uma.jmetal.util.JMetalException;

import GUI.GUI;
import InputOutput.HamReader;
import InputOutput.SpamReader;
import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;

/**
 * This class tests the AntiSpamFilter class
 *@author ES1-2017-IC1-70
 * @version 0.9
 *
 */
class TestAntiSpamFilter {

	
	/**
	 * Instantiates the GUI
	 */
	void init() {
		@SuppressWarnings("unused")
		GUI gui =  new GUI(1100, 1100);
		for (int i = 0; i < 3; i++) {
			GUI.getButtonList().get(i).doClick();
		}
	}

	/**
	 * Creates the configuration class and it's problem and all the data structures 
	 * with which the Problem class will operate.
	 * Asserts id the structures are not null and if the computed Rules Map has definitely 335 rules 
	 * as the current rules document has.
	 * After the creation of the configuration class is asserts if this latter is not null.
	 */

	@Test
	void testAntiSpamConfiguration() {
		init();
		for (int i = 0; i < GUI.getAutomaticRulesWeightList().getRowCount(); i++) {
			GUI.getRulesMap().put((String) GUI.getAutomaticRulesWeightList().getValueAt(i, 0), 0.0);
		}	

		GUI.setHamFile(new File(GUI.getTextFieldHam().getText()));
		HamReader hamReader = new HamReader (GUI.getHamFile());
		hamReader.start();

		GUI.setSpamFile(new File(GUI.getTextFieldSpam().getText()));
		SpamReader spamReader = new SpamReader(GUI.getSpamFile()); 
		spamReader.start();

		AntiSpamFilterProblem problem = new AntiSpamFilterProblem(GUI.getRulesMap(), GUI.getSpamMap(), GUI.getHamMap());
		assertNotNull(problem);
		assertNotNull(problem.getHamMap());
		assertNotNull(problem.getSpamMap());
		assertNotNull(problem.getRulesMap());

		assertEquals(335, problem.getRulesMap().size());

		try {
			AntiSpamFilterAutomaticConfiguration configuration = new AntiSpamFilterAutomaticConfiguration(problem, 1);
			assertNotNull(configuration);
		}catch(JMetalException e) {
		}
	}
}
