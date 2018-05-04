package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauBienvenue{
    private JLabel texteAccueil;
    private JLabel baniere;
    private ImageIcon image;
    private JPanel panneauContainerPrincipal;

    public PanneauBienvenue() {
    }

    public JPanel getPanneauContainerPrincipal(){
        return panneauContainerPrincipal;
    }
}
