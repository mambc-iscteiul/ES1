package GUI;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class DefaultTableModelEdited extends DefaultTableModel {

	protected enum Options {MANUAL, AUTOMATIC};
	private Options option;

	public DefaultTableModelEdited(Object[] arg0, int arg1, Options option) {
		super(arg0, arg1);
		this.option = option;
	}

	public void addRow(Object[] rowData) {
		super.addRow(rowData);
	}

	@Override
	public void fireTableCellUpdated(int rowsNumber, int columnsNumber) {
		super.fireTableCellUpdated(rowsNumber, columnsNumber);

		switch (option) {
		case MANUAL:
			try {
				@SuppressWarnings("unused")
				Double a = Double.parseDouble((String) this.getValueAt(rowsNumber, columnsNumber));
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null,"Valor n�o aceite, insira somente decimais");
				this.setValueAt("0.0", rowsNumber, columnsNumber);
			}			

			break;

		default:
			break;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if(option == Options.MANUAL) {
			return column==1;
		}else {
			return false;
		}

	}



}