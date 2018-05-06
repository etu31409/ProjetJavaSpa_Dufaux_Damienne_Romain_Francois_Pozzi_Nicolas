package viewPackage;

import controllerPackage.Controller;
import java.awt.*;
import java.awt.event.ActionEvent;

import exceptionPackage.MedicamentException;
import modelPackage.Medicament;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionListener;

public class PanneauMedicament{

    private Controller controller;

    private FenetreMedicament fenetreMedicament;
    private PanneauFicheDeSoin panneauFicheDeSoin;
    private JPanel panneauContainerPrincipal;

    private JButton buttonAnnuler;
    private JButton buttonValider;
    private JTextField textFieldNom;
    private JTextField textFieldStockage;
    private JTextField textFieldDosage;

    public PanneauMedicament(Controller controller, FenetreMedicament fenetreMedicament, PanneauFicheDeSoin panneauFicheDeSoin) {
        this.controller = controller;
        this.fenetreMedicament = fenetreMedicament;
        this.panneauFicheDeSoin = panneauFicheDeSoin;

        buttonAnnuler.addActionListener(new ButtonListener());
        buttonValider.addActionListener(new ButtonListener());
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private Boolean validationFormulaire() {
        Boolean estValide = true;
        if (textFieldNom.getText().equals("")) {
            estValide = false;
            Border border = BorderFactory.createLineBorder(Color.red);
            textFieldNom.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }
        if (textFieldStockage.getText().equals("")) {
            estValide = false;
            Border border = BorderFactory.createLineBorder(Color.red);
            textFieldStockage.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }
        if (textFieldDosage.getText().equals("")) {
            estValide = false;
            Border border = BorderFactory.createLineBorder(Color.red);
            textFieldDosage.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }
        return estValide;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            fenetreMedicament.dispose();
            if (event.getSource() == buttonValider){
                try {
                    textFieldNom.setBorder(null);
                    textFieldStockage.setBorder(null);
                    textFieldDosage.setBorder(null);
                    if (validationFormulaire()) {
                        Medicament medicament = new Medicament(textFieldStockage.getText(), textFieldDosage.getText(), textFieldNom.getText());
                        //controller.ajouterNouveauMedicament(medicament); //TODO
                        JOptionPane.showMessageDialog(null, "La fiche de soin a été correctement ajoutée à la base de données !");
                        panneauFicheDeSoin.ajouterMedicamentAListeMedicamentsDispos(medicament);
                        fenetreMedicament.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Certains champs obligatoires ne sont pas remplis !");
                    }
                }catch (MedicamentException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }

            }
            else if (event.getSource() == buttonAnnuler){
                fenetreMedicament.dispose();
            }
        }
    }
}
