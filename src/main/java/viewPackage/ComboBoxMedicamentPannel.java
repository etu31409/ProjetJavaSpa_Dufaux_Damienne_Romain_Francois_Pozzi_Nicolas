package viewPackage;

import javax.swing.*;

public class ComboBoxMedicamentPannel extends JPanel {
    private JComboBox combox;

    public ComboBoxMedicamentPannel() {
        //récupérer les médicaments de la base de donnée
        String values[] = {"Liste des médicaments de l'animal","Cahchet", "Crème", "Pastille", "Bandage"};
        combox = new JComboBox(values);
        combox.setSelectedItem("Liste des médicaments de l'animal");
        combox.setMaximumRowCount(3);
        this.add(combox);
    }
}
