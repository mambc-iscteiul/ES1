package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GUI.GUI;

public class LeitorExperimental extends Thread {
	
	private double otimiza��oFP=999999;
	private double otimiza��oFN=999999;
	private int lineNumber=0;


	@Override
	public void run() {
		try {
			Scanner scan = new Scanner(new File("./experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.rf"));
			int countLines=1;
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split(" ");
				if(Double.parseDouble(line[0])<otimiza��oFP) {
					otimiza��oFP=Double.parseDouble(line[0]);
					otimiza��oFN=Double.parseDouble(line[1]);
					lineNumber=countLines;
				}else if((Double.parseDouble(line[0])==otimiza��oFP)&&Double.parseDouble(line[1])<otimiza��oFN) {
					otimiza��oFN=Double.parseDouble(line[1]);
					lineNumber=countLines;
				}
				countLines++;
			}
			
			System.out.println(lineNumber+"� linha");
			System.out.println("Par otimizado: "+otimiza��oFP+", "+otimiza��oFN+".");

			scan = new Scanner(new File("./experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.rs"));
			int counter=1;
			while(counter !=lineNumber) {
				scan.nextLine();
				counter++;
			}
				String[] optimizedRules= scan.nextLine().split(" ");
				GUI.getFp_automatico().setText(""+(int)otimiza��oFP);
				GUI.getFn_automatico().setText(""+(int)otimiza��oFN);
				
				for (int i = 0; i < optimizedRules.length; i++) {
					GUI.getLista_regras_pesos_automatico().setValueAt(optimizedRules[i],i, 1);
				}
	
		
		scan.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}



}
}
