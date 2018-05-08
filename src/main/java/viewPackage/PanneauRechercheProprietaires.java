package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Veterinaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauRechercheProprietaires{

    private Controller controller;
    private JTable resultatRecherche;
    private JComboBox veterinaireComboBox;
    private JButton rechercheButton;
    private JPanel panneauContainerPrincipal;
    private JPanel panneauListeRecherche;
    private JScrollPane scrollPane;

    public PanneauRechercheProprietaires(Controller controller) {
        this.controller = controller;
        instancieListeVeterinaire();
        rechercheButton.addActionListener(new rechercheListener());
    }

    public void instancieListeVeterinaire() {
        veterinaireComboBox.removeAllItems();
        try {
            for (Veterinaire v : controller.getVeterinaires()){
                veterinaireComboBox.addItem(v);
            }
        } catch (VeterinaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class rechercheListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == rechercheButton) {
                /*try {
                    Veterinaire selectionVeterinaire = (Veterinaire) veterinaireComboBox.getSelectedItem();
                    String[][] resultatRequeteRecherche = controller.getResultatRechercheProprietaire(selectionVeterinaire);

                    String[] nomDesColonnes = {"Identifiant de l'animal", "Nom de l'animal", "Identifiant du propriétaire",
                            "Nom du propriétaire"};
                    resultatRecherche = new JTable(resultatRequeteRecherche, nomDesColonnes);
                    resultatRecherche.setRowSelectionAllowed(false);
                    resultatRecherche.setFillsViewportHeight(true);
                    scrollPane.setViewportView(resultatRecherche);
                }
                catch (SingletonConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                catch (ProprietaireException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }*/
            }
        }
    }
}
