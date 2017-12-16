package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.GUI;

public class HamReader extends Thread{
	
	private String hamFileName;
	private ArrayList<String> hamArray;
	

	public HamReader(String hameFileName) {
		this.hamFileName=hameFileName;
		
	}
	
	
	@Override
	public void run() {
		
		try {
			Scanner scanner = new Scanner(new File(hamFileName));
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
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
