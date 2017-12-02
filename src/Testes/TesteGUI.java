package Testes;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.JFileChooser;

import org.junit.jupiter.api.Test;

import GUI.FileChooserListener;
import GUI.GUI;
import GUI.Leitor;

class TesteGUI {
	

	private GUI gui = new GUI(100, 100);
	private Leitor leitor = new Leitor("C:/Users/Filipe/Documents/Rules.cf");
	private FileChooserListener file_chooser = new FileChooserListener();
	
	
	@Test
	void testRun() {
		assertNotNull(gui);
		leitor.run();
		assertNotNull(GUI.getLista_regras_pesos_manual());
		assertEquals(335, GUI.getLista_regras_pesos_manual().getRowCount());
		assertNotNull(file_chooser);
	}
	
	@Test
	void testLeitor() {
		assertNotNull(leitor);
	}

	@Test
	void testStart() {
		leitor.start();
		assertTrue(leitor.isAlive());
	}

	@Test
	void testIsInterrupted() {
		leitor.start();
		leitor.interrupt();
		assertTrue(leitor.isInterrupted());
	}

}
