package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.SoinException;
import exceptionPackage.VeterinaireException;
import modelPackage.SoinAvance;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PanneauListingFichesDeSoin extends JPanel {
    private Controller controller;

    private JPanel panneauContainerPrincipal;
    private JComboBox comboBoxListingFiches;
    private JButton trierButton;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JScrollPane listingScrollPane;
    private JTable resultatRecherche;

    public PanneauListingFichesDeSoin(Controller controller) {
        this.controller = controller;
        instanciationComboBox();
        trierButton.addActionListener(new EcouteurBouton());
        supprimerButton.addActionListener(new EcouteurBouton());
        modifierButton.addActionListener(new EcouteurBouton());
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private void instanciationComboBox() {
        comboBoxListingFiches.addItem("Aucun tri");
        comboBoxListingFiches.addItem("Date du soin");
        comboBoxListingFiches.addItem("Identifiant du vétérinaire");
        comboBoxListingFiches.addItem("Identifiant de l'animal");
        comboBoxListingFiches.addItem("Numéro de soin");
        comboBoxListingFiches.setSelectedItem("Aucun tri");
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            TableColumn colonne;
            ListSelectionModel listeSelectionnee;
            TableModeleListeSoins modele;
            ArrayList<SoinAvance> soinsTries = new ArrayList<>();

            if(event.getSource() == trierButton){
                try {
                    String critere = (String)comboBoxListingFiches.getSelectedItem();

                    soinsTries = controller.getSoinsTries(critere);
                    modele = new TableModeleListeSoins(soinsTries);
                    resultatRecherche = new JTable(modele);
                    resultatRecherche.setDefaultRenderer(String.class, modele.getCenterRenderer());
                    resultatRecherche.setDefaultRenderer(Integer.class, modele.getCenterRenderer());
                    listingScrollPane.setViewportView(resultatRecherche);
                    resultatRecherche.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    listingScrollPane.createHorizontalScrollBar();
                    listingScrollPane.createVerticalScrollBar();
                    resultatRecherche.setFillsViewportHeight(true);
                }
                catch (VeterinaireException s) {
                    JOptionPane.showMessageDialog(null, s.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
                catch (SoinException s) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'accès aux soins", "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
                catch (SingletonConnectionException s) {
                    JOptionPane.showMessageDialog(null, s.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(event.getSource() == supprimerButton){
                int confirmation = JOptionPane.showConfirmDialog(null, "La suppression est irréversible. Êtes-vous sûr de vouloir continuer?",
                        "Veuillez confirmer votre choix",
                        JOptionPane.YES_NO_OPTION);
                if(confirmation == 0) {
                    listeSelectionnee = resultatRecherche.getSelectionModel();
                    int indiceLigneSelectionnee = listeSelectionnee.getMinSelectionIndex();
                    try {
                        String critere = (String) comboBoxListingFiches.getSelectedItem();
                        soinsTries = controller.getSoinsTries(critere);
                        SoinAvance soinASup = soinsTries.get(indiceLigneSelectionnee);
                        controller.supprimerSoin(soinASup);
                        trierButton.doClick();
                        JOptionPane.showMessageDialog(null, "Le soin a été correctemen supprimé de la base de données !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    } catch (VeterinaireException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (SoinException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (SingletonConnectionException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    } catch (Exception exception) {
                        System.out.println("Exception : " + exception.getMessage());
                    }
                }
            }
            else if(event.getSource() == modifierButton){
                try{
                    listeSelectionnee = resultatRecherche.getSelectionModel();
                    int indiceLigneSelectionnee = listeSelectionnee.getMinSelectionIndex();
                    String critere = (String)comboBoxListingFiches.getSelectedItem();
                    soinsTries = controller.getSoinsTries(critere);
                    SoinAvance soinAModif = soinsTries.get(indiceLigneSelectionnee);
                    System.out.println(resultatRecherche.isCellEditable(1,1));
                    controller.modifierSoin(soinAModif);
                    JOptionPane.showMessageDialog(null, "Le soin a été correctemen modifié dans la base de données !");
                } catch (VeterinaireException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                } catch (SoinException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                } catch (SingletonConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }catch (Exception e) {
                    System.out.println("Exception : " + e.getMessage());
                }
            }
        }
    }
}
