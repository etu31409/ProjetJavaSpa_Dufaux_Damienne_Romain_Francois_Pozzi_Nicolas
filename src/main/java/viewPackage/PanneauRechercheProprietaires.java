package viewPackage;

import controllerPackage.Controller;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Veterinaire;
import modelPackage.modelJointure.AnimalProprietaireRecherche;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanneauRechercheProprietaires extends JPanel {

    private Controller controller;
    private JPanel panneauRecherche, panneauListe, rechercheContainer;
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

        titreRechProprietaire = new JLabel("<html><h1>Recherche des propriétaire selon un vétérinaire</h1></html>");
        titreRechProprietaire.setHorizontalAlignment(SwingConstants.CENTER);

        rechercheContainer = new JPanel();
        rechercheContainer.setLayout(new GridLayout(1, 2));

        this.add(titreRechProprietaire, BorderLayout.NORTH);
        this.add(rechercheContainer, BorderLayout.CENTER);

        panneauRecherche.setLayout(new GridLayout(25, 1));
        rechercheContainer.add(panneauRecherche);
        panneauListe.setLayout(new GridLayout(2, 1));
        LayoutManager test = panneauListe.getLayout();
        rechercheContainer.add(panneauListe);


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
        }
        catch (VeterinaireException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        catch (SingletonConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private class rechercheListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == boutonRecherche) {
                try {
                    Veterinaire selectionVeterinaire = (Veterinaire)listeVeterinaire.getSelectedItem();
                    String[][] list = controller.getResultatRechercheProprietaire(selectionVeterinaire);

                    String[] columnNames = {"Identifiant de l'animal","Nom de l'animal","Identifiant du propriétaire", "Nom du propriétaire"};
                    resultatRecherche = new JTable(list, columnNames);
                    jScrollpane = new JScrollPane (resultatRecherche);
                    panneauListe.add(jScrollpane, BorderLayout.CENTER);
                    panneauListe.repaint();

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
