package InputOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.GUI;
/**
 * This class maps all the Spam e-mails in a relation (number of email)-(rules observed).
 * 
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */
public class SpamReader extends Thread {

	private File spamFile;
	private int keyValue;

	/**
	 * Creates a SpamReader to work on the designated File
	 * @param spamfile file to work on
	 */
	public SpamReader(File spamfile) {
		spamFile= spamfile;

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