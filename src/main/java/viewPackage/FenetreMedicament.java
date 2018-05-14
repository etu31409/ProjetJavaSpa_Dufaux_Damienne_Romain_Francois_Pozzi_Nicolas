package viewPackage;

import controllerPackage.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FenetreMedicament extends JFrame {
    private Container frameContainer ;
    private JPanel panneauMedicament;

    public FenetreMedicament(Controller controller, PanneauFicheDeSoin panneauFicheDeSoin){
        super("Ajout d'un médicament");
        setBounds(500, 100, 360, 322);
        panneauMedicament = new PanneauMedicament(controller, FenetreMedicament.this, panneauFicheDeSoin).getPanneauContainerPrincipal();
        frameContainer = getContentPane();
        frameContainer.setLayout(new BorderLayout());
        frameContainer.add(panneauMedicament, BorderLayout.CENTER);
        frameContainer.repaint();
        frameContainer.validate();

        this.addWindowListener(new ClosingListener());
        setVisible(true);
    }
    public class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
}
