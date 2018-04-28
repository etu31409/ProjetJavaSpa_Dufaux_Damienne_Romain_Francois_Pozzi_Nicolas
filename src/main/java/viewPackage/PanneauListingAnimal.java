package viewPackage;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauListingAnimal extends JPanel {
    private JPanel panneauRecherche;
    private JPanel panneauBoutons;
    private JButton retour;
    private JLabel intituleLabel, videLabel;

    public PanneauListingAnimal() {
        this.setLayout(new BorderLayout());
        panneauBoutons = new JPanel();
        panneauRecherche = new JPanel();
        this.add(panneauRecherche, BorderLayout.NORTH);
        this.add(panneauBoutons, BorderLayout.SOUTH);
        panneauRecherche.setLayout(new GridLayout(1, 2, 5, 5));

        videLabel = new JLabel("");

        intituleLabel = new JLabel("<html><h1>Liste de tous les animaux :</h1></html>");
        intituleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panneauRecherche.add(intituleLabel);
        panneauRecherche.add(videLabel);

        panneauBoutons.setLayout(new FlowLayout());
        retour = new JButton("Retour");
        retour.addActionListener(new EcouteurBouton());
        panneauBoutons.add(retour);
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}
