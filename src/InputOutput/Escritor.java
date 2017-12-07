package InputOutput;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import GUI.GUI;

public class Escritor extends Thread{

	private BufferedWriter writer;
	private FileWriter fileWriter;



	public Escritor(String path) throws IOException {
		fileWriter= new FileWriter(path);
		writer= new BufferedWriter(fileWriter);
	}


	@Override
	public void run() {

		try {
			writer.flush();
			for (int i = 0; i < GUI.getLista_regras_pesos_manual().getRowCount(); i++) {
				writer.write(GUI.getLista_regras_pesos_manual().getValueAt(i, 0)+" "+GUI.getLista_regras_pesos_manual().getValueAt(i, 1)+"\n");
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
