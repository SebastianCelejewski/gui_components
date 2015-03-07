package sebcel.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import com.standbysoft.datepicker.JDatePicker;

public class DateEditField extends JComponent {

    private static final long serialVersionUID = 1L;

    private JRadioButton button = new JRadioButton();
    private JDatePicker datePicker = new JDatePicker();
    private JLabel label = new JLabel("(nieokreœlona)");

    public DateEditField() {
	this.setLayout(new BorderLayout());
	this.add(button, BorderLayout.WEST);
	this.add(datePicker, BorderLayout.CENTER);
	this.add(label, BorderLayout.EAST);

	button.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		setEnabled(button.isSelected());
	    }
	});
    }

    public Date getValue() {
	if (button.isSelected()) {
	    return datePicker.getSelectedDate();
	} else {
	    return null;
	}
    }

    public void setValue(Date value) {
	if (value != null) {
	    datePicker.setSelectedDate(value);
	    setEnabled(true);
	} else {
	    setEnabled(false);
	}
    }

    public void setEnabled(boolean enabled) {
	button.setSelected(enabled);
	datePicker.setVisible(enabled);
	label.setVisible(!enabled);
	datePicker.repaint();
	label.repaint();
    }
}