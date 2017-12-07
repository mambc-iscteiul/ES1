package GUI;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DefTableModel extends DefaultTableModel {

	public DefTableModel(Object[] arg0, int arg1) {
		super(arg0, arg1);
	}

	public void addRow(Object[] rowData) {
		super.addRow(rowData);
	}

	@Override
	public void fireTableCellUpdated(int row, int column) {
		super.fireTableCellUpdated(row, column);
		try {
			Double a = Double.parseDouble((String) this.getValueAt(row, column));
			System.out.println(a);
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"Valor não aceite, insira somente decimais");
			this.setValueAt("0.0", row, column);
		}		
	}

	@Override
	public boolean isCellEditable(int r, int c) {
		return c==1;
	}
}
