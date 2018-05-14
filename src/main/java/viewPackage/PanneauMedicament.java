package viewPackage;

import controllerPackage.Controller;
import java.awt.*;
import java.awt.event.ActionEvent;

import exceptionPackage.MedicamentException;
import exceptionPackage.SingletonConnectionException;
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

        buttonAnnuler.addActionListener(new EcouteurBouton());
        buttonValider.addActionListener(new EcouteurBouton());
    }

    private Boolean validationFormulaire()throws MedicamentException {
        if (textFieldNom.getText().equals("") || sontDesChiffres(textFieldNom.getText())) {
            Border border = BorderFactory.createLineBorder(Color.red);
            textFieldNom.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            throw new MedicamentException("Nom invalide - Le nom doit être une chaine de caractères non vide !");
        }
        if (textFieldStockage.getText().equals("") || sontDesChiffres(textFieldStockage.getText())) {
            Border border = BorderFactory.createLineBorder(Color.red);
            textFieldStockage.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            throw new MedicamentException("Stockage invalide - Le stockage doit être une chaine de caractères non vide !");
        }
        if (textFieldDosage.getText().equals("") || sontDesChiffres(textFieldDosage.getText())) {
            Border border = BorderFactory.createLineBorder(Color.red);
            textFieldDosage.setBorder(BorderFactory.createCompoundBorder(border,
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            throw new MedicamentException("Dosage invalide - Le dosage doit être une chaine de caractères non vide !");
        }
        return true;
    }

    private Boolean sontDesChiffres(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e){
            return false;
        }return true;
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class EcouteurBouton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == buttonValider){
                try {
                    textFieldNom.setBorder(null);
                    textFieldStockage.setBorder(null);
                    textFieldDosage.setBorder(null);
                    if (validationFormulaire()) {
                        Medicament medicament = new Medicament(textFieldStockage.getText(), textFieldDosage.getText(), textFieldNom.getText());
                        controller.ajouterMedicament(medicament);
                        JOptionPane.showMessageDialog(null, "Le médicament a correctement été ajouté à la base de données !",
                                "Confirmation!", JOptionPane.INFORMATION_MESSAGE);
                        panneauFicheDeSoin.ajouterMedicamentAListeMedicamentsDispos(medicament);
                        fenetreMedicament.dispose();

                    }
                }catch (MedicamentException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }catch (SingletonConnectionException e){
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur !", JOptionPane.ERROR_MESSAGE);
                }

            }
            else if (event.getSource() == buttonAnnuler){
                fenetreMedicament.dispose();
            }
        }
    }
}
