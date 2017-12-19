package Testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;

import GUI.GUI;
import InputOutput.Writer;
import InputOutput.RulesReader;
import Launch.Launch;

class TesteGUI {

	
	Launch launch = new Launch();
	private GUI gui = new GUI(100, 100);
	private RulesReader rulesReader = new RulesReader("C:/Users/Filipe/Documents/rules.cf");
	private Writer writer;
	JButton tester;


	
	@Test
	void testGUI() {
		assertNotNull(launch);
		//gui e lista
		assertNotNull(gui);
		rulesReader.run();
		assertNotNull(GUI.getManualRulesWeightList());
		assertEquals(335, GUI.getManualRulesWeightList().getRowCount());
		assertFalse(GUI.getManualRulesWeightList().isCellEditable(0, 0));

		//botoes
		GUI.ActivateButons();	
		for (int i = 0; i < gui.getButtonList().size(); i++) {
			gui.getButtonList().get(i).doClick();
			assertTrue(gui.getButtonList().get(i).isEnabled());
		}

		//writer
		try {
			GUI.setRulesFile(new File("C:/Users/Filipe/Documents/rules.cf"));
			writer = new Writer(GUI.getRulesFile(),GUI.getManualRulesWeightList());
			writer.start();
			assertNotNull(writer.getWriter());
			assertNotNull(writer.getFileWriter());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	void testFileChooser() {

			//clicar so nos primeiros 3 botoes(procurar ficheiros)
			//desta forma cria o jfilechooser
		
		for (int i = 0; i < 3; i++) {
			gui.getButtonList().get(i).doClick();
			System.out.println(gui.getButtonList().get(i).getText());
			assertTrue(gui.getButtonList().get(i).isEnabled());
		}
		
		//FALTA ASSERTS
	}
	
	
	// falta teste de butoplistener

}
