package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

import Avaliators.FalseNegativeAvaliator;
import Avaliators.FalsePositiveAvaliator;
import InputOutput.Escritor;

public class ButtonOpListener implements ActionListener {
	//Listener dedicado aos butões, generalista,enumerado serve para decidir a funcionalidade
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
					Writer = new Escritor(GUI.getRules().getText());
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
				System.out.println("GERAR AUTOMÁTICO");
				if(!GUI.getHam().getText().equals("")&&!GUI.getSpam().getText().equals("")) {


					//iniciar antispamautom
				}else {
					JOptionPane.showMessageDialog(null, "Insira os ficheiros de Spam e de Ham por favor");
				}
				break;

			case GRAVAR_AUTO:

				System.out.println("GRAVAR AUTOMÁTICO");
				//  E SE A LISTA AINDA NÃO ESTIVER LÁ NAO FAZ NADA TB
				if(!GUI.getHam().getText().equals("")&&!GUI.getSpam().getText().equals("")) {


					//gravar  a lista no ficheirorules

				}else {
					JOptionPane.showMessageDialog(null, "Insira os ficheiros de Spam e de Ham por favor");
				}
				break;

			default:
				break;


			}
		}

	}
}