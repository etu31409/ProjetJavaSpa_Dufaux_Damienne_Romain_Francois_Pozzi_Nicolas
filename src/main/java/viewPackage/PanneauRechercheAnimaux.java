package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.AnimalException;
import exceptionPackage.MedicamentException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Animal;
import modelPackage.Medicament;
import modelPackage.Veterinaire;
import viewPackage.tableModele.TableModeleRechercheAnimaux;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        titreFacteurRecherche.setText("Aucune recherche sélectionnée");

        instancieComboBox();
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private void instancieComboBox() {
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (MedicamentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class RechercheListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            ArrayList<Animal> resultatRequeteRecherche = new ArrayList<>();
            TableModeleRechercheAnimaux modele;

            if (event.getSource() == rechercherButton) {
                try {
                    if (veterinairesCheckBox.isSelected() && !medicamentsCheckBox.isSelected())
                    {
                        Veterinaire veterinaireChoisi = (Veterinaire) veterinairesComboBox.getSelectedItem();
                        resultatRequeteRecherche = controller.getResultatRecherchAnimauxVeterinaire(veterinaireChoisi);
                        titreFacteurRecherche.setText("Résultat.s de la recherche pour le vétérinaire " +
                                veterinaireChoisi.getNom());
                    }
                    else if (!veterinairesCheckBox.isSelected() && medicamentsCheckBox.isSelected())
                    {
                        Medicament medicamentChoisi = (Medicament) medicamentsComboBox.getSelectedItem();
                        resultatRequeteRecherche = controller.getResultatRecherchAnimauxMedicament(medicamentChoisi);
                        titreFacteurRecherche.setText("Résultat.s de la recherche pour le médicament " +
                                medicamentChoisi.getNomMedic());
                    }
                    else if (veterinairesCheckBox.isSelected() && medicamentsCheckBox.isSelected())
                    {
                        Veterinaire veterinaireChoisi = (Veterinaire) veterinairesComboBox.getSelectedItem();
                        Medicament medicamentChoisi = (Medicament) medicamentsComboBox.getSelectedItem();
                        resultatRequeteRecherche = controller.getResultatRecherchAnimauxMedicamentVeto(medicamentChoisi,
                                veterinaireChoisi);
                        titreFacteurRecherche.setText("Résultat.s de la recherche pour pour le médicament " +
                                medicamentChoisi.getNomMedic()
                                + " et le vétérinaire " + veterinaireChoisi.getPrenom()+  " " + veterinaireChoisi.getNom());
                    }
                    else if (!veterinairesCheckBox.isSelected() && !medicamentsCheckBox.isSelected())
                    {
                        JOptionPane.showMessageDialog(null, "Veuillez selectionner au moins un des deux critères !",
                                "Attention !", JOptionPane.INFORMATION_MESSAGE);
                    }

                    modele = new TableModeleRechercheAnimaux(resultatRequeteRecherche);
                    tableResultat = new JTable(modele);
                    tableResultat.setAutoCreateRowSorter(true);

                    tableResultat.setDefaultRenderer(String.class, modele.getCenterRenderer());
                    tableResultat.setDefaultRenderer(Integer.class, modele.getCenterRenderer());

                    scrollPane.setViewportView(tableResultat);

                    tableResultat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    tableResultat.setFillsViewportHeight(true);

                    scrollPane.createHorizontalScrollBar();
                    scrollPane.createVerticalScrollBar();
                }
                catch (AnimalException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur !", JOptionPane.ERROR_MESSAGE);
                }
                catch (SingletonConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
