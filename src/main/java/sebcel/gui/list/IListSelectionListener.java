package sebcel.gui.list;

import java.util.Collection;

public interface IListSelectionListener<T> {

    public void elementsWasSelected(Collection<T> elements);

}
