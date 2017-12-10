package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import GUI.GUI;


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
					GUI.getLista_regras_pesos_automatico().removeRow(0);
				}
			}
			while(sc.hasNextLine()) {
				String[] linha_dividida = sc.nextLine().split(" ");
				//criar vetor com a regra na primeira coluna e "0.0" na segunda
				String[] vetor_lido= {linha_dividida[0],"0.0"};
				if(linha_dividida.length>1) {
					//caso já exista um valor gravado sobrepõe
					try {
						//tenta converter a string lida em double
						@SuppressWarnings("unused")
						Double a = Double.parseDouble((String)linha_dividida[1]);
						vetor_lido[1]=linha_dividida[1];
					}catch(NumberFormatException e){
						//não conseguindo não sobre põe o valor pois não é aceite	
				
					}
					
					
				}
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
