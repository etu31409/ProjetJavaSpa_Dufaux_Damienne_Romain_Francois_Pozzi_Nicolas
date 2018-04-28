package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauBienvenue extends JPanel {
    private JLabel texteAccueil;
    private JLabel baniere;
    private ImageIcon image;

    public PanneauBienvenue() {
        texteAccueil = new JLabel("<html><h1>Bienvenue ! </h1></html>");
        texteAccueil.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLayout(new BorderLayout());
        this.add(texteAccueil, BorderLayout.CENTER);

        image = new ImageIcon("E:/cropped-photo-couverture-facebookb.jpg");
        baniere = new JLabel(image);
        this.add(baniere, BorderLayout.NORTH);
    }
}
