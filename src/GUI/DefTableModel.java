package GUI;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DefTableModel extends DefaultTableModel {

	public DefTableModel() {
		super();
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


	public void addRow(Object[] rowData) {
		// TODO Auto-generated method stub
		super.addRow(rowData);
	}
	
	@Override
	public void fireTableCellUpdated(int row, int column) {
		super.fireTableCellUpdated(row, column);
//		System.out.println("algo mudou na celula"+row+","+column);
//		System.out.println("valor mudado para:"+this.getValueAt(row, column));
		try {
		Double a = Double.parseDouble((String) this.getValueAt(row, column));
		System.out.println(a);
		}catch(NumberFormatException e) {
			System.out.println("é string");
			JOptionPane.showMessageDialog(null,"Valor não aceite, insira somente decimais");
			this.setValueAt("0.0", row, column);
		}		
	}

	@Override
	public boolean isCellEditable(int r, int c) {
		return c==1;
	}

}
