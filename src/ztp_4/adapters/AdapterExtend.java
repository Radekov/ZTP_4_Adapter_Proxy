package ztp_4.adapters;

import javax.swing.table.AbstractTableModel;
import ztp_4.interfaces.Data;

/**
 *
 * @author Radosław Naruszewicz <dorianin5@gmail.com>
 */
//@TODO Zmienić AdapterExtend na AdapterExtend<T>
public class AdapterExtend extends AbstractTableModel implements Adapter {

    private Data data = null;

    @Override
    public void setData(Data data) {
        this.data = data;
        //fireTableDataChanged();
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return new Integer(rowIndex + 1);
            case 1:
                return new Integer(data.get(rowIndex));
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Indeks";
            case 1:
                return "Wartość";
            default:
                return "WTF";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return true;
        }
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.set(rowIndex, ((Integer) aValue).intValue());
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    //zaimplementuj również setValueAt - pozwolą one na edycję danych
}
