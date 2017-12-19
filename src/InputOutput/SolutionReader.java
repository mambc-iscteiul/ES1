package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GUI.GUI;

public class SolutionReader extends Thread {

	private double falsePositiveOptimization = 999999;
	private double falseNegativeOptimization = 999999;
	private int lineNumber = 0;


	@Override
	public void run() {
		try {
			Scanner scanner = new Scanner(new File("./experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.rf"));
			int countLines = 1;
			while(scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split(" ");
				if(Double.parseDouble(line[0])<falsePositiveOptimization) {
					falsePositiveOptimization = Double.parseDouble(line[0]);
					falseNegativeOptimization = Double.parseDouble(line[1]);
					lineNumber = countLines;
				}else if((Double.parseDouble(line[0]) == falsePositiveOptimization)&&Double.parseDouble(line[1])<falseNegativeOptimization) {
					falseNegativeOptimization = Double.parseDouble(line[1]);
					lineNumber = countLines;
				}
				countLines++;
			}

			System.out.println(lineNumber+"ª linha");
			System.out.println("Par otimizado: "+falsePositiveOptimization+", "+falseNegativeOptimization+".");

			scanner = new Scanner(new File("./experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.rs"));
			int counter = 1;
			while(counter != lineNumber) {
				scanner.nextLine();
				counter++;
			}
			String[] optimizedRules= scanner.nextLine().split(" ");
			GUI.getTextFieldAutomaticFalsePositive().setText(""+(int)falsePositiveOptimization);
			GUI.getTextFieldAutomaticFalseNegative().setText(""+(int)falseNegativeOptimization);

			for (int i = 0; i < optimizedRules.length; i++) {
				GUI.getAutomaticRulesWeightList().setValueAt(optimizedRules[i],i, 1);
			}


			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}



	}
}
