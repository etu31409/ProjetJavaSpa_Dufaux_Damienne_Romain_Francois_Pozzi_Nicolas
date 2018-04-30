package viewPackage;
import modelPackage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FenetrePrescription extends JFrame {
    //private Animal animal;
    private Container frameContainer;
    private PanneauPrescription panneauPrescription;

    public FenetrePrescription() {
        super("Ajouter une nouvelle prescription");
        setBounds(300, 0, 800, 825);
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        panneauPrescription = new PanneauPrescription(this);
        frameContainer.add(panneauPrescription, BorderLayout.CENTER);
        this.addWindowListener(new ClosingListener());
        setVisible(true);
    }

    public class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
}
