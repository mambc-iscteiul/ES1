package GUI;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 * This is a decorator class which extends the DefaultTableModel class.
 * Some methods are overridden with the intend of satisfying requirements 
 * of non-editability in some cases.
 * Also this class provides additional restrictions in concern with the input values given
 * @author ES1-2017-IC1-70
 * @version 0.9
 *
 */
@SuppressWarnings("serial")
public class DefaultTableModelEdited extends DefaultTableModel {

	/**
	 * This Enumerate is used to determine which type of list to construct.
	 * Manual and Automatic lists have different editability requirements
	 * @author ES1-2017-IC1-70
	 * @version 0.9
	 */
	
	public enum ListType {MANUAL, AUTOMATIC};
	private ListType listType;

	/**
	 * This method constructs a DefaultTableModelEdited with as many columns as there are elements in columnNames and rowCount of null object values. Each column's name will be taken from the columnNames array.
	 * @param columnNames  array containing the names of the new columns
	 * @param columnsrowCount the number of rows the table holds
	 * @param listType the type of list do be constructed
	 */
	public DefaultTableModelEdited(Object[] columnNames, int columnsrowCount, ListType listType) {
		super(columnNames, columnsrowCount);
		this.listType = listType;
	}

	/**
	 * This method adds a row to the end of the model. The new row will contain null values unless rowData is specified. Notification of the row being added will be generated.
	 * @param rowData - optional data of the row being added	
	 */
	public void addRow(Object[] rowData) {
		super.addRow(rowData);
	}

	
	/**
	 *This method notifies all listeners that the value of the cell at [row, column] has been updated.
	 * @param rowsNumber row of cell which has been updated
	 * @param columnsNumber column of cell which has been updated
	 */
	@Override
	public void fireTableCellUpdated(int rowsNumber, int columnsNumber) {
		super.fireTableCellUpdated(rowsNumber, columnsNumber);
		switch (listType) {
		case MANUAL:
			try {
				@SuppressWarnings("unused")
				Double a = Double.parseDouble((String) this.getValueAt(rowsNumber, columnsNumber));
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null,"Valor não aceite, insira somente decimais");
				this.setValueAt("0.0", rowsNumber, columnsNumber);
			}		
			
			break;
		default:
			break;
		}
	}
	/**
	 * This method returns true or false depending on the type of the list
	 * @param row - the row whose value is to be queried
	 * @param column - the column whose value is to be queried
	 */

	@Override
	public boolean isCellEditable(int row, int column) {
		if(listType == ListType.MANUAL) {
			return column==1;
		}else {
			return false;
		}
	}



}
