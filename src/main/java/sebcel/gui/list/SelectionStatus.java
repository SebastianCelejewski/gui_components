package sebcel.gui.list;


public class SelectionStatus<T> {

    private int numberOfSelectedElements;
    private T status;

    public SelectionStatus(int numberOfSelectedElements, T status) {
	this.numberOfSelectedElements = numberOfSelectedElements;
	this.status = status;
    }

    public int getNumberOfSelectedElements() {
	return numberOfSelectedElements;
    }

    public T getStatus() {
	return status;
    }

}