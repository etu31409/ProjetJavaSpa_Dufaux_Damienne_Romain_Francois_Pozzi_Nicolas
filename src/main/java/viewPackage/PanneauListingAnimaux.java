package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.AnimalException;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Animal;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class PanneauListingAnimaux extends JPanel {
    private Controller controller;

    private JPanel panneauContainerPrincipal;
    private JComboBox comboBoxTriAnimaux;
    private JButton buttonTri;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JScrollPane listingScrollPane;

    private JTable resultatRecherche;

    public PanneauListingAnimaux(Controller controller){

        this.controller = controller;
        instanciationComboBox();
        buttonTri.addActionListener(new EcouteurBouton());
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private void instanciationComboBox() {
        comboBoxTriAnimaux.addItem("Aucun tri");
        comboBoxTriAnimaux.addItem("Date d'arrivée");
        comboBoxTriAnimaux.addItem("Date de naissance");
        comboBoxTriAnimaux.addItem("Nom");
        comboBoxTriAnimaux.addItem("Identifiant de l'animal");
        comboBoxTriAnimaux.addItem("Poids");
        comboBoxTriAnimaux.addItem("Espèce");
        comboBoxTriAnimaux.setSelectedItem("Aucun tri");
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            TableColumn colonne;
            ListSelectionModel listeSelectionnee;
            TableModeleListeAnimaux modele;
            ArrayList<Animal> animauxTries = new ArrayList<>();

            if(e.getSource() == buttonTri){
                try {
                    String critere = (String)comboBoxTriAnimaux.getSelectedItem();

                    animauxTries = controller.getAnimauxTries(critere);
                    modele = new TableModeleListeAnimaux(animauxTries);
                    resultatRecherche = new JTable(modele);
                    listingScrollPane.setViewportView(resultatRecherche);

                    colonne = resultatRecherche.getColumnModel().getColumn(1);
                    colonne.setPreferredWidth(250);
                    resultatRecherche.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    resultatRecherche.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    listingScrollPane.createHorizontalScrollBar();
                    listingScrollPane.createVerticalScrollBar();
                    resultatRecherche.setFillsViewportHeight(true);

                }
                catch (AnimalException s) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'accès aux animaux");
                }
                catch (SingletonConnectionException s) {
                    JOptionPane.showMessageDialog(null, s.getMessage());
                }
            }
            if(e.getSource() == supprimerButton){
                listeSelectionnee = resultatRecherche.getSelectionModel();
                int indiceLigneSelectionnee = listeSelectionnee.getMinSelectionIndex();
            }
            if(e.getSource() == modifierButton){
                listeSelectionnee = resultatRecherche.getSelectionModel();
                int indiceLigneSelectionnee = listeSelectionnee.getMinSelectionIndex();
            }
        }
    }
}
