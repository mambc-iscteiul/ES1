package Avaliators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import GUI.GUI;
/**
 * Creates an evaluator which will open the designated Spam File.
 * It calculates how the current set of weights assert
 * the Spam e-mails correctly as Spam.
 * 
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */
public class FalseNegativeAvaliator extends Thread {

	private int falseNegativesCount;
	private File spamFile;

	private Map<String, Double> rulesMap;


	/**
	 *Creates the evaluator Thread and asserts which file it must work on 
	 * @param spamFile hamFile Designated file of Spam e-mails
	 */
	public FalseNegativeAvaliator(File spamFile) {
		falseNegativesCount = 0;
		this.spamFile = spamFile;
		rulesMap = GUI.getRulesMap();
	}
	
	/**
	 * It creates a scanner to read the designated file and calculate the false negatives
	 * If this thread was constructed using a separate Runnable run object, then that Runnable object's run method is called; otherwise, this method does nothing and returns. 
	 *	Subclasses of Thread should override this method.
	 */
	@Override
	public void run() {
		try {
			Scanner scanner = new Scanner(spamFile);
			while(scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split("	");		
				double veredict = calculate(line);
				if(veredict<-5.0)falseNegativesCount++;
			}

			GUI.getTextFieldManualFalseNegative().setText(""+falseNegativesCount);	
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}

	/**
	 * This method calculates the impact of the current set of weights
	 * on the rules present in the Spam e-mails.
	 * @param line line to evaluate
	 * @return the calculation of that line
	 */
	private double calculate (String [] line) {
		double math =0;
		for (int i = 1; i < line.length; i++) {
			if(rulesMap.get(line[i])!=null)
				math += rulesMap.get(line[i]);				
		}
		return math;
	}


	/**
	 * This methods returns the overall count of False Negatives
	 * @return the count of False Negatives
	 */
	public int getFalseNegativesCount() {
		return falseNegativesCount;
	}

	/**
	 * This method returns the rule-weight relation which has been used
	 * to calculate the False Negatives
	 * 
	 * @return the current rule-weight mapping 
	 */
	public Map<String, Double> getRuleMap() {
		return rulesMap;
	}

}


