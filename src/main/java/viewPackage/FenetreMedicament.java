package viewPackage;

import controllerPackage.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class FenetreMedicament extends JFrame {
    private Controller controller;
    private Container frameContainer ;
    private JPanel panneauMedicament;

    public FenetreMedicament(){
        super("Ajout d'un médicament");
        setBounds(500, 100, 330, 372);
        panneauMedicament = new PanneauMedicament(controller).getPanneauContainerPrincipal();
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

    public void fermerFenetre(){

    }

}
