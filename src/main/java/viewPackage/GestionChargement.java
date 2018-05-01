package viewPackage;

import javax.swing.*;

public class GestionChargement {
    private JProgressBar barre;
    private JPanel panneauCharge, fen;

    public GestionChargement(JProgressBar barre, JPanel panneauCharge, JPanel fen)
    {
        this.barre = barre;
        this.panneauCharge = panneauCharge;
        this.fen = fen;
    }

    public void run()
    {
        for(int i = 0 ; i <= 100; i++)
        {
            try
            {
                String msg = "";
                Thread.sleep(20);

                msg += "Traitement de la requête... ";

                barre.setValue(i);
                barre.setString(msg + i + " %");
                panneauCharge.repaint();
                panneauCharge.doLayout();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        panneauCharge.setVisible(true);
    }
}

