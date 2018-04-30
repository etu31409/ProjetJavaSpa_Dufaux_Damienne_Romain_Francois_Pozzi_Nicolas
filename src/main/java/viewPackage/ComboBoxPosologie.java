package viewPackage;

import javax.swing.*;

public class ComboBoxPosologie extends JPanel {
    private JComboBox combox;

    public ComboBoxPosologie() {

        String values[] = {"1mg/1mL","2mg/1mL", "5mg/10mL", "5g/1Kg", "10g/1Kg", "15g/1Kg", "10cm", "50cm", "1m"};
        combox = new JComboBox(values);
        combox.setMaximumRowCount(5);
        this.add(combox);
    }
}
