package viewPackage;

import controllerPackage.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauRechercheAnimaux{


    private JCheckBox veterinairesCheckBox;
    private JCheckBox medicamentsCheckBox;
    private JButton rechercherButton;
    private JComboBox veterinairesComboBox;
    private JComboBox medicamentsComboBox;
    private JLabel titreFacteurRecherche;
    private JPanel panneauListeRecherche;
    private JScrollPane scrollPane;
    private JPanel panneauContainerPrincipal;
    private Controller controller;

    public PanneauRechercheAnimaux(Controller controller){
        this.controller = controller;
        rechercherButton.addActionListener(new RechercheListener());
        titreFacteurRecherche = new JLabel("Aucune recherche sélectionnée");
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class RechercheListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == rechercherButton){
                String titrefacteur ="";
                if (veterinairesCheckBox.isSelected() && !medicamentsCheckBox.isSelected()){

                }
                else if(!veterinairesCheckBox.isSelected() && medicamentsCheckBox.isSelected()){

                }
                else if (veterinairesCheckBox.isSelected() && medicamentsCheckBox.isSelected()){

                    //controller.getResultatRecherchAnimauxMedicamentVeto();
                }
            }
        }
    }
}
