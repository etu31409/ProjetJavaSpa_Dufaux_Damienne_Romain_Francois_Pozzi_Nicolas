package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.AnimalException;
import exceptionPackage.ConnexionException;
import exceptionPackage.SoinException;
import exceptionPackage.VeterinaireException;
import modelPackage.SoinAnimalVeto;
import viewPackage.tableModele.TableModeleListeSoins;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PanneauListingFichesDeSoin extends JPanel {
    private Controller controller;
    private FenetrePrincipale fenetrePrincipale;
    private JPanel panneauContainerPrincipal;
    private JComboBox comboBoxListingFiches;
    private JButton trierButton;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JScrollPane listingScrollPane;
    private JTable resultatRecherche;

    public PanneauListingFichesDeSoin(Controller controller, FenetrePrincipale fenetrePrincipale) {
        this.controller = controller;
        this.fenetrePrincipale = fenetrePrincipale;
        instanciationComboBox();
        trierButton.addActionListener(new EcouteurBouton());
        supprimerButton.addActionListener(new EcouteurBouton());
        modifierButton.addActionListener(new EcouteurBouton());
    }

    private void instanciationComboBox() {
        comboBoxListingFiches.addItem("Aucun tri");
        comboBoxListingFiches.addItem("Date du soin");
        comboBoxListingFiches.addItem("Identifiant du vétérinaire");
        comboBoxListingFiches.addItem("Identifiant de l'animal");
        comboBoxListingFiches.addItem("Identifiant du soin");
        comboBoxListingFiches.setSelectedItem("Aucun tri");
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            TableModeleListeSoins modele;
            ArrayList<SoinAnimalVeto> soinsTries;

            if (event.getSource() == trierButton) {
                try {
                    String critere = (String) comboBoxListingFiches.getSelectedItem();
                    soinsTries = controller.getSoinsTries(critere);

                    modele = new TableModeleListeSoins(soinsTries);
                    resultatRecherche = new JTable(modele);

                    resultatRecherche.setDefaultRenderer(String.class, modele.getCenterRenderer());
                    resultatRecherche.setDefaultRenderer(Integer.class, modele.getCenterRenderer());
                    resultatRecherche.setDefaultRenderer(GregorianCalendar.class, modele.getCenterRenderer());

                    listingScrollPane.setViewportView(resultatRecherche);
                    resultatRecherche.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    listingScrollPane.createHorizontalScrollBar();
                    listingScrollPane.createVerticalScrollBar();
                    resultatRecherche.setFillsViewportHeight(true);
                } catch (VeterinaireException s) {
                    JOptionPane.showMessageDialog(null, s.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                } catch (SoinException s) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'accès aux soins", "Erreur !",
                            JOptionPane.ERROR_MESSAGE);
                } catch (ConnexionException s) {
                    JOptionPane.showMessageDialog(null, s.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                } catch (AnimalException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            } else if (event.getSource() == supprimerButton) {
                if (resultatRecherche != null) {
                    int confirmation = JOptionPane.showConfirmDialog(null, "La suppression est irréversible. " +
                                    "Êtes-vous sûr.e de vouloir continuer ?",
                            "Veuillez confirmer votre choix",
                            JOptionPane.YES_NO_OPTION);
                    if (confirmation == 0) {
                        try {
                            modele = (TableModeleListeSoins) resultatRecherche.getModel();
                            Integer ligne = resultatRecherche.getSelectionModel().getMinSelectionIndex();
                            SoinAnimalVeto soinAnimalVeto = modele.getSoinAnimalVetoSelectionne(ligne);
                            controller.supprimerSoin(soinAnimalVeto.getNumSoin());

                            JOptionPane.showMessageDialog(null, "Le soin a été correctement supprimé de la base de" +
                                    " données !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            trierButton.doClick();
                        } catch (SoinException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        } catch (ConnexionException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                        } catch (Exception exception) {
                            System.out.println("Exception : " + exception.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vous devez sélectionner un élément dans la liste !", "Erreur !",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (event.getSource() == modifierButton) {
                if (resultatRecherche != null) {
                    try {
                        modele = (TableModeleListeSoins) resultatRecherche.getModel();
                        Integer ligne = resultatRecherche.getSelectionModel().getMinSelectionIndex();
                        SoinAnimalVeto soinAnimalVeto = modele.getSoinAnimalVetoSelectionne(ligne);

                        fenetrePrincipale.afficherPanneauSoinPourModifier(controller.getUnSoinAvance(soinAnimalVeto.getNumSoin()));
                    } catch (SoinException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                    } catch (ConnexionException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
                        System.out.println("Exception : " + e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vous devez sélectionner un élément dans la liste !", "Erreur !",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

