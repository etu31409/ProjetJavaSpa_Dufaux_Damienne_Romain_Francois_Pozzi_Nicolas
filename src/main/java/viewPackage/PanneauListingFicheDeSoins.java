package viewPackage;

import controllerPackage.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauListingFicheDeSoins extends JPanel {
    private Controller controller;
    private JPanel panneauRecherche;
    private JPanel panneauBoutons;
    private JButton retour, lister, reinnitialiser;
    private JLabel intituleLabel,rechercheNomLabel, rechercheIdLabel, videLabel;
    private JComboBox listeNomsAnimaux, listeIdentifiantsAnimaux;

    public PanneauListingFicheDeSoins() {
        this.setLayout(new BorderLayout());
        panneauBoutons = new JPanel();
        panneauRecherche = new JPanel();
        this.add(panneauRecherche, BorderLayout.NORTH);
        this.add(panneauBoutons, BorderLayout.SOUTH);
        //panneauRecherche.setLayout(new GridLayout(5, 2, 5, 5));
        panneauRecherche.setLayout(new FlowLayout());

        intituleLabel = new JLabel("<html><h1>Selectionner un animal :</h1></html>");
        intituleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panneauRecherche.add(intituleLabel);
        videLabel = new JLabel("");
        panneauRecherche.add(videLabel);

        rechercheIdLabel = new JLabel("<html><h2>Par nom :</h2></html>");
        rechercheIdLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panneauRecherche.add(rechercheIdLabel);
        videLabel = new JLabel("");
        panneauRecherche.add(videLabel);

        listeNomsAnimaux = new JComboBox();
        panneauRecherche.add(listeNomsAnimaux);
        videLabel = new JLabel("");
        panneauRecherche.add(videLabel);

        listeNomsAnimaux.addItem("Barney");

        rechercheNomLabel = new JLabel("<html><h2>Par identifiant :</h2></html>");
        rechercheNomLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panneauRecherche.add(rechercheNomLabel);
        videLabel = new JLabel("");
        panneauRecherche.add(videLabel);

        listeIdentifiantsAnimaux = new JComboBox();
        panneauRecherche.add(listeIdentifiantsAnimaux);
        videLabel = new JLabel("");
        panneauRecherche.add(videLabel);

        listeIdentifiantsAnimaux.addItem("001");

        panneauBoutons.setLayout(new FlowLayout());
        retour = new JButton("Retour");
        retour.addActionListener(new EcouteurBouton());
        panneauBoutons.add(retour);
        lister = new JButton("Lister les fiches de soins");
        lister.addActionListener(new EcouteurBouton());
        lister.addActionListener(new EcouteurBouton());
        panneauBoutons.add(lister);
        reinnitialiser = new JButton("Reinnitialiser");
        reinnitialiser.addActionListener(new EcouteurBouton());
        reinnitialiser.addActionListener(new EcouteurBouton());
        panneauBoutons.add(reinnitialiser);
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == reinnitialiser){
                panneauRecherche.repaint();
                panneauRecherche.validate();
            }
            if(e.getSource() == lister){

            }
        }
    }
}
