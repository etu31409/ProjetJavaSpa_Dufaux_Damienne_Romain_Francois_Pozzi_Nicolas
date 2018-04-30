package viewPackage;

import javax.swing.*;

public class ComboBoxStockage extends JPanel {
    private JComboBox combox;

    public ComboBoxStockage() {
        String values[] = {"Cachet", "Pastille", "Crème", "Bandage", "Capsule", "Flacon", "Poudre", "Gouttes"};
        combox = new JComboBox(values);
        combox.setMaximumRowCount(5);
        this.add(combox);
    }
}
