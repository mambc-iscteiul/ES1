package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Leitor extends Thread{

	private ArrayList<String> list;
	private String namefile;

	public Leitor(String namefile) {
		this.namefile=namefile;

	}

	@Override
	public void run() {
		
		try {
			Scanner sc = new Scanner(new File(namefile));
			ArrayList<String> list = new ArrayList<>();
			
			while(sc.hasNextLine()) {
				list.add(sc.nextLine());
				System.out.println(list);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

//		System.out.println("tem File");
		
	}
	
	

	public String getNamefile() {
		return namefile;
	}

	public void setNamefile(String namefile) {
		this.namefile = namefile;
	}

}
