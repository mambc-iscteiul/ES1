package InputOutput;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import GUI.DefTableModel;
import GUI.GUI;

public class Escritor extends Thread{

	private BufferedWriter writer;
	private FileWriter fileWriter;
	private DefTableModel model;



	public Escritor(File file,DefTableModel model) throws IOException {
		fileWriter= new FileWriter(file);
		writer= new BufferedWriter(fileWriter);
		this.model=model;
	}


	@Override
	public void run() {

		try {
			writer.flush();
			for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
				writer.write(model.getValueAt(i, 0)+" "+model.getValueAt(i, 1)+"\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public FileWriter getFileWriter() {
		return fileWriter;
	}


}
