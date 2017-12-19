package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.ShutdownChannelGroupException;
import java.util.Random;

import javax.swing.JOptionPane;

import org.uma.jmetal.util.JMetalException;

import Avaliators.FalseNegativeAvaliator;
import Avaliators.FalsePositiveAvaliator;
import InputOutput.Escritor;
import InputOutput.HamReader;
import InputOutput.LeitorExperimental;
import InputOutput.SpamReader;
import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
import antiSpamFilter.AntiSpamFilterProblem;

public class ButtonOpListener implements ActionListener {
	//Listener dedicado aos butoes, generalista,enumerado serve para decidir a funcionalidade
	protected enum ManualOptions{GERAR,AVALIAR,GRAVAR,INICIALIZAR};
	protected enum AutomaticOptions{GERAR_AUTO,GRAVAR_AUTO};

	private ManualOptions op;
	private AutomaticOptions op_aut;


	public ButtonOpListener(ManualOptions op,AutomaticOptions op_aut) {
		this.op=op;
		this.op_aut=op_aut;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(op_aut==null) {
			switch(op) {
			case GERAR: 
				for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
					Random r = new Random();
					double randomValue = ((r.nextDouble()*10.0)-5.0);

					GUI.getLista_regras_pesos_manual().setValueAt(""+randomValue, i, 1);
				}
				break;

			case AVALIAR: 	
				if(!GUI.getHam().getText().equals("")&&!GUI.getSpam().getText().equals("")) {
					for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
						GUI.getMapa().put((String) GUI.getLista_regras_pesos_manual().getValueAt(i, 0), Double.parseDouble((String) GUI.getLista_regras_pesos_manual().getValueAt(i, 1)));
					}
					//					for (String key : GUI.getMapa().keySet()) {
					//						   System.out.println("------------------------------------------------");
					//						   System.out.println("key: " + key + " value: " + GUI.getMapa().get(key));
					//						}

					GUI.setHam(new File(GUI.getHam().getText()));
					FalsePositiveAvaliator avaliatorPos = new FalsePositiveAvaliator(GUI.GetHamFile());
					avaliatorPos.start();

					GUI.setSpam(new File(GUI.getSpam().getText()));
					FalseNegativeAvaliator avaliatorNeg = new FalseNegativeAvaliator(GUI.GetSpamFile());
					avaliatorNeg.start();	
				}else {
					JOptionPane.showMessageDialog(null, "Insira os ficheiros de Spam e de Ham por favor");
				}

				break;

			case GRAVAR	:
				Escritor Writer = null;
				try {
					GUI.setRules(new File(GUI.getRules().getText()));
					Writer = new Escritor(GUI.GetRulesFile(),GUI.getLista_regras_pesos_manual());
					Writer.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;

			case INICIALIZAR:
				for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
					GUI.getLista_regras_pesos_manual().setValueAt("0.0", i, 1);
				}
				break;

			default:

				break;
			}
		}else {
			switch(op_aut) {
			case GERAR_AUTO: 
				if(!GUI.getHam().getText().equals("")&&!GUI.getSpam().getText().equals("")) {

					//Criar Mapa de Rules a 0.0 (Aproveitar as lista manual)
					for (int i = 0; i < GUI.getLista_regras_pesos_automatico().getRowCount(); i++) {
						GUI.getMapa().put((String) GUI.getLista_regras_pesos_automatico().getValueAt(i, 0), 0.0);

					}

					//criar a estrutura de dados de HAM

					GUI.setHam(new File(GUI.getHam().getText()));
					HamReader hamReader = new HamReader (GUI.GetHamFile());
					hamReader.start();

					//	criar a estrutura de dados de SPAM

					GUI.setSpam(new File(GUI.getSpam().getText()));
					SpamReader spamReader = new SpamReader(GUI.GetSpamFile()); 
					spamReader.start();

					boolean done=false;
					int maxEvaluations=0;
					do {		
						try {
							String str=JOptionPane.showInputDialog("Quanto maior o numero de avaliacoes mais lento sera o algoritmo!\n Qual o maximo de avaliacoes que deseja? (maximo recomendado 450)");
							maxEvaluations = Integer.parseInt(str);
							done=true;
						}catch(NumberFormatException e) {
							JOptionPane.showMessageDialog(null,"Por favor, insira valores numericos validos");
						}
					} while (!done);

					AntiSpamFilterProblem antiSpamProblem = new AntiSpamFilterProblem(GUI.getMapa(),GUI.getMapa_Spam(),GUI.getHamMap());

					try {
						@SuppressWarnings("unused")
						AntiSpamFilterAutomaticConfiguration l = new AntiSpamFilterAutomaticConfiguration(antiSpamProblem,maxEvaluations);
					}catch(JMetalException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "O algoritmo encontrou a otimizacao maxima!");
					}
					LeitorExperimental le = new LeitorExperimental();
					le.start();

				}else {
					JOptionPane.showMessageDialog(null, "Insira os ficheiros de Spam e de Ham por favor");
				}
				break;

			case GRAVAR_AUTO:
				try {
					GUI.setRules(new File(GUI.getRules().getText()));
					Escritor writer = new Escritor(GUI.GetRulesFile(), GUI.getLista_regras_pesos_automatico());
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