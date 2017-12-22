package InputOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import GUI.DefaultTableModelEdited;
import GUI.GUI;
/**
 * This class writes the desired set of rules and their weights to the file they've been read.
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */
public class Writer extends Thread{

	private BufferedWriter bufferedWriter;
	private FileWriter fileWriter;
	private DefaultTableModelEdited tableModel;


/**
 * Creates a writer to write the elements of the designated TableModel on the File given.
 * @param file the File to write on.
 * @param tableModel the TableModel out of which the rules and weights will be written.
 * @throws IOException an exception in case the buffers are broken or the File is corrupted. 
 */
	public Writer(File file, DefaultTableModelEdited tableModel) throws IOException {
		fileWriter = new FileWriter(file);
		bufferedWriter= new BufferedWriter(fileWriter);
		this.tableModel = tableModel;
	}


	/**
	 * Writes the content of the Model in the specified File
	 */
	@Override
	public void run() {

		
		try {
			bufferedWriter.flush();
			for (int i = 0; i < GUI.getManualRulesWeightList().getRowCount(); i++) {
				bufferedWriter.write(tableModel.getValueAt(i, 0)+" "+tableModel.getValueAt(i, 1)+"\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
		}
	}

	/**
	 * This method return the BufferedWritter used on the writing process
	 * @return a BufferedWriter associated with an FileWriter.
	 * 
	 */
	public BufferedWriter getWriter() {
		return bufferedWriter;
	}

	/**
	 * This method return the FileWriter used on the writing process
	 * @return a FileWriter associated with a File.
	 * 
	 */
	public FileWriter getFileWriter() {
		return fileWriter;
	}


}
