package Avaliators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import GUI.GUI;

public class FalseNegativeAvaliator extends Thread {

	private int FalseNegatives;
	private File SpamFile;

	private Map<String, Double> mapa;





	public FalseNegativeAvaliator(File SpamFile) {
		FalseNegatives=0;
		this.SpamFile = SpamFile;
		mapa = GUI.getMapa();
	}

	@Override
	public void run() {
		try {
			Scanner scan = new Scanner(SpamFile);
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split("	");		
				double veredict = calculate(line);
				if(veredict<-5.0) {System.out.println("CRLH");FalseNegatives++;} 	
			}

			GUI.getFn_manual().setText(""+FalseNegatives);	
			System.out.println("-------------->"+FalseNegatives);
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}

	private double calculate (String [] line) {
		double counter =0;
		for (int i = 1; i < line.length; i++) {
			if(mapa.get(line[i])!=null)
				counter += mapa.get(line[i]);				
		}
		return counter;
	}


	public int getFalseNegatives() {
		return FalseNegatives;
	}

	public Map<String, Double> getMapa() {
		return mapa;
	}

}


