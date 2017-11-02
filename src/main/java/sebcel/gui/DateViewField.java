package sebcel.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

public class DateViewField extends JTextField {

    private static final long serialVersionUID = 1L;

    private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public void setDate(Date date) {
        if (date != null) {
            this.setText(df.format(date));
        } else {
            this.setText("");
        }
    }
}
