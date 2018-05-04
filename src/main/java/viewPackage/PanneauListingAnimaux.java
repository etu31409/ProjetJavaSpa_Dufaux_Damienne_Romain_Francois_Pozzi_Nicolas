package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.AnimalException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Animal;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauListingAnimaux extends JPanel {
    private Controller controller;

    private JPanel panneauRecherche, panneauBoutons;
    private JButton retour;
    private JLabel intituleLabel, videLabel;
    private JPanel panneauContainerPrincipal;
    private JComboBox comboBoxTriAnimaux;
    private JTable tableListingAnimaux;

    public PanneauListingAnimaux(Controller controller){
        this.controller = controller;
        instanciationComboBox();


    }

    private void instanciationComboBox() {
        comboBoxTriAnimaux.removeAllItems();
        try {
            for (Animal v : controller.getAnimaux()) {
                comboBoxTriAnimaux.addItem(v);
            }
        } catch (AnimalException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
