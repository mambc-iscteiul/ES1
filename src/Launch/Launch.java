package Launch;

import GUI.GUI;
/**
 * The Launch class is a facade which instantiates the Graphical user interface for an AntiSpamFilter application
 * 
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */

public class Launch {
/**
 * Creates the Graphical user interface
 * 
 * @param args String arguments
 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		GUI window = new GUI(1000, 650);
	}
}
