package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ButtonOpListener implements ActionListener {
	//Listener dedicado aos butões, generalista,enumerado serve para decidir a funcionalidade
	protected enum Option{GERAR,AVALIAR,GRAVAR,INICIALIZAR};

	private Option op;

	public ButtonOpListener(Option op) {
		this.op=op;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {

		switch(op) {
		case GERAR: 
			for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
				Random r = new Random();
				double randomValue = 0.0 + (5.0) * r.nextDouble();
				GUI.getLista_regras_pesos_manual().setValueAt((float)randomValue, i, 1);
			}
			break;

		case AVALIAR: System.out.println("Avaliar");
		break;

		case GRAVAR	:System.out.println("Gravar");
		for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
			System.out.print(GUI.getLista_regras_pesos_manual().getValueAt(i, 0));
			System.out.println(" "+ GUI.getLista_regras_pesos_manual().getValueAt(i, 1));
			

		}
		break;

		case INICIALIZAR:
			for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
				GUI.getLista_regras_pesos_manual().setValueAt(0.0, i, 1);
			}
			break;

		}

	}

}
