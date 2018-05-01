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

public class PanneauRechercheProprietaires extends JPanel {

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

        /*this.setLayout(new BorderLayout());
        panneauRecherche = new JPanel();
        panneauListe = new JPanel();
        panneauRechercheContainer = new JPanel();

        panneauRechercheContainer.setLayout(new GridLayout(1, 2));
        panneauRecherche.setLayout(new GridLayout(25, 1));
        panneauListe.setLayout(new GridLayout(2, 1));

        titreRechProprietaire = new JLabel("<html><h1>Recherche des propriétaire selon un vétérinaire</h1></html>");
        titreRechProprietaire.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(titreRechProprietaire, BorderLayout.NORTH);
        this.add(panneauRechercheContainer, BorderLayout.CENTER);

        panneauRechercheContainer.add(panneauRecherche);
        panneauRechercheContainer.add(panneauListe);

        titreSelectionVeterinaire = new JLabel("<html><h3>Selection du vétérinaire</h3></html>");
        titreSelectionVeterinaire.setHorizontalAlignment(SwingConstants.CENTER);
        panneauRecherche.add(titreSelectionVeterinaire);

        listeVeterinaire = new JComboBox();
        instancieListeVeterinaire();
        panneauRecherche.add(listeVeterinaire);

        boutonRecherche = new JButton("Rechercher");
        boutonRecherche.addActionListener(new rechercheListener());
        panneauRecherche.add(boutonRecherche);

        titreResultat = new JLabel("<html><h3>Resultat de la recherche</h3></html>");
        titreResultat.setVerticalAlignment(SwingConstants.TOP);
        panneauListe.add(titreResultat);*/

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
                try {
                    Veterinaire selectionVeterinaire = (Veterinaire) veterinaireComboBox.getSelectedItem();
                    String[][] resultatRequeteRecherche = controller.getResultatRechercheProprietaire(selectionVeterinaire);

                    String[] nomDesColonnes = {"Identifiant de l'animal", "Nom de l'animal", "Identifiant du propriétaire",
                            "Nom du propriétaire"};
                    resultatRecherche = new JTable(resultatRequeteRecherche, nomDesColonnes);
                    resultatRecherche.setFillsViewportHeight(true);
                    scrollPane.setViewportView(resultatRecherche);
                }
                catch (SingletonConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                catch (ProprietaireException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }
}
