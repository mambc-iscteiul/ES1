package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.GUI;

public class SpamReader extends Thread {

	private File spamFile;
	private int keyValue;

	public SpamReader(File spamfile) {
		spamFile= spamfile;

	}
	@Override
	public void run() {
		try {
			Scanner scanner = new Scanner(spamFile);
			keyValue = 0;
			while(scanner.hasNextLine()) {
				String [] readedLine = scanner.nextLine().split("	");
				ArrayList<String> intermidiate = new ArrayList<>(); 
				for (int i = 1; i < readedLine.length; i++) {
					intermidiate.add(readedLine[i]);
				}
				GUI.getSpamMap().put(keyValue, intermidiate);
				keyValue++;
			}	
			scanner.close();
		} catch (FileNotFoundException e) {
		}
	}
}