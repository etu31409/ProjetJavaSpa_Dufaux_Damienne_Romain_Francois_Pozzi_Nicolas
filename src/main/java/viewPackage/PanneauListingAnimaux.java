package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.AnimalException;
import exceptionPackage.ConnexionException;
import exceptionPackage.SoinException;
import modelPackage.Animal;
import modelPackage.SoinAvance;
import viewPackage.tableModele.TableModeleListeAnimaux;

import javax.swing.*;
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
    private FenetrePrincipale fenetrePrincipale;
    private JTable resultatRecherche;

    public PanneauListingAnimaux(Controller controller, FenetrePrincipale fenetrePrincipale){

        this.controller = controller;
        instanciationComboBox();
        buttonTri.addActionListener(new EcouteurBouton());
        supprimerButton.addActionListener(new EcouteurBouton());
        modifierButton.addActionListener(new EcouteurBouton());
        this.fenetrePrincipale = fenetrePrincipale;
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

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            ListSelectionModel listeSelectionnee;
            TableModeleListeAnimaux modele;
            ArrayList<Animal> animauxTries = new ArrayList<>();

            if(event.getSource() == buttonTri){
                try {
                    String critere = (String)comboBoxTriAnimaux.getSelectedItem();
                    animauxTries = controller.getAnimauxTries(critere);

                    modele = new TableModeleListeAnimaux(animauxTries);
                    resultatRecherche = new JTable(modele);

                    resultatRecherche.setDefaultRenderer(String.class, modele.getCenterRenderer());
                    resultatRecherche.setDefaultRenderer(Integer.class, modele.getCenterRenderer());
                    resultatRecherche.setDefaultRenderer(GregorianCalendar.class, modele.getCenterRenderer());
                    resultatRecherche.setDefaultRenderer(Double.class, modele.getCenterRenderer());

                    listingScrollPane.setViewportView(resultatRecherche);
                    resultatRecherche.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

                    listingScrollPane.createHorizontalScrollBar();
                    listingScrollPane.createVerticalScrollBar();
                    resultatRecherche.setFillsViewportHeight(true);
                }
                catch (AnimalException s) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'accès aux animaux", "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
                catch (ConnexionException s) {
                    JOptionPane.showMessageDialog(null, s.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(event.getSource() == supprimerButton) {
                if (resultatRecherche != null) {
                    int confirmation = JOptionPane.showConfirmDialog(null, "La suppression est irréversible." +
                                    " Êtes-vous sûr.e de vouloir continuer ?",
                                    "Veuillez confirmer votre choix",
                            JOptionPane.YES_NO_OPTION);
                    if (confirmation == 0) {
                        listeSelectionnee = resultatRecherche.getSelectionModel();
                        int indiceLigneSelectionnee = listeSelectionnee.getMinSelectionIndex();
                        try {
                            String critere = (String) comboBoxTriAnimaux.getSelectedItem();
                            animauxTries = controller.getAnimauxTries(critere);
                            Animal animalASup = animauxTries.get(indiceLigneSelectionnee);
                            SoinAvance soinASup = controller.getUnSoinAvance(animalASup.getNumRegistre());

                            while (soinASup.getNumRegistre() != null) {
                                controller.supprimerSoin(soinASup);
                                soinASup = controller.getUnSoinAvance(animalASup.getNumRegistre());
                            }
                            controller.supprimerAnimal(animalASup);
                            buttonTri.doClick();
                            JOptionPane.showMessageDialog(null, "L'animal a été correctement supprimé de la base de données !",
                                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        } catch (AnimalException e) {
                            JOptionPane.showMessageDialog(null, "Erreur lors de l'accès aux animaux !", "Erreur !",
                                    JOptionPane.ERROR_MESSAGE);
                        } catch (SoinException e) {
                            JOptionPane.showMessageDialog(null, "Erreur lors de l'accès aux fiches de soin !", "Erreur !",
                                    JOptionPane.ERROR_MESSAGE);
                        } catch (ConnexionException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception e) {
                            System.out.println("Exception: " + e.getMessage());
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Vous devez selectionner un élément dans la liste !", "Erreur !",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if(event.getSource() == modifierButton){
                if(resultatRecherche != null){
                    listeSelectionnee = resultatRecherche.getSelectionModel();
                    int indiceLigneSelectionnee = listeSelectionnee.getMinSelectionIndex();
                    try {
                        String critere = (String) comboBoxTriAnimaux.getSelectedItem();
                        animauxTries = controller.getAnimauxTries(critere);
                        Animal animalModif = animauxTries.get(indiceLigneSelectionnee);
                        fenetrePrincipale.afficherPanneauAnimalPourModifier(animalModif);
                        buttonTri.doClick();

                    } catch (AnimalException e) {
                        JOptionPane.showMessageDialog(null, "Erreur lors de l'accès aux animaux !", "Erreur !",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (ConnexionException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Une erreur imprévue semble être survenue !", "Erreur !",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Vous devez selectionner un élément dans la liste !",  "Erreur !",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
