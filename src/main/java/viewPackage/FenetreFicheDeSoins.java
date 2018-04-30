package viewPackage;
import controllerPackage.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FenetreFicheDeSoins extends JFrame {

    private Container frameContainer;
    private Controller controller;
    private PanneauFicheDeSoins panneauFicheDeSoins;

    public FenetreFicheDeSoins() {
        super("Ajouter une nouvelle prescription");
        setBounds(300, 0, 300, 600);
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        panneauFicheDeSoins = new PanneauFicheDeSoins(this, controller);
        frameContainer.add(panneauFicheDeSoins, BorderLayout.CENTER);
        this.addWindowListener(new ClosingListener());
        setVisible(true);
    }

    public class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }

}
