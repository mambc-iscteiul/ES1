package Avaliators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import GUI.GUI;

public class FalseNegativeAvaliator extends Thread {

	private int falseNegativesCount;
	private File spamFile;

	private Map<String, Double> rulesMap;


	public FalseNegativeAvaliator(File spamFile) {
		falseNegativesCount = 0;
		this.spamFile = spamFile;
		rulesMap = GUI.getRulesMap();
	}

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
			System.out.println("-------------->"+falseNegativesCount);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}

	private double calculate (String [] line) {
		double counter =0;
		for (int i = 1; i < line.length; i++) {
			if(rulesMap.get(line[i])!=null)
				counter += rulesMap.get(line[i]);				
		}
		return counter;
	}


	public int getFalseNegativesCount() {
		return falseNegativesCount;
	}

	public Map<String, Double> getRuleMap() {
		return rulesMap;
	}

}


