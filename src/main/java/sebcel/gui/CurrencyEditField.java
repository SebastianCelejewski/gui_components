package sebcel.gui;

import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CurrencyEditField extends JTextField {

    private DecimalFormat df = new DecimalFormat("0.00 zł");
    private static final long serialVersionUID = 1L;

    private Double value;

    public CurrencyEditField() {
        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                value = parse(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                value = parse(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                value = parse(getText());
            }
        });
    }

    public void setValue(Double value) {
        this.value = value;
        if (value != null) {
            setText(df.format(value));
        } else {
            setText("");
        }
        setBackground(Color.WHITE);
    }

    public Double getValue() {
        return value;
    }

    private Double parse(String s) {
        if (s == null || s.trim().length() == 0) {
            return null;
        }
        try {
            Double newValue = new Double(df.parse(s).doubleValue());
            this.setBackground(Color.WHITE);
            return newValue;
        } catch (Exception e1) {
            try {
                Double newValue = new Double(s.replaceAll(",", "."));
                this.setBackground(Color.WHITE);
                return newValue;
            } catch (Exception e2) {
                this.setBackground(Color.RED);
                return value;
            }
        }
    }
}