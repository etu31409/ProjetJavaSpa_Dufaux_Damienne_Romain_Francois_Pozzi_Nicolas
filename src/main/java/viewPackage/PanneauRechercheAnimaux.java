package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.AnimalException;
import exceptionPackage.MedicamentException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Medicament;
import modelPackage.Veterinaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauRechercheAnimaux {


    private JCheckBox veterinairesCheckBox;
    private JCheckBox medicamentsCheckBox;
    private JButton rechercherButton;
    private JComboBox veterinairesComboBox;
    private JComboBox medicamentsComboBox;
    private JLabel titreFacteurRecherche;
    private JPanel panneauListeRecherche;
    private JScrollPane scrollPane;
    private JPanel panneauContainerPrincipal;
    private JTable tableResultat;
    private Controller controller;

    public PanneauRechercheAnimaux(Controller controller) {
        this.controller = controller;
        rechercherButton.addActionListener(new RechercheListener());
        titreFacteurRecherche = new JLabel("Aucune recherche sélectionnée");

        instanciationComboBox();
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }


    private void instanciationComboBox() {
        veterinairesComboBox.removeAllItems();
        medicamentsComboBox.removeAllItems();
        try {
            for (Veterinaire v : controller.getVeterinaires()) {
                veterinairesComboBox.addItem(v);
            }
            for (Medicament m : controller.getMedicaments()) {
                medicamentsComboBox.addItem(m);
            }
        } catch (VeterinaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (MedicamentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private class RechercheListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == rechercherButton) {
                String titrefacteur = "";
                String[] nomDesColonnes = {"Identifiant de l'animal", "Nom de l'animal"};
                try {
                    if (veterinairesCheckBox.isSelected() && !medicamentsCheckBox.isSelected())
                    {
                        Veterinaire veterinaireChoisi = (Veterinaire) veterinairesComboBox.getSelectedItem();
                        String[][] resultatRequeteRecherche = controller.getResultatRecherchAnimauxVeterinaire(veterinaireChoisi);
                        tableResultat = new JTable(resultatRequeteRecherche, nomDesColonnes);
                        tableResultat.setFillsViewportHeight(true);
                        scrollPane.setViewportView(tableResultat);
                    }
                    else if (!veterinairesCheckBox.isSelected() && medicamentsCheckBox.isSelected())
                    {
                        Medicament medicamentChoisi = (Medicament) medicamentsComboBox.getSelectedItem();
                        String[][] resultatRequeteRecherche = controller.getResultatRecherchAnimauxMedicament(medicamentChoisi);
                        tableResultat = new JTable(resultatRequeteRecherche, nomDesColonnes);
                        tableResultat.setFillsViewportHeight(true);
                        scrollPane.setViewportView(tableResultat);
                    }
                    else if (veterinairesCheckBox.isSelected() && medicamentsCheckBox.isSelected())
                    {
                        Veterinaire veterinaireChoisi = (Veterinaire) veterinairesComboBox.getSelectedItem();
                        Medicament medicamentChoisi = (Medicament) medicamentsComboBox.getSelectedItem();
                        String[][] resultatRequeteRecherche = controller.getResultatRecherchAnimauxMedicamentVeto(medicamentChoisi, veterinaireChoisi);
                        tableResultat = new JTable(resultatRequeteRecherche, nomDesColonnes);
                        tableResultat.setFillsViewportHeight(true);
                        scrollPane.setViewportView(tableResultat);
                    }
                    else if (!veterinairesCheckBox.isSelected() && !medicamentsCheckBox.isSelected())
                    {
                        JOptionPane.showMessageDialog(null, "Veuillez selectionner au moins un des deux critères!");
                    }
                }
                catch (AnimalException e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                catch (SingletonConnectionException e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }
}
