package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GUI.GUI;


public class RulesReader extends Thread{

	private String namefile;

	public RulesReader(String namefile) {
		this.namefile=namefile;
	}

	@Override
	public void run() {
		try {

			Scanner scanner = new Scanner(new File(namefile));
			if(GUI.getManualRulesWeightList().getRowCount()>0) {
				//se a lista já tiver valores, apagá-los um a um e adicionar novos
				int numberOfLines =GUI.getManualRulesWeightList().getRowCount();
				for (int i = 0; i < numberOfLines; i++) {
					//como o removeRow provoca um ajustamento de numeração da lista
					//tem de se remover a primeira posição o numero de vezes igual à 
					//dimensão original da lista
					GUI.getManualRulesWeightList().removeRow(0);
					GUI.getAutomaticRulesWeightList().removeRow(0);
				}
			}
			while(scanner.hasNextLine()) {
				String[] splittedLine = scanner.nextLine().split(" ");
				//criar vetor com a regra na primeira coluna e "0.0" na segunda
				String[] readedLine= {splittedLine[0],"0.0"};
				if(splittedLine.length>1) {
					//caso já exista um valor gravado sobrepõe
					try {
						//tenta converter a string lida em double
						@SuppressWarnings("unused")
						Double a = Double.parseDouble((String)splittedLine[1]);
						readedLine[1]=splittedLine[1];
					}catch(NumberFormatException e){
						//não conseguindo não sobre põe o valor pois não é aceite	
					}					
				}
				GUI.getManualRulesWeightList().addRow(readedLine);
				//Sem certeza, teste ONLY
				GUI.getAutomaticRulesWeightList().addRow(new String[]{readedLine[0],"0.0"});
			}			
			GUI.ActivateButons();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
