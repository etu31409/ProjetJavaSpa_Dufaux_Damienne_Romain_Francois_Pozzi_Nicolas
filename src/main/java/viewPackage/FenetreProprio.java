package viewPackage;

import controllerPackage.Controller;
import modelPackage.Proprietaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreProprio extends JFrame {
    private Container frameContainer ;
    private JPanel panneauProprio;

    public FenetreProprio(Controller controller, PanneauAnimal panneauAnimal){
        super("Ajout d'un propriétaire");
        setBounds(500, 100, 330, 272);
        panneauProprio = new PanneauProprio(controller, FenetreProprio.this, panneauAnimal).getPanneauContainerPrincipal();
        frameContainer = getContentPane();
        frameContainer.setLayout(new BorderLayout());
        frameContainer.add(panneauProprio, BorderLayout.CENTER);
        this.addWindowListener(new ClosingListener());
        setVisible(true);
    }

    public class ClosingListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }

}