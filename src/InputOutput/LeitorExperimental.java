package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GUI.GUI;

public class LeitorExperimental extends Thread {
	
	private double otimizaçãoFP=999999;
	private double otimizaçãoFN=999999;
	private int lineNumber=0;


	@Override
	public void run() {
		try {
			Scanner scan = new Scanner(new File("./experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.rf"));
			int countLines=1;
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split(" ");
				if(Double.parseDouble(line[0])<otimizaçãoFP) {
					otimizaçãoFP=Double.parseDouble(line[0]);
					otimizaçãoFN=Double.parseDouble(line[1]);
					lineNumber=countLines;
				}else if((Double.parseDouble(line[0])==otimizaçãoFP)&&Double.parseDouble(line[1])<otimizaçãoFN) {
					otimizaçãoFN=Double.parseDouble(line[1]);
					lineNumber=countLines;
				}
				countLines++;
			}
			
			System.out.println(lineNumber+"ª linha");
			System.out.println("Par otimizado: "+otimizaçãoFP+", "+otimizaçãoFN+".");

			scan = new Scanner(new File("./experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.rs"));
			int counter=1;
			while(counter !=lineNumber) {
				scan.nextLine();
				counter++;
			}
				String[] optimizedRules= scan.nextLine().split(" ");
				GUI.getFp_automatico().setText(""+(int)otimizaçãoFP);
				GUI.getFn_automatico().setText(""+(int)otimizaçãoFN);
				
				for (int i = 0; i < optimizedRules.length; i++) {
					GUI.getLista_regras_pesos_automatico().setValueAt(optimizedRules[i],i, 1);
				}
	
		
		scan.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}



}
}
