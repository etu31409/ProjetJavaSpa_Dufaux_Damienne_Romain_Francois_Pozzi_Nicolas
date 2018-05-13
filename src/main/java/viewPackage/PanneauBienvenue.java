package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauBienvenue extends JPanel{
    private JLabel texteAccueil;
    private JLabel baniere;
    private ImageIcon image;

    public PanneauBienvenue(){
        texteAccueil = new JLabel("<html><h1>Bienvenue dans votre outil de gestion de S.P.A. ! </h1></html>");
        texteAccueil.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLayout(new BorderLayout());
        this.add(texteAccueil, BorderLayout.CENTER);

        image = new ImageIcon("src\\resources\\kmy-animaux.png");
        baniere = new JLabel(image);
        this.add(baniere, BorderLayout.NORTH);
    }

    public JPanel getPanneauContainerPrincipal(){
        return this;
    }

}