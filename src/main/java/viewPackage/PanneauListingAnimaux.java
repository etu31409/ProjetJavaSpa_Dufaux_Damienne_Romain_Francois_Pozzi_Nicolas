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
import java.util.HashMap;

public class PanneauListingAnimaux extends JPanel {
    private Controller controller;

    private JPanel panneauContainerPrincipal;
    private JComboBox comboBoxTriAnimaux;
    private JButton buttonTri;
    private JScrollPane listingScrollPane;

    private JTable resultatRecherche;
    private static HashMap<String, String> listeCriteres = new HashMap<String, String>();

    public PanneauListingAnimaux(Controller controller){
        this.controller = controller;
        initialisationListeCriteres();
        instanciationComboBox();
        buttonTri.addActionListener(new EcouteurBouton());
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private static void initialisationListeCriteres(){
        listeCriteres.put("Aucun tri", "");
        listeCriteres.put("Date d'arrivée","dateArrivee");
        listeCriteres.put("Date de naissance", "dateNaissance");
        listeCriteres.put("Nom", "nom");
        listeCriteres.put("Identifiant de l'animal", "numRegistre");
        listeCriteres.put("Poids", "poids");
        listeCriteres.put("Espèce", "espece");
    }

    private void instanciationComboBox() {
        for (String key: listeCriteres.keySet()) {
            comboBoxTriAnimaux.addItem(key);
        }
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonTri){
                try {
                    String critere = (String)comboBoxTriAnimaux.getSelectedItem();
                    String[][] resultatRequeteRecherche = controller.getAnimauxTries(listeCriteres.get(critere));
                    String[] nomDesColonnes = {"Identifiant de l'animal", "Date d'arrivée","Nom", "Espèce", "Race",
                            "Sexe", "Stérilisé.e", "Couleur", "Date de naissance","Poids", "Identifiant du propriétaire",
                            "Numéro de puce", "Localisation de la puce", "Date d'attribution de la puce",
                            "Numéro de tatouage", "Localisation du tatouage"};
                    resultatRecherche = new JTable(resultatRequeteRecherche, nomDesColonnes);
                    resultatRecherche.setFillsViewportHeight(true);
                    listingScrollPane.setViewportView(resultatRecherche);
                }
                catch (AnimalException s) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'accès aux animaux");
                }
                catch (SingletonConnectionException s) {
                    JOptionPane.showMessageDialog(null, s.getMessage());
                }
            }
        }
    }
}
