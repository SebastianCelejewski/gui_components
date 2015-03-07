package sebcel.gui.list;

import javax.swing.table.TableModel;

public interface ListTableModel<T> extends TableModel {
    
    public void reload();
    
    public int getIndexOfId(int id);
    
    public T getElementAt(int idx);
    
}