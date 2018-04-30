package viewPackage;

import javax.swing.*;

public class ComboBoxMedicament extends JPanel{
    private JComboBox combox;

    public ComboBoxMedicament() {

        String values[] = {"Fadalgan","Isobetadaine", "Morfyne", "Plastanza", "Prannedoli", "Cloviraci", "Rolphinamo", "Tadinlora", "Lyzoe"};
        combox = new JComboBox(values);
        combox.setMaximumRowCount(5);
        this.add(combox);
    }
}
