package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GUI.DefaultTableModelEdited;
import GUI.GUI;
import GUI.DefaultTableModelEdited.ListType;
/**
 * This class maps all the Rules in a relation rules-weight and represents it visually.
 * 
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */

public class RulesReader extends Thread{

	private String namefile;

	/**
	 * Creates a RulesReader to work on the designated File
	 * @param namefile file to work on
	 */
	public RulesReader(String namefile) {
		this.namefile=namefile;
	}
	
	/**
	 * It associates the rules in the file with their weights.
	 *  Case the File only contains the Rules, it will set the value to '0.0' 
	 *  and will not accept other written values, as it also replace them with '0.0'.
	 * When reading the rules, if this Thread has been previously called it will remove the visualization of the previous rules and start a new one.
	 * 
	 * If this thread was constructed using a separate Runnable run object, then that Runnable object's run method is called; otherwise, this method does nothing and returns. 
	 * Subclasses of Thread should override this method.
	 */
	@Override
	public void run() {
		try {
			
			Scanner scanner = new Scanner(new File(namefile));
			if(GUI.getManualRulesWeightList().getRowCount()>0) {
				try {
					//se a lista já tiver valores, apagá-los um a um e adicionar novos
					int numberOfLines =GUI.getManualRulesWeightList().getRowCount();
					for (int i = 0; i < numberOfLines; i++) {
						//como o removeRow provoca um ajustamento de numeração da lista
						//tem de se remover a primeira posição o numero de vezes igual à 
						//dimensão original da list
					GUI.getManualRulesWeightList().removeRow(0);
					GUI.getTextFieldManualFalseNegative().setText("0");
					GUI.getTextFieldManualFalsePositive().setText("0");
					GUI.getAutomaticRulesWeightList().removeRow(0);
					GUI.getTextFieldAutomaticFalseNegative().setText("0");
					GUI.getTextFieldAutomaticFalseNegative().setText("0");
					}
					
				}catch(IndexOutOfBoundsException e) {
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
		}
	}

}
