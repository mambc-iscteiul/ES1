package GUI;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DefTableModel extends DefaultTableModel {
	
	protected enum Options{MANUAL, AUTOMATIC};
	private Options option;

	public DefTableModel(Object[] arg0, int arg1, Options option) {
		super(arg0, arg1);
		this.option = option;
	}

	public void addRow(Object[] rowData) {
		super.addRow(rowData);
	}

	@Override
	public void fireTableCellUpdated(int row, int column) {
		super.fireTableCellUpdated(row, column);
		
		switch (option) {
		case MANUAL:
			try {
				@SuppressWarnings("unused")
				Double a = Double.parseDouble((String) this.getValueAt(row, column));
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null,"Valor nao aceite, insira somente decimais");
				this.setValueAt("0.0", row, column);
			}			
			
			break;

		default:
			break;
		}
	}

	@Override
	public boolean isCellEditable(int r, int c) {
		if(option == Options.MANUAL) {
			return c==1;
		}else {
			return false;
		}
		
	}
	
	
	
}
