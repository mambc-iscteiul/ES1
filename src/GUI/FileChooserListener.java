package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class FileChooserListener implements ActionListener {

	private JTextField caminho;	
	private File income;

	public FileChooserListener(JTextField caminho,File income) {
		this.caminho=caminho;
		this.income=income;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc =new JFileChooser();
		fc.showOpenDialog(null);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		income = fc.getSelectedFile();
		caminho.setText(income.getAbsolutePath());
	}
}
