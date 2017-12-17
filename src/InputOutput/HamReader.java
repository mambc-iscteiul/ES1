package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.GUI;

public class HamReader extends Thread{

	private File hamFile;
	private ArrayList<String> hamArray;


	public HamReader(File file) {
		this.hamFile=file;	
	}


	@Override
	public void run() {
		try {
			Scanner scanner = new Scanner(hamFile);
			int key = 0;
			while (scanner.hasNextLine()) {
				String[] splitedLine = scanner.nextLine().split("	");
				hamArray = new ArrayList<String>();

				for (int i = 1; i < splitedLine.length; i++) {
					hamArray.add(splitedLine[i]);
				}
				GUI.getHamMap().put(key, hamArray);
				key++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}

}
