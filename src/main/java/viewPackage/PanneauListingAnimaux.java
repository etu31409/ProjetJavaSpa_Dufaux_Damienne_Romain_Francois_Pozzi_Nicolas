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

    private JPanel panneauContainerPrincipal;
    private JComboBox comboBoxTriAnimaux;
    private JButton buttonTri;

    public PanneauListingAnimaux(Controller controller){
        this.controller = controller;
        buttonTri.addActionListener(new EcouteurBouton());
        instanciationComboBox();
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private void instanciationComboBox() {
        comboBoxTriAnimaux.removeAllItems();
        String[] listeCritères = {"Numéro de registre", "Nom", "Date d'arrivée", "Espèce", "Race", "Date de naissance",
                "Poids"};
        comboBoxTriAnimaux = new JComboBox(listeCritères);
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonTri){

            }
        }
    }
}
