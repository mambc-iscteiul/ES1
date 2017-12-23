package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 * This class is a ActionListener wich is to be called to elegantly assert
 *  which Files are to be used during the program.
 * 
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */
public class FileChooserListener implements ActionListener {

	/**
	 * This Nested class is used for the Listener to determine by which button it has been called
	 * so as to assert which types of Files can be admited or not. 
	 * 
	 * @author ES1-2017-IC1-70
	 * @version 0.9
	 */
	public enum FileType {RULES,SPAM,HAM};

	private JTextField path;	
	private File file;
	private FileType fileType;



	/**
	 * This method constructs the Listener in association with a FileType to assert if the 
	 * correct type of File is selected, if so, it utilizes it to extract it's absolute path and 
	 * represent it visually via the JtextField provided
	 * 
	 * @param path the TextField object responsible for the visualization of the absolute path of the selected File
	 * @param file selected File in association with the FileType
	 * @param fileType Enumerated of the description of the type of File to be instantiated
	 */
	
	public FileChooserListener(JTextField path, File file, FileType fileType) {
		this.path = path;
		this.file = file;
		this.fileType = fileType;
	}

	/**
	 * This method call the JFileChooser API for the sorting of the correct file to
	 * instantiate
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			do {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				//selecionar somente ficheiros
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
				}
				//Estar ciclicamente a apresentar o FileChooser enquanto o documento selecionado não for o correto para cada caso 
			} while(!file.getAbsolutePath().endsWith(".cf")&& fileType.equals(FileType.RULES)|| !file.getName().startsWith("ham")&& fileType.equals(FileType.HAM)|| !file.getName().startsWith("spam")&& fileType.equals(FileType.SPAM));	
			path.setText(file.getAbsolutePath());
			
		}catch(NullPointerException e){
		}
	}
}
