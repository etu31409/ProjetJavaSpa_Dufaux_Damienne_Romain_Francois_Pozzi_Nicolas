package viewPackage;

import controllerPackage.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauListingFichesDeSoin extends JPanel {
    private Controller controller;

    private JPanel panneauContainerPrincipal;
    private JComboBox comboBoxListingFiches;
    private JButton trierButton;

    public PanneauListingFichesDeSoin(Controller controller) {
        instanciationComboBox();
        trierButton.addActionListener(new EcouteurBouton());
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private void instanciationComboBox() {
        comboBoxListingFiches.removeAllItems();
        String[] listeCritères = {"Date du soin", "Heure du soin", "Identifiant du vétérinaire",
                "Numéro de registre de l'animal", "Numéro de soin"};
        for(int i = 0; i < listeCritères.length; ++i)
            comboBoxListingFiches.addItem(listeCritères[i]);
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == trierButton){

            }
        }
    }
}
