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
        String[] listeCritères = new String[] {"Date d'arrivée", "Date de naissance", "Espèce",  "Nom",
                "Numéro de registre", "Poids", "Race"};
        for(int i = 0; i < listeCritères.length; ++i)
            comboBoxTriAnimaux.addItem(listeCritères[i]);
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonTri){

            }
        }
    }
}
