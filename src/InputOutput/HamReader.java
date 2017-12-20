package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.GUI;
/**
 * This class maps all the Ham e-mails in a relation (number of email)-(rules observed).
 * 
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */
public class HamReader extends Thread{

	private File hamFile;
	private ArrayList<String> hamArray;

	/**
	 * Creates a HamReader to work on the designated File
	 * @param file file file to work on
	 */
	public HamReader(File file) {
		this.hamFile=file;	
	}

	/**
	 * It associates the number of the email to the observed 
	 * rules in that email(line) and maps that relation.
	 * If this thread was constructed using a separate Runnable run object, then that Runnable object's run method is called; otherwise, this method does nothing and returns. 
	 * Subclasses of Thread should override this method.
	 */

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
