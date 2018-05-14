package viewPackage;

import java.awt.*;
import javax.swing.*;

public class PanneauChargement extends JPanel {
    private GestionChargement g;
    private JProgressBar barreDeChargement;
    private JPanel container;

    public PanneauChargement(FenetrePrincipale fenetrePrincipale){

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