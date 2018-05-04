package viewPackage;

import controllerPackage.Controller;
import modelPackage.Medicament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class FenetreMedicament extends JFrame {
    private Controller controller;
    private Container frameContainer ;
    private PanneauMedicament panneauMedicament;
    private Medicament medicament;

    public FenetreMedicament(){
        super("Ajout d'un médicament");
        setBounds(500, 100, 300, 400);
        panneauMedicament = new PanneauMedicament(controller).getPanneauContainerMedicament();
        /*frameContainer = getContentPane();
        frameContainer.setLayout(new BorderLayout());
        frameContainer.add(panneauMedicament, BorderLayout.CENTER);*/
        this.addWindowListener(new ClosingListener());
        setVisible(true);
    }
    public class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
    Medicament getMedicament(){
        return panneauMedicament.getMedicament();
    }
}
