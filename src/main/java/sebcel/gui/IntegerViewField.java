package sebcel.gui;

import javax.swing.JTextField;

public class IntegerViewField extends JTextField {

    private static final long serialVersionUID = 1L;

    public void setValue(Integer value) {
	if (value != null) {
	    this.setText(Integer.toString(value));
	} else {
	    this.setText("");
	}
    }
}
