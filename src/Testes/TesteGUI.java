package Testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import GUI.FileChooserListener;
import GUI.FileChooserListener.FileType;
import GUI.GUI;
import InputOutput.Escritor;
import InputOutput.Leitor;

class TesteGUI {


	private GUI gui = new GUI(100, 100);
	private Leitor leitor = new Leitor("C:/Users/Filipe/Documents/rules.cf");
	private Escritor escritor;
	private FileChooserListener fc;
	JButton tester;



	@Test
	void testGUI() {
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
		JTextField j = new JTextField();
		File f_rules = new File("C:/Users/Filipe/Documents/rules.cf");
		File f_spam = new File("C:/Users/Filipe/Documents/ham.cf");
		File f_ham = new File("C:/Users/Filipe/Documents/spam.cf");
		FileType ft_rules = FileType.RULES;
		FileType ft_ham = FileType.HAM;
		FileType ft_spam = FileType.SPAM;

		fc = new FileChooserListener(j, f_rules, ft_rules);
		System.out.println(fc.getClass().getPackage());
		assertTrue(fc.getClass().getPackage().getName().equals("GUI"));

		tester = new JButton();
		tester.addActionListener(fc);
		tester.doClick();

		fc = new FileChooserListener(j, f_ham, ft_ham);

		tester = new JButton();
		tester.addActionListener(fc);
		tester.doClick();

		fc = new FileChooserListener(j, f_spam, ft_spam);

		tester = new JButton();
		tester.addActionListener(fc);
		tester.doClick();

		//FALTA ASSERTS
	}

}
