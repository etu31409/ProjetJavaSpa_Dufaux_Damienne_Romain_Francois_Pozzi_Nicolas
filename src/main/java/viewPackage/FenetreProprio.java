package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreProprio extends JFrame {
    private Container frameContainer ;
    private PanneauProprio panneauProprio;
    public FenetreProprio(){
        super("Ajout d'un propriétaire");
        setBounds(300, 100, 500, 500);
        panneauProprio = new PanneauProprio(this);
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