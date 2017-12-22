package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

import org.uma.jmetal.util.JMetalException;

import Avaliators.FalseNegativeAvaliator;
import Avaliators.FalsePositiveAvaliator;
import InputOutput.HamReader;
import InputOutput.SolutionReader;
import InputOutput.SpamReader;
import InputOutput.Writer;
import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;
/**
 * This class is an ActionListener for the buttons in the GUI of the AntiSpamFilterConfiguration
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */

public class ButtonOptionListener implements ActionListener {
	//Listener dedicado aos butões, generalista,enumerado serve para decidir a funcionalidade
	/**
	 * This Enumerate represents the default operations in the manual configuration
	 * @author ES1-2017-IC1-70
	 * @version 0.9
	 */
	protected enum ManualOptions{GENERATEMANUAL, EVALUATE, SAVEMANUAL, INITIALIZE};
	/**
	 * This Enumerate represents the default operations in the automatic configuration
	 * @author ES1-2017-IC1-70
	 * @version 0.9
	 */
	protected enum AutomaticOptions{GENERATEAUTOMATIC, SAVEAUTOMATIC};

	private ManualOptions manualOption;
	private AutomaticOptions automaticOption;


	/**
	 * Constructs an ActionListener in respect with the type of operations
	 * @param manualOption type of manual operation, can be null
	 * @param automaticOption type of automatic operation, can be null
	 */
	public ButtonOptionListener(ManualOptions manualOption,AutomaticOptions automaticOption) {
		this.manualOption = manualOption;
		this.automaticOption = automaticOption;
	}

	/**
	 * Invoked when an action occurs. Depending on the option type,
	 * the actions will vary
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(automaticOption == null) {
			/*
			 * Gera valores aleatório 
			 */
			switch(manualOption) {
			case GENERATEMANUAL: 
				for (int i = 0; i < GUI.getManualRulesWeightList().getRowCount(); i++) {
					Random r = new Random();
					double randomValue = ((r.nextDouble()*10.0)-5.0);

					GUI.getManualRulesWeightList().setValueAt(""+randomValue, i, 1);
				}
				break;

				/*
				 * Lança os avaliadores 
				 */
			case EVALUATE:
				if(!GUI.getTextFieldHam().getText().equals("")&&!GUI.getTextFieldSpam().getText().equals("")) {
					for (int i = 0; i < GUI.getManualRulesWeightList().getRowCount(); i++) {
						GUI.getRulesMap().put((String) GUI.getManualRulesWeightList().getValueAt(i, 0), Double.parseDouble((String) GUI.getManualRulesWeightList().getValueAt(i, 1)));
					}
					GUI.setHamFile(new File(GUI.getTextFieldHam().getText()));
					FalsePositiveAvaliator falsePositiveAvaliator = new FalsePositiveAvaliator(GUI.getHamFile());
					falsePositiveAvaliator.start();

					GUI.setSpamFile(new File(GUI.getTextFieldSpam().getText()));
					FalseNegativeAvaliator falseNegativeAvaliator = new FalseNegativeAvaliator(GUI.getSpamFile());
					falseNegativeAvaliator.start();	
				}else {
					JOptionPane.showMessageDialog(null, "Insira os ficheiros de Spam e de Ham por favor");
				}

				break;

			case SAVEMANUAL	:
				/*
				 * Guarda os pesos no ficheiro
				 */
				Writer writer = null;
				try {
					GUI.setRulesFile(new File(GUI.getTextFieldRules().getText()));
					writer = new Writer(GUI.getRulesFile(),GUI.getManualRulesWeightList());
					writer.start();

					writer = new Writer(new File("./AntiSpamConfigurationForProfessionalMailbox/rules.cf"),GUI.getManualRulesWeightList());
					writer.start();


				} catch (IOException e1) {
				}
				break;

			case INITIALIZE:
				/*
				 * Atribui "0.0"a todas as regras 
				 */
				for (int i = 0; i < GUI.getManualRulesWeightList().getRowCount(); i++) {
					GUI.getManualRulesWeightList().setValueAt("0.0", i, 1);
				}
				break;

			default:

				break;
			}
		}else {
			switch(automaticOption) {
			case GENERATEAUTOMATIC: 
				/*
				 *Lança o algoritmo automático e lê do ficheiro de otimização que este cria
				 * para replicar os pesos da melhor opção, na tabela do GUI 
				 */
				if(!GUI.getTextFieldHam().getText().equals("")&&!GUI.getTextFieldSpam().getText().equals("")) {

					//Criar Mapa de Rules a 0.0 (Aproveitar as lista manual)
					for (int i = 0; i < GUI.getAutomaticRulesWeightList().getRowCount(); i++) {
						GUI.getRulesMap().put((String) GUI.getAutomaticRulesWeightList().getValueAt(i, 0), 0.0);

					}

					//criar a estrutura de dados de HAM

					GUI.setHamFile(new File(GUI.getTextFieldHam().getText()));
					HamReader hamReader = new HamReader (GUI.getHamFile());
					hamReader.start();

					//	criar a estrutura de dados de SPAM

					GUI.setSpamFile(new File(GUI.getTextFieldSpam().getText()));
					SpamReader spamReader = new SpamReader(GUI.getSpamFile()); 
					spamReader.start();

					boolean done = false;
					int maxEvaluations = 0;
					do {		

						try {
							String notice = JOptionPane.showInputDialog("Quanto maior o numero de avaliações mais lento será o algoritmo!\n Qual o máximo de avaliações que deseja? (máximo recomendado 450)");

							maxEvaluations = Integer.parseInt(notice);
							done = true;
						}catch(NumberFormatException e) {
							JOptionPane.showMessageDialog(null,"Por favor, insira valores numéricos válidos");
						}
					} while (!done);

					AntiSpamFilterProblem antiSpamFilterProblem = new AntiSpamFilterProblem(GUI.getRulesMap(),GUI.getSpamMap(),GUI.getHamMap());

					try {
						@SuppressWarnings("unused")
						AntiSpamFilterAutomaticConfiguration antiSpamFilterAutomaticConfiguration = new AntiSpamFilterAutomaticConfiguration(antiSpamFilterProblem,maxEvaluations);
					}catch(JMetalException e) {
						JOptionPane.showMessageDialog(null, "O algoritmo encontrou a otimização máxima!");
					}
					createEPS();
					SolutionReader solutionReader = new SolutionReader();
					solutionReader.start();

				}else {
					JOptionPane.showMessageDialog(null, "Insira os ficheiros de Spam e de Ham por favor");
				}
				break;

			case SAVEAUTOMATIC:
				try {
					/*
					 * Guarda os pesos no ficheiro
					 */
					GUI.setRulesFile(new File(GUI.getTextFieldRules().getText()));
					Writer writer = new Writer(GUI.getRulesFile(), GUI.getAutomaticRulesWeightList());
					writer.start();

					writer = new Writer(new File("./AntiSpamConfigurationForProfessionalMailbox/rules.cf"),GUI.getManualRulesWeightList());
					writer.start();
				} catch (IOException e) {
				}
				break;
			default:
				break;
			}
		}
	}

	private void createEPS() {

		String[] params = new String [2];

		params[0] = "C:/Program Files/R/R-3.4.3/bin/x64/Rscript.exe";

		params[1] = "C:/Users/Filipe/git/ES1-2017-IC1-70/experimentBaseDirectory/AntiSpamStudy/R/HV.Boxplot.R";

		String[] envp = new String [1];

		envp[0] = "Path=C:/Program Files/R/R-3.4.3/bin/x64";

		try {
			@SuppressWarnings("unused")
			Process p = Runtime.getRuntime().exec(params, envp, new File("C:/Users/Filipe/git/ES1-2017-IC1-70/experimentBaseDirectory/AntiSpamStudy/R"));
		} catch (IOException e) {
		}

	}
}