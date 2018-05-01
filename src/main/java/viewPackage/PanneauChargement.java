package viewPackage;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class PanneauChargement extends JPanel {
    private JLabel message;
    private JProgressBar barreDeChargement;
    private GestionChargement g;

    public PanneauChargement(JPanel panneauACharger){

        //message = new JLabel("Traitement de la requête");
        barreDeChargement = new JProgressBar();
        barreDeChargement.setValue(0);
        barreDeChargement.setStringPainted(true);

        setLayout(new BorderLayout());
        //add(message, BorderLayout.NORTH);
        add(barreDeChargement, BorderLayout.CENTER);

        g = new GestionChargement(barreDeChargement, panneauACharger, this);
    }

    public GestionChargement getG()
    {
        return g;
    }
}