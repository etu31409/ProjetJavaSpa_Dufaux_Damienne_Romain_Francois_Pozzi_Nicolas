package viewPackage;

import javax.swing.*;

public class GestionChargement extends Thread{
    private JProgressBar barre;
    private JPanel panneauCharge;
    private FenetrePrincipale fenetrePrincipale;

    public GestionChargement(JProgressBar barre, JPanel panneauCharge, FenetrePrincipale fenetrePrincipale) {
        this.barre = barre;
        this.panneauCharge = panneauCharge;
        this.fenetrePrincipale = fenetrePrincipale;
    }

    public void run() {
        for(int i = 0 ; i <= 100; i++) {
            try {
                String msg = "";
                Thread.sleep(20);

                msg += "Chargement de l'application... ";

                barre.setValue(i);
                barre.setString(msg + i + " %");
                panneauCharge.repaint();
                panneauCharge.doLayout();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        fenetrePrincipale.afficherAccueil();
    }
}

