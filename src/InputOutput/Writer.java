package InputOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import GUI.DefaultTableModelEdited;
import GUI.GUI;

public class Writer extends Thread{

	private BufferedWriter bufferedWriter;
	private FileWriter fileWriter;
	private DefaultTableModelEdited tableModel;



	public Writer(File file, DefaultTableModelEdited tableModel) throws IOException {
		fileWriter = new FileWriter(file);
		bufferedWriter= new BufferedWriter(fileWriter);
		this.tableModel = tableModel;
	}


	@Override
	public void run() {

		try {
			bufferedWriter.flush();
			for (int i = 0; i < GUI.getManualRulesWeightList().getRowCount(); i++) {
				bufferedWriter.write(tableModel.getValueAt(i, 0)+" "+tableModel.getValueAt(i, 1)+"\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedWriter getWriter() {
		return bufferedWriter;
	}

	public FileWriter getFileWriter() {
		return fileWriter;
	}


}
