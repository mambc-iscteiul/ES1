package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FileChooserListener implements ActionListener {

	private JTextField caminho;	
	private File income;
	private FileType enu;

	public enum FileType{RULES,SPAM,HAM}


	public FileChooserListener(JTextField caminho,File income,FileType enu) {
		this.caminho=caminho;
		this.income=income;
		this.enu=enu;

	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			do {
				JFileChooser fc =new JFileChooser();
				fc.showOpenDialog(null);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				income = fc.getSelectedFile();
				if (!income.getAbsolutePath().endsWith(".cf")&& enu.equals(FileType.RULES)){
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro não suportado, exprimente outro ficheiro do tipo '.cf' ");
				}else if(!income.getName().startsWith("ham")&& enu.equals(FileType.HAM)){
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro não suportado, exprimente outro ficheiro do tipo 'ham.log' ");
				}else if(!income.getName().startsWith("spam")&& enu.equals(FileType.SPAM)){
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro não suportado, exprimente outro ficheiro do tipo '.spam.log' ");
				}

			} while(!income.getAbsolutePath().endsWith(".cf")&& enu.equals(FileType.RULES)|| !income.getName().startsWith("ham")&& enu.equals(FileType.HAM)|| !income.getName().startsWith("spam")&& enu.equals(FileType.SPAM));	
			caminho.setText(income.getAbsolutePath());
		}catch(NullPointerException e){

		}


		//inserir aqui THREAD QUE VAI LER O FICHEIRO E METER AS CENAS NAS CELULAS

	}
	
	
}
