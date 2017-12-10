package Avaliators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import GUI.GUI;

public class FalsePositiveAvaliator extends Thread {

	private int FalsePositives;
	private File HamFile;

	private Map<String, Double> mapa;



	public FalsePositiveAvaliator(File HamFile) {
		FalsePositives=0;
		this.HamFile = HamFile;
		mapa = GUI.getMapa();
	}

	@Override
	public void run() {
		try {
			Scanner scan = new Scanner(HamFile);
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split("	");		
				double veredict = calculate(line);
				if(veredict>5.0)FalsePositives++; 	
			}
			GUI.getFp_manual().setText(""+FalsePositives);			
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
	
}


