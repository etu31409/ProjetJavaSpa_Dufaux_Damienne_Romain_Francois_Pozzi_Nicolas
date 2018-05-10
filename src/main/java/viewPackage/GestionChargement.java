package viewPackage;

import javax.swing.*;
import java.awt.*;

public class GestionChargement extends Thread{
    private JProgressBar barre;
    private JPanel panneauChargement;
    private FenetrePrincipale fenetrePrincipale;

    public GestionChargement(JProgressBar barre, JPanel panneauCharge, FenetrePrincipale fenetrePrincipale) {
        this.barre = barre;
        this.panneauChargement = panneauCharge;
        this.fenetrePrincipale = fenetrePrincipale;
    }

    public void run() {
        for(int i = 0 ; i <= 100; i++) {
            try {
                String msg = "";
                Thread.sleep(15);

                msg += "Chargement de l'application...";
                barre.setValue(i);
                barre.setString(msg + i + " %");

                panneauChargement.repaint();
                panneauChargement.doLayout();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        fenetrePrincipale.afficherAccueil();
        fenetrePrincipale.getBarMenu().setVisible(true);
    }
}

