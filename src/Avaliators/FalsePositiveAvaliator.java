package Avaliators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import GUI.GUI;
/**
 * Creates an evaluator which will open the designated Ham File.
 * It calculates how the current set of weights assert
 * the Ham e-mails correctly as Ham.
 * 
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */

public class FalsePositiveAvaliator extends Thread {

	private int falsePositivesCount;
	private File hamFile;
	private Map<String, Double> rulesMap;

	/**
	 *Creates the evaluator Thread and asserts which file it must work on 
	 * @param hamFile Designated file of Ham e-mails
	 */
	public FalsePositiveAvaliator(File hamFile) {
		falsePositivesCount = 0;
		this.hamFile = hamFile;
		rulesMap = GUI.getRulesMap();
	}

	/**
	 * It creates a scanner to read the designated file and calculate the false positives
	 * If this thread was constructed using a separate Runnable run object, then that Runnable object's run method is called; otherwise, this method does nothing and returns. 
	 *	Subclasses of Thread should override this method.
	 */
	@Override
	public void run() {
		try {
			Scanner scanner = new Scanner(hamFile);
			while(scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split("	");		
				double veredict = calculate(line);
				if(veredict>5.0)falsePositivesCount++; 	
			}
			GUI.getTextFieldManualFalsePositive().setText(""+falsePositivesCount);			
			scanner.close();
		} catch (FileNotFoundException e) {
		}
	}


	/**
	 * This method calculates the impact of the current set of weights
	 * on the rules present in the Ham e-mails.
	 * @param line line to evaluate
	 * @return the calculation of that line
	 */
	private double calculate (String [] line) {
		double math = 0;
		for (int i = 1; i < line.length; i++) {
			if(rulesMap.get(line[i])!=null)
				math += rulesMap.get(line[i]);				
		}
		return math;
	}
	/**
	 * This methods returns the overall count of False Positives
	 * @return the count of False Positives
	 */
	public int getFalsePositivesCount() {
		return falsePositivesCount;
	}
	/**
	 * This method returns the rule-weight relation which has been used
	 * to calculate the False Positives
	 * 
	 * @return the current rule-weight mapping 
	 */
	public Map<String, Double> getRulesMap() {
		return rulesMap;
	}

}


