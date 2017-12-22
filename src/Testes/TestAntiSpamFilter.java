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

class TestAntiSpamFilter {


	void init() {
		GUI gui =  new GUI(1100, 1100);
		for (int i = 0; i < 3; i++) {
			GUI.getButtonList().get(i).doClick();
		}
	}

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
		assertEquals(695, problem.getHamMap().size());
		assertEquals(239, problem.getSpamMap().size());
		try {
			AntiSpamFilterAutomaticConfiguration configuration = new AntiSpamFilterAutomaticConfiguration(problem, 1);
			assertNotNull(configuration);
		}catch(JMetalException e) {
		}
	}
}
