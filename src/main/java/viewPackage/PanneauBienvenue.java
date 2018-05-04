package viewPackage;

import javax.swing.*;
import java.awt.*;

public class PanneauBienvenue extends JPanel {
    private JLabel texteAccueil;
    private JLabel baniere;
    private ImageIcon image;
    private JPanel panel1;

    public PanneauBienvenue() {
        texteAccueil = new JLabel("<html><h1>Bienvenue ! </h1></html>");
        texteAccueil.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLayout(new BorderLayout());
        this.add(texteAccueil, BorderLayout.CENTER);

        image = new ImageIcon("H:/slider 1 (1).png");
        baniere = new JLabel(image);
        this.add(baniere, BorderLayout.NORTH);
    }
}
