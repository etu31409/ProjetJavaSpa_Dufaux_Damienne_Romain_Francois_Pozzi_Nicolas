package viewPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FenetreFicheDeSoins extends JFrame {
    //private Animal animal;
    private Container frameContainer;
    private PanneauFicheDeSoins panneauFicheDeSoins;

    public FenetreFicheDeSoins() {
        super("Ajouter une nouvelle prescription");
        setBounds(300, 0, 600, 600);
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        panneauFicheDeSoins = new PanneauFicheDeSoins(this);
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
