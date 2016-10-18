package ztp_4.adapters;

import javax.swing.table.TableModel;
import ztp_4.interfaces.Data;

/**
 *
 * @author Radosław Naruszewicz <dorianin5@gmail.com>
 */
public interface Adapter extends TableModel{
    public void setData(Data data);
}
