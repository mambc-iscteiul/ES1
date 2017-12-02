package GUI;

import java.io.File;
import java.io.FileNotFoundException;
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
			while(sc.hasNextLine()) {
				String linha_lida = sc.nextLine();		
				String[] vetor_lido = {linha_lida,"0.0"};
				//Adicionar à lista as regras como primeira coluna e "0.0" na segunda
				GUI.getLista_regras_pesos_manual().addRow(vetor_lido);
			}			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
