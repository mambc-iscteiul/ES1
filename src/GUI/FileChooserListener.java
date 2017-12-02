package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FileChooserListener implements ActionListener {
	
	//Enumerados para o FileChooser,como � generalista, perceber qual bot�o o chamou.
	public enum FileType{RULES,SPAM,HAM}

	private JTextField caminho;	
	private File income;
	private FileType enu;


	public FileChooserListener() {
	}

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
				//selecionar somente ficheiros
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				income = fc.getSelectedFile();
				if (!income.getAbsolutePath().endsWith(".cf")&& enu.equals(FileType.RULES)){
					//Apresentar informa��o de ficheiro n�o suportado quando o tipo requerido � de REGRAS e o ficheiro escolhido n�o coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro n�o suportado, exprimente outro ficheiro do tipo '.cf' ");
				}else if(!income.getName().startsWith("ham")&& enu.equals(FileType.HAM)){
					//Apresentar informa��o de ficheiro n�o suportado quando o tipo requerido � de HAM e o ficheiro escolhido n�o coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro n�o suportado, exprimente outro ficheiro do tipo 'ham.log' ");
				}else if(!income.getName().startsWith("spam")&& enu.equals(FileType.SPAM)){
					//Apresentar informa��o de ficheiro n�o suportado quando o tipo requerido � de SPAM e o ficheiro escolhido n�o coincide
					JOptionPane.showMessageDialog(null,"Tipo de ficheiro n�o suportado, exprimente outro ficheiro do tipo '.spam.log' ");
				}
					//Estar ciclicamente a apresentar o FileChooser enquanto o documento selecionado n�o for o correto para cada caso 
			} while(!income.getAbsolutePath().endsWith(".cf")&& enu.equals(FileType.RULES)|| !income.getName().startsWith("ham")&& enu.equals(FileType.HAM)|| !income.getName().startsWith("spam")&& enu.equals(FileType.SPAM));	
			caminho.setText(income.getAbsolutePath());
			
		}catch(NullPointerException e){

		}

	}

}
