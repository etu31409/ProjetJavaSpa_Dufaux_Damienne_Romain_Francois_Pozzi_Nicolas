package viewPackage;

import javax.swing.*;

public class GestionChargement {
    private JProgressBar barre;
    private JFrame fen;
    private PanneauFicheDeSoins panFicheSoins;

    public GestionChargement(JProgressBar barre, PanneauFicheDeSoins panFicheSoins)
    {
        this.fen = fen;
        this.panFicheSoins = panFicheSoins;
    }

    public void run()
    {
        for(int i = 0 ; i <= 100; i++)
        {
            try
            {
                String msg = "";
                Thread.sleep(40);
                if(i<70)msg += "Envoi des requêtes... ";
                else msg = "Vérification... ";
                barre.setValue(i);
                barre.setString(msg + i + " %");
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        panFicheSoins.setVisible(true);
    }
}

