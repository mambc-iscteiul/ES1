package GUI;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DefTableModel extends DefaultTableModel {

	public DefTableModel() {
		// TODO Auto-generated constructor stub
	}

	public DefTableModel(int arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DefTableModel(Vector arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DefTableModel(Object[] arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DefTableModel(Vector arg0, Vector arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DefTableModel(Object[][] arg0, Object[] arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isCellEditable(int r, int c) {
		return false;
	}

}
