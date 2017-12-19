package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FileChooserListener implements ActionListener {

	//Enumerados para o FileChooser,como � generalista, perceber qual bot�o o chamou.
	public enum FileType {RULES,SPAM,HAM};

	private JTextField path;	
	private File file;
	private FileType fileType;




	public FileChooserListener(JTextField path, File file, FileType fileType) {
		this.path = path;
		this.file = file;
		this.fileType = fileType;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			do {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				fileChooser.setDialogTitle("Jesus");
				//selecionar somente ficheiros
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				file = fileChooser.getSelectedFile();
				if (!file.getAbsolutePath().endsWith(".cf")&& fileType.equals(FileType.RULES)){
					//Apresentar informa��o de ficheiro n�o suportado quando o tipo requerido � de REGRAS e o ficheiro escolhido n�o coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro n�o suportado, exprimente outro ficheiro do tipo '.cf' ");
				}else if(!file.getName().startsWith("ham")&& fileType.equals(FileType.HAM)){
					//Apresentar informa��o de ficheiro n�o suportado quando o tipo requerido � de HAM e o ficheiro escolhido n�o coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro n�o suportado, exprimente outro ficheiro do tipo 'ham.log' ");
				}else if(!file.getName().startsWith("spam")&& fileType.equals(FileType.SPAM)){
					//Apresentar informa��o de ficheiro n�o suportado quando o tipo requerido � de SPAM e o ficheiro escolhido n�o coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro n�o suportado, exprimente outro ficheiro do tipo '.spam.log' ");
				}
				//Estar ciclicamente a apresentar o FileChooser enquanto o documento selecionado n�o for o correto para cada caso 
			} while(!file.getAbsolutePath().endsWith(".cf")&& fileType.equals(FileType.RULES)|| !file.getName().startsWith("ham")&& fileType.equals(FileType.HAM)|| !file.getName().startsWith("spam")&& fileType.equals(FileType.SPAM));	
			path.setText(file.getAbsolutePath());

		}catch(NullPointerException e){
		}

	}

}
