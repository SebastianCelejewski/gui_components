package sebcel.gui.list;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class ListTable<T> extends JComponent {

    private static final long serialVersionUID = 1L;

    private JScrollPane scrollPane;
    private JTable table;

    private ListTableModel<T> tableModel;
    private Set<IListSelectionListener<T>> listSelectionListeners = new HashSet<IListSelectionListener<T>>();

    public ListTable(ListTableModel<T> tableModel, TableColumnModel tableColumnModel, TableRowSorter<? extends ListTableModel<T>> sorter, TableCellRenderer cellRenderer) {
        this.tableModel = tableModel;

        table = new JTable();
        table.setAutoCreateRowSorter(true);
        table.setModel(tableModel);
        table.setColumnModel(tableColumnModel);
        table.setRowSorter(sorter);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        int cc = table.getColumnCount();
        for (int i = 0; i < cc; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        scrollPane = new JScrollPane(table);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                if (selectedRows != null && selectedRows.length > 0) {
                    Collection<T> selectedElements = new ArrayList<T>();
                    for (int selectedRow : selectedRows) {
                        if (selectedRow > -1) {
                            selectedRow = table.convertRowIndexToModel(selectedRow);
                            T element = ListTable.this.tableModel.getElementAt(selectedRow);
                            selectedElements.add(element);
                        }
                    }
                    fireListSelectionChanged(selectedElements);
                }
            }
        });
    }

    public void reload() {
        tableModel.reload();
    }

    public Collection<Integer> getSelectedElementIds() {
        int[] selectedRows = table.getSelectedRows();
        if (selectedRows == null || selectedRows.length == 0) {
            return new ArrayList<Integer>();
        }
        Collection<Integer> selectedIds = new ArrayList<Integer>();
        for (int selectedRow : selectedRows) {
            int modelRow = table.convertRowIndexToModel(selectedRow);
            selectedIds.add((Integer) table.getModel().getValueAt(modelRow, 0));
        }
        return selectedIds;
    }

    public void setSelectedElementId(Integer id) {
        if (id != null) {
            int modelIdx = tableModel.getIndexOfId(id);
            int viewIdx = table.convertRowIndexToView(modelIdx);
            table.getSelectionModel().setSelectionInterval(viewIdx, viewIdx);

            Rectangle viewRectangle = table.getCellRect(viewIdx, 0, true);
            if (!scrollPane.getViewport().getViewRect().contains(viewRectangle)) {
                scrollPane.getViewport().setViewPosition(new Point(0, 0)); // viewport bug fix
                scrollPane.getViewport().scrollRectToVisible(viewRectangle);
            }
        }
    }

    public void addListSelectionListener(IListSelectionListener<T> listener) {
        listSelectionListeners.add(listener);
    }

    private void fireListSelectionChanged(Collection<T> elements) {
        for (IListSelectionListener<T> listener : listSelectionListeners) {
            listener.elementsWasSelected(elements);
        }
    }
}