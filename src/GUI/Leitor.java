package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;


public class Leitor extends Thread{

	private Vector<String> linha;
	private String namefile;
	private ArrayList<String> lista_de_linhas;

	public Leitor(String namefile) {
		this.namefile=namefile;

	}

	@Override
	public void run() {

		try {
			Scanner sc = new Scanner(new File(namefile));
			String str;
			lista_de_linhas = new ArrayList<>();


			while(sc.hasNextLine()) {
				str = sc.nextLine();
				lista_de_linhas.add(str);
				
				for (String string : lista_de_linhas) {
					linha.add(string);
				}
				
				
				
			}
			
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
