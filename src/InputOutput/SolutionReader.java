package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GUI.GUI;
/**
 * This class reads from an algorithm-created-file and computes the best pair of False Positives and False Negatives according
 * to the specification of an AntiSpamFilter for a ProfessionalMailBox.
 * After doing so, it reads the rule set which is the genesis of that optimized solution and transfers it to the GUI for visualization
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */
public class SolutionReader extends Thread {

	private double falsePositiveOptimization = 999999;
	private double falseNegativeOptimization = 999999;
	private int lineNumber = 0;

	/**
	 * This method extracts the line of a designated file(AntiSpamFilterProblem.NSGAII.rs) which is the optimized set of rules calculated in reference to another algorithm-created-file(AntiSpamFilterProblem.NSGAII.rf)
	 * and transfers the solution to the GUI. 
	 * If this thread was constructed using a separate Runnable run object, then that Runnable object's run method is called; otherwise, this method does nothing and returns. 
	 * Subclasses of Thread should override this method.
	 */
	
	@Override
	public void run() {
		try {
			Scanner scanner = new Scanner(new File("./experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.NSGAII.rf"));
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

			scanner = new Scanner(new File("./experimentBaseDirectory/referenceFronts/AntiSpamFilterProblem.NSGAII.rs"));
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
		}
	}
}
