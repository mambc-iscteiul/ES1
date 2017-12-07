package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import InputOutput.Escritor;

public class ButtonOpListener implements ActionListener {
	//Listener dedicado aos butões, generalista,enumerado serve para decidir a funcionalidade
	protected enum Option{GERAR,AVALIAR,GRAVAR,INICIALIZAR};
	private Option op;
	private Map<String,Double> mapa;

	public ButtonOpListener(Option op) {
		this.op=op;
		mapa = new HashMap<String, Double>();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {

		switch(op) {
		case GERAR: 
			for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
				Random r = new Random();
				double randomValue = ((r.nextDouble()*10.0)-5.0);

				GUI.getLista_regras_pesos_manual().setValueAt(""+randomValue, i, 1);
			}
			break;

		case AVALIAR: 
			for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
				mapa.put((String) GUI.getLista_regras_pesos_manual().getValueAt(i, 0), Double.parseDouble((String) GUI.getLista_regras_pesos_manual().getValueAt(i, 1)));
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
		}
	}
}
