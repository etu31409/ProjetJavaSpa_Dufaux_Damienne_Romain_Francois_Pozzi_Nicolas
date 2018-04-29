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
    private JPanel panneauRecherche, panneauListe, panneauRechercheContainer;
    private JComboBox listeVeterinaire;
    private JButton boutonRecherche;
    private JLabel titreRechProprietaire, titreSelectionVeterinaire, titreResultat;
    private JTable resultatRecherche;
    private JScrollPane jScrollpane;

    public PanneauRechercheProprietaires(Controller controller) {
        this.controller = controller;

        this.setLayout(new BorderLayout());
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
        panneauListe.add(titreResultat);


    }

    public void instancieListeVeterinaire() {
        listeVeterinaire.removeAllItems();
        try {
            for (Veterinaire v : controller.getIdentifiantsVeterinaires()) {
                listeVeterinaire.addItem(v);
            }
        } catch (VeterinaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private class rechercheListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == boutonRecherche) {
                try {
                    if (jScrollpane != null)
                        panneauListe.remove(jScrollpane);
                    Veterinaire selectionVeterinaire = (Veterinaire) listeVeterinaire.getSelectedItem();
                    String[][] resultatRequeteRecherche = controller.getResultatRechercheProprietaire(selectionVeterinaire);

                    String[] nomDesColonnes = {"Identifiant de l'animal", "Nom de l'animal", "Identifiant du propriétaire",
                            "Nom du propriétaire"};
                    resultatRecherche = new JTable(resultatRequeteRecherche, nomDesColonnes);
                    jScrollpane = new JScrollPane(resultatRecherche);
                    panneauListe.add(jScrollpane);
                    panneauListe.doLayout();
                } catch (SingletonConnectionException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                } catch (ProprietaireException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }
}
