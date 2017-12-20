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
import InputOutput.RulesReader;
import InputOutput.Writer;
import Launch.Launch;
/**
 * 
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */
class TesteGUI {

	//	private GUI gui = new GUI(100, 100);
	private RulesReader rulesReader;
	private Writer writer;
	JButton tester;

	@Test
	void testGUI() {
		Launch.main(null);
		//só funciona assim
		rulesReader = new RulesReader("C:/Users/Filipe/Documents/rules.cf");
		assertNotNull(rulesReader);
		rulesReader.run();
		assertTrue(rulesReader.isAlive());
		assertNotNull(GUI.getManualRulesWeightList());
		assertEquals(335, GUI.getManualRulesWeightList().getRowCount());
		assertFalse(GUI.getManualRulesWeightList().isCellEditable(0, 0));

	}

	@Test
	void testButtons(){
		
		GUI.ActivateButons();	
		for (int i = 0; i < GUI.getButtonList().size(); i++) {
			assertNotNull(GUI.getButtonList().get(i));
			GUI.getButtonList().get(i).doClick();
			assertTrue(GUI.getButtonList().get(i).isEnabled());
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
			e.printStackTrace();
		}
	}

	@Test
	void testFileChooser() {

		//clicar so nos primeiros 3 botoes(procurar ficheiros)
		//desta forma cria o jfilechooser

				for (int i = 0; i < 3; i++) {
					GUI.getButtonList().get(i).doClick();
					System.out.println(GUI.getButtonList().get(i).getText());
					assertTrue(GUI.getButtonList().get(i).isEnabled());
				}
				assertFalse(GUI.getTextFieldRules().getText().equals(" "));
				assertFalse(GUI.getTextFieldHam().getText().equals(" "));
				assertFalse(GUI.getTextFieldSpam().getText().equals(" "));
	}
	//FALTA ASSERTS
}


// falta teste de butoplistener


