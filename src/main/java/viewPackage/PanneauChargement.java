package viewPackage;

import java.awt.BorderLayout;
import javax.swing.*;

public class PanneauChargement extends JPanel {
    private GestionChargement g;
    private JProgressBar barreDeChargement;
    private JPanel container;

    public PanneauChargement(JPanel panneauACharger, FenetrePrincipale fenetrePrincipale){

        barreDeChargement = new JProgressBar();
        barreDeChargement.setValue(0);
        barreDeChargement.setStringPainted(true);
        barreDeChargement.setVisible(true);

        setLayout(new BorderLayout());
        add(barreDeChargement, BorderLayout.CENTER);
        repaint();

        g = new GestionChargement(barreDeChargement, this, fenetrePrincipale);
    }

    public GestionChargement getG()
    {
        return g;
    }
}