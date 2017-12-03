package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;


public class Leitor extends Thread{

	private String namefile;

	public Leitor(String namefile) {
		this.namefile=namefile;
	}

	@Override
	public void run() {

		try {
			Scanner sc = new Scanner(new File(namefile));
			if(GUI.getLista_regras_pesos_manual().getRowCount()>0) {
				//se a lista já tiver valores, apagá-los um a um e adicionar novos
				int number_of_lines =GUI.getLista_regras_pesos_manual().getRowCount();
				for (int i = 0; i < number_of_lines; i++) {
					//como o removeRow provoca um ajustamento de numeração da lista
					//tem de se remover a primeira posição o numero de vezes igual à 
					//dimensão original da lista
					GUI.getLista_regras_pesos_manual().removeRow(0);
				}
			}
			while(sc.hasNextLine()) {
				String linha_lida = sc.nextLine();		
				String[] vetor_lido = {linha_lida,"0.0"};
				//Adicionar à lista as regras como primeira coluna e "0.0" na segunda
				GUI.getLista_regras_pesos_manual().addRow(vetor_lido);
				//Sem certeza, teste ONLY
				GUI.getLista_regras_pesos_automatico().addRow(vetor_lido);
			}			
			GUI.ActivateButons();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
