package viewPackage;

import javax.swing.*;

public class ComboBoxVeterinairePannel extends JPanel {
    private JComboBox combox;

    public ComboBoxVeterinairePannel() {
        String values[] = {"Vétérinaire responsable","Hainstein", "Garcia", "Assin"};
        combox = new JComboBox(values);
        combox.setSelectedItem("térinaire responsable");
        combox.setMaximumRowCount(3);
        this.add(combox);
    }
}
