package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FileChooserListener implements ActionListener {

<<<<<<< HEAD
	//Enumerados para o FileChooser,como é generalista, perceber qual botão o chamou.
	public enum FileType {RULES,SPAM,HAM};
=======
	//Enumerados para o FileChooser,como e generalista, perceber qual botao o chamou.
	public enum FileType{RULES,SPAM,HAM};
>>>>>>> branch 'master' of https://github.com/mambc-iscteiul/ES1-2017-IC1-70.git

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
<<<<<<< HEAD
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				file = fileChooser.getSelectedFile();
				if (!file.getAbsolutePath().endsWith(".cf")&& fileType.equals(FileType.RULES)){
					//Apresentar informação de ficheiro não suportado quando o tipo requerido é de REGRAS e o ficheiro escolhido não coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro não suportado, exprimente outro ficheiro do tipo '.cf' ");
				}else if(!file.getName().startsWith("ham")&& fileType.equals(FileType.HAM)){
					//Apresentar informação de ficheiro não suportado quando o tipo requerido é de HAM e o ficheiro escolhido não coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro não suportado, exprimente outro ficheiro do tipo 'ham.log' ");
				}else if(!file.getName().startsWith("spam")&& fileType.equals(FileType.SPAM)){
					//Apresentar informação de ficheiro não suportado quando o tipo requerido é de SPAM e o ficheiro escolhido não coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro não suportado, exprimente outro ficheiro do tipo '.spam.log' ");
=======
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				income = fc.getSelectedFile();
				if (!income.getAbsolutePath().endsWith(".cf")&& enu.equals(FileType.RULES)){
					//Apresentar informacao de ficheiro nao suportado quando o tipo requerido e de REGRAS e o ficheiro escolhido nao coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro nao suportado, exprimente outro ficheiro do tipo '.cf' ");
				}else if(!income.getName().startsWith("ham")&& enu.equals(FileType.HAM)){
					//Apresentar informacao de ficheiro nao suportado quando o tipo requerido e de HAM e o ficheiro escolhido nao coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro nao suportado, exprimente outro ficheiro do tipo 'ham.log' ");
				}else if(!income.getName().startsWith("spam")&& enu.equals(FileType.SPAM)){
					//Apresentar informacao de ficheiro nao suportado quando o tipo requerido e de SPAM e o ficheiro escolhido nao coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro nao suportado, exprimente outro ficheiro do tipo '.spam.log' ");
>>>>>>> branch 'master' of https://github.com/mambc-iscteiul/ES1-2017-IC1-70.git
				}
<<<<<<< HEAD
				//Estar ciclicamente a apresentar o FileChooser enquanto o documento selecionado não for o correto para cada caso 
			} while(!file.getAbsolutePath().endsWith(".cf")&& fileType.equals(FileType.RULES)|| !file.getName().startsWith("ham")&& fileType.equals(FileType.HAM)|| !file.getName().startsWith("spam")&& fileType.equals(FileType.SPAM));	
			path.setText(file.getAbsolutePath());
=======
				//Estar ciclicamente a apresentar o FileChooser enquanto o documento selecionado nao for o correto para cada caso 
			} while(!income.getAbsolutePath().endsWith(".cf")&& enu.equals(FileType.RULES)|| !income.getName().startsWith("ham")&& enu.equals(FileType.HAM)|| !income.getName().startsWith("spam")&& enu.equals(FileType.SPAM));	
			caminho.setText(income.getAbsolutePath());
>>>>>>> branch 'master' of https://github.com/mambc-iscteiul/ES1-2017-IC1-70.git

		}catch(NullPointerException e){
		}

	}

}
