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
import InputOutput.Writer;
import InputOutput.HamReader;
import InputOutput.SolutionReader;
import InputOutput.SpamReader;
import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;

public class ButtonOptionListener implements ActionListener {
	//Listener dedicado aos butões, generalista,enumerado serve para decidir a funcionalidade
	protected enum ManualOptions{GENERATEMANUAL, EVALUATE, SAVEMANUAL, INITIALIZE};
	protected enum AutomaticOptions{GENERATEAUTOMATIC, SAVEAUTOMATIC};

	private ManualOptions manualOption;
	private AutomaticOptions automaticOption;


	public ButtonOptionListener(ManualOptions manualOption,AutomaticOptions automaticOption) {
		this.manualOption = manualOption;
		this.automaticOption = automaticOption;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(automaticOption == null) {
			switch(manualOption) {
			case GENERATEMANUAL: 
				for (int i = 0; i < GUI.getManualRulesWeightList().getRowCount(); i++) {
					Random r = new Random();
					double randomValue = ((r.nextDouble()*10.0)-5.0);

					GUI.getManualRulesWeightList().setValueAt(""+randomValue, i, 1);
				}
				break;

			case EVALUATE:
				if(!GUI.getTextFieldHam().getText().equals("")&&!GUI.getTextFieldSpam().getText().equals("")) {
					for (int i = 0; i < GUI.getManualRulesWeightList().getRowCount(); i++) {
						GUI.getRulesMap().put((String) GUI.getManualRulesWeightList().getValueAt(i, 0), Double.parseDouble((String) GUI.getManualRulesWeightList().getValueAt(i, 1)));
					}
					//					for (String key : GUI.getMapa().keySet()) {
					//						   System.out.println("------------------------------------------------");
					//						   System.out.println("key: " + key + " value: " + GUI.getMapa().get(key));
					//						}

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
				Writer writer = null;
				try {
					GUI.setRulesFile(new File(GUI.getTextFieldRules().getText()));
					writer = new Writer(GUI.getRulesFile(),GUI.getManualRulesWeightList());
					writer.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;

			case INITIALIZE:
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
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "O algoritmo encontrou a otimização máxima!");
					}
					SolutionReader solutionReader = new SolutionReader();
					solutionReader.start();

				}else {
					JOptionPane.showMessageDialog(null, "Insira os ficheiros de Spam e de Ham por favor");
				}
				break;

			case SAVEAUTOMATIC:
				try {
					GUI.setRulesFile(new File(GUI.getTextFieldRules().getText()));
					Writer writer = new Writer(GUI.getRulesFile(), GUI.getAutomaticRulesWeightList());
					writer.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	}
}