package Avaliators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import GUI.GUI;

public class FalsePositiveAvaliator extends Thread {

	private int falsePositivesCount;
	private File hamFile;
	private Map<String, Double> rulesMap;

	public FalsePositiveAvaliator(File hamFile) {
		falsePositivesCount = 0;
		this.hamFile = hamFile;
		rulesMap = GUI.getRulesMap();
	}

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
			System.out.println("-------------->"+falsePositivesCount);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}


	private double calculate (String [] line) {
		double counter = 0;
		for (int i = 1; i < line.length; i++) {
			if(rulesMap.get(line[i])!=null)
				counter += rulesMap.get(line[i]);				
		}
		return counter;
	}


	public int getFalsePositivesCount() {
		return falsePositivesCount;
	}

	public Map<String, Double> getRulesMap() {
		return rulesMap;
	}

}


