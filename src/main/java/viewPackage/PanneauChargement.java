package viewPackage;

import java.awt.*;
import javax.swing.*;

public class PanneauChargement extends JPanel {
    private GestionChargement g;
    private JProgressBar barreDeChargement;
    private JPanel container;

    public PanneauChargement(JPanel panneauACharger, FenetrePrincipale fenetrePrincipale){

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