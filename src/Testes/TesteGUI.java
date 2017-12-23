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
 * Test class than tests all the existing functionalities on the GUI (e.g. buttons, text fields, tables).
 * 
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

	/**
	 * Run the Launch and check if the GUI is not null after this
	 */
	@Test
	void testGUI() {
		Launch.main(null);
		assertNotNull(GUI.class);
	}

	/**
	 * Creates a RulesReader, run it, and check if exists a List with weights of each rule from the file Rules.cf than is readed on the method run of RulesReader
	 * It also confirms if the number of rows readed from the file Rules.cf is equals to original number of rows in this file.
	 */
	@Test
	void testRulesReader() {
		@SuppressWarnings("unused")
		GUI gui= new GUI(0, 0);
		rulesReader = new RulesReader("./AntiSpamConfigurationForProfessionalMailbox/rules.cf");
		rulesReader.run();
		assertNotNull(GUI.getManualRulesWeightList());
		assertEquals(335, GUI.getManualRulesWeightList().getRowCount());
	}

	/**
	 * Creates a DefaultTableModelEdited and adds a test row, after that it will check if the columns are editable to both options, manual and automatic
	 */
	@Test
	void testDefaultTableModelEdited() {
		DefaultTableModelEdited manualList = new DefaultTableModelEdited(null, 1, ListType.MANUAL);
		String[] randomLine1= {"Hello World","0.0"};
		manualList.addRow(randomLine1);

		assertTrue(manualList.isCellEditable(0, 1));

		DefaultTableModelEdited automaticList = new DefaultTableModelEdited(null, 1, ListType.AUTOMATIC);
		assertFalse(automaticList.isCellEditable(0, 1));

	}

	
	/**
	 * Test all the buttons present in the GUI through the doClick() on each button
	 */
	@Test
	void testButtons() {
		GUI.ActivateButons();	
		for (int i = 0; i < GUI.getButtonList().size(); i++) {
			assertNotNull(GUI.getButtonList().get(i));
			assertTrue(GUI.getButtonList().get(i).isEnabled());
			GUI.getButtonList().get(i).doClick();
		}
	}

	/**
	 * Test the Writer through running the Writer and checking if Writer is not null, in other words, check if exists a Writer after its creation.
	 */
	@Test
	void testeWriter() {
		try {
			GUI.setRulesFile(new File(GUI.getTextFieldRules().getText()));
			writer = new Writer(GUI.getRulesFile(),GUI.getManualRulesWeightList());
			writer.start();
			assertNotNull(writer.getWriter());
			assertNotNull(writer.getFileWriter());
		} catch (IOException e) {
		}
	}

	/**
	 * Test the fileChooser, through clicking in all buttons (Rules, Ham, Spam) and checking if the Button List is enabled. After that, checks if the corresponding text fields are not empty.
	 */
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
