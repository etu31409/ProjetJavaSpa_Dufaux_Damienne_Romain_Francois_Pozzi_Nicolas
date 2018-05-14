package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.ConnexionException;
import exceptionPackage.ProprietaireException;
import exceptionPackage.VeterinaireException;
import modelPackage.ProprietaireAnimal;
import modelPackage.Veterinaire;
import viewPackage.tableModele.TableModeleRechercheProprietaires;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        rechercheButton.addActionListener(new RechercheListener());
    }

    public void instancieListeVeterinaire() {
        veterinaireComboBox.removeAllItems();
        try {
            for (Veterinaire v : controller.getVeterinaires()){
                veterinaireComboBox.addItem(v);
            }
        } catch (VeterinaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        } catch (ConnexionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class RechercheListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            TableModeleRechercheProprietaires modele;
            ArrayList<ProprietaireAnimal> resultatRequeteRecherche = new ArrayList<>();

            if (event.getSource() == rechercheButton) {
                try {
                    Veterinaire selectionVeterinaire = (Veterinaire) veterinaireComboBox.getSelectedItem();
                    resultatRequeteRecherche = controller.getResultatRechercheProprietaire(selectionVeterinaire);

                    modele = new TableModeleRechercheProprietaires(resultatRequeteRecherche);
                    resultatRecherche = new JTable(modele);
                    resultatRecherche.setAutoCreateRowSorter(true);

                    resultatRecherche.setDefaultRenderer(String.class, modele.getCenterRenderer());
                    resultatRecherche.setDefaultRenderer(Integer.class, modele.getCenterRenderer());

                    scrollPane.setViewportView(resultatRecherche);
                    resultatRecherche.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    resultatRecherche.setFillsViewportHeight(true);
                }
                catch (ConnexionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                catch (ProprietaireException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }
}
