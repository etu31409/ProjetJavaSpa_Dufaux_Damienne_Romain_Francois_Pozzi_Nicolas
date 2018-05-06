package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.SoinException;
import exceptionPackage.VeterinaireException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PanneauListingFichesDeSoin extends JPanel {
    private Controller controller;

    private JPanel panneauContainerPrincipal;
    private JComboBox comboBoxListingFiches;
    private JButton trierButton;
    private JScrollPane listingScrollPane;
    private JTable resultatRecherche;
    private static HashMap<String, String> listeCriteres = new HashMap<String, String>();


    public PanneauListingFichesDeSoin(Controller controller) {
        this.controller = controller;
        initialisationListeCriteres();
        instanciationComboBox();
        trierButton.addActionListener(new EcouteurBouton());
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private static void initialisationListeCriteres(){
        listeCriteres.put("Aucun tri", "");
        listeCriteres.put("Date du soin","dateSoin");
        listeCriteres.put("Identifiant du vétérinaire", "identifiantVeto");
        listeCriteres.put("Identifiant de l'animal", "numRegistre");
        listeCriteres.put("Numéro de soin", "numSoin");
    }

    private void instanciationComboBox() {
        for (String key: listeCriteres.keySet()) {
            comboBoxListingFiches.addItem(key);
        }
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == trierButton){
                try {
                    String critere = (String)comboBoxListingFiches.getSelectedItem();
                    String[][] resultatRequeteRecherche = controller.getSoinsTries(listeCriteres.get(critere));
                    String[] nomDesColonnes = {"Numéro du soin", "Identifiant de l'animal", "Intitulé",
                            "Partie du corps", "Date du soin", "Identifiant du vétérinaire", "Urgent",
                            "Remarque"};
                    resultatRecherche = new JTable(resultatRequeteRecherche, nomDesColonnes);
                    resultatRecherche.setFillsViewportHeight(true);
                    listingScrollPane.setViewportView(resultatRecherche);
                }
                catch (VeterinaireException s) {
                    JOptionPane.showMessageDialog(null, s.getMessage());
                }
                catch (SoinException s) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'accès aux soins");
                }
                catch (SingletonConnectionException s) {
                    JOptionPane.showMessageDialog(null, s.getMessage());
                }
            }
        }
    }
}
