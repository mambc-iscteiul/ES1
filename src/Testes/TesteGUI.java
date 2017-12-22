package Testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;

import GUI.DefaultTableModelEdited;
import GUI.DefaultTableModelEdited.ListType;
import GUI.GUI;
import InputOutput.HamReader;
import InputOutput.RulesReader;
import InputOutput.SpamReader;
import InputOutput.Writer;
import Launch.Launch;
/**
 * @author ES1-2017-IC1-70
 * @version 0.9
 */
class TesteGUI {

	Launch launch = new Launch();
	private RulesReader rulesReader;
	private HamReader hamReader;
	private SpamReader spamReader;
	private Writer writer;
	JButton tester;

	@Test
	void testGUI() {
		Launch.main(null);
		assertNotNull(GUI.class);
	}

	@Test
	void testRulesReader() {
		@SuppressWarnings("unused")
		GUI gui= new GUI(0, 0);
		rulesReader = new RulesReader("./AntiSpamConfigurationForProfessionalMailbox/rules.cf");
		rulesReader.run();
		assertNotNull(GUI.getManualRulesWeightList());
		assertEquals(335, GUI.getManualRulesWeightList().getRowCount());
	}

	@Test
	void testDefaultTableModelEdited() {
		DefaultTableModelEdited manualList = new DefaultTableModelEdited(null, 1, ListType.MANUAL);
		String[] randomLine1= {"Hello World","0.0"};
		manualList.addRow(randomLine1);

		assertTrue(manualList.isCellEditable(0, 1));

		DefaultTableModelEdited automaticList = new DefaultTableModelEdited(null, 1, ListType.AUTOMATIC);
		assertFalse(automaticList.isCellEditable(0, 1));

	}


	@Test
	void testButtons() {
		GUI.ActivateButons();	
		for (int i = 0; i < GUI.getButtonList().size(); i++) {
			assertNotNull(GUI.getButtonList().get(i));
			assertTrue(GUI.getButtonList().get(i).isEnabled());
			GUI.getButtonList().get(i).doClick();
		}
	}

	@Test
	void testeWriter() {
		//talvez ler o ficheiro e assert de que foram botadas 335 linhas
		try {
			GUI.setRulesFile(new File(GUI.getTextFieldRules().getText()));
			writer = new Writer(GUI.getRulesFile(),GUI.getManualRulesWeightList());
			writer.start();
			assertNotNull(writer.getWriter());
			assertNotNull(writer.getFileWriter());
		} catch (IOException e) {
		}
	}

	@Test
	void testFileChooser() {
		for (int i = 0; i < 3; i++) {
			GUI.getButtonList().get(i).doClick();
			System.out.println(GUI.getButtonList().get(i).getText());
			assertTrue(GUI.getButtonList().get(i).isEnabled());
		}
		assertFalse(GUI.getTextFieldRules().getText().equals(" "));
		assertFalse(GUI.getTextFieldHam().getText().equals(" "));
		assertFalse(GUI.getTextFieldSpam().getText().equals(" "));
	}







}
