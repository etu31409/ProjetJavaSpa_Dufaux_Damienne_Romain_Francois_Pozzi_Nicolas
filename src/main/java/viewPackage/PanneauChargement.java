package viewPackage;

import java.awt.BorderLayout;
import javax.swing.*;

public class PanneauChargement extends JPanel {
    private JLabel message;
    private JProgressBar barreDeChargement;
    private GestionChargement g;

    public PanneauChargement(JPanel panneauACharger, FenetrePrincipale fenetrePrincipale){

        //message = new JLabel("Traitement de la requête");
        barreDeChargement = new JProgressBar();
        barreDeChargement.setValue(0);
        barreDeChargement.setStringPainted(true);
        barreDeChargement.setVisible(true);

        setLayout(new BorderLayout());
        //add(message, BorderLayout.NORTH);
        add(barreDeChargement, BorderLayout.CENTER);
        repaint();

        g = new GestionChargement(barreDeChargement, this, fenetrePrincipale);
    }

    public GestionChargement getG()
    {
        return g;
    }
}