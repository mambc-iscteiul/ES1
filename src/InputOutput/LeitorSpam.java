package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.GUI;

public class LeitorSpam extends Thread {

	private File spam_file;
	private int iterator;

	public LeitorSpam(File SpamFile) {
		spam_file= SpamFile;

	}
	@Override
	public void run() {
		try {
			Scanner scan = new Scanner(spam_file);
			iterator=0;
			
			while(scan.hasNextLine()) {
				
				String [] linha_lida = scan.nextLine().split("	");

				ArrayList<String> intermidiate = new ArrayList<>(); 
				for (int i = 1; i < linha_lida.length; i++) {
					intermidiate.add(linha_lida[i]);
				}
				GUI.getMapa_Spam().put(iterator, intermidiate);
				iterator++;
			}	
			

			//			for (int i = 0; i <GUI.getMapa_Spam().size(); i++) {
			//				ArrayList<String> m =GUI.getMapa_Spam().get(i);			
			//				for (int j = 0; j < m.size(); j++) {
			//					System.out.print(m.get(j)+" ");
			//					if(j==m.size()-1) System.out.println(" ");
			//				}
			//			}
			scan.close();
		} catch (FileNotFoundException e) {
		}
	}
}