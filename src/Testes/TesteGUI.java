package Testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;

import GUI.GUI;
import InputOutput.Escritor;
import InputOutput.Leitor;
import Launch.Launch;

class TesteGUI {

	
	Launch l = new Launch();
	private GUI gui = new GUI(100, 100);
	private Leitor leitor = new Leitor("C:/Users/Filipe/Documents/rules.cf");
	private Escritor escritor;
	JButton tester;


	
	@Test
	void testGUI() {
		assertNotNull(l);
		//gui e lista
		assertNotNull(gui);
		leitor.run();
		assertNotNull(GUI.getLista_regras_pesos_manual());
		assertEquals(335, GUI.getLista_regras_pesos_manual().getRowCount());
		assertFalse(GUI.getLista_regras_pesos_manual().isCellEditable(0, 0));

		//botoes
		GUI.ActivateButons();	
		for (int i = 0; i < gui.getLista_de_botoes().size(); i++) {
			gui.getLista_de_botoes().get(i).doClick();
			assertTrue(gui.getLista_de_botoes().get(i).isEnabled());
		}

		//writer
		try {
			escritor= new Escritor("C:/Users/Filipe/Documents/rules.cf");
			escritor.start();
			assertNotNull(escritor.getWriter());
			assertNotNull(escritor.getFileWriter());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	void testFileChooser() {

			//clicar so nos primeiros 3 botões(procurar ficheiros)
			//desta forma cria o jfilechooser
		
		for (int i = 0; i < 3; i++) {
			gui.getLista_de_botoes().get(i).doClick();
			System.out.println(gui.getLista_de_botoes().get(i).getText());
			assertTrue(gui.getLista_de_botoes().get(i).isEnabled());
		}
		
		//FALTA ASSERTS
	}
	
	
	// falta teste de butoplistener

}
