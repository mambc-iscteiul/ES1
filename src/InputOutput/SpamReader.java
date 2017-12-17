package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.GUI;

public class SpamReader extends Thread {

	private File spam_file;
	private int keyValue;

	public SpamReader(File SpamFile) {
		spam_file= SpamFile;

	}
	@Override
	public void run() {
		try {
			Scanner scan = new Scanner(spam_file);
			keyValue=0;
			while(scan.hasNextLine()) {
				String [] linha_lida = scan.nextLine().split("	");
				ArrayList<String> intermidiate = new ArrayList<>(); 
				for (int i = 1; i < linha_lida.length; i++) {
					intermidiate.add(linha_lida[i]);
				}
				GUI.getMapa_Spam().put(keyValue, intermidiate);
				keyValue++;
			}	
			scan.close();
		} catch (FileNotFoundException e) {
		}
	}
}