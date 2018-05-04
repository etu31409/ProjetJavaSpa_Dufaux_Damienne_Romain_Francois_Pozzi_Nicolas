package viewPackage;

import controllerPackage.Controller;

import java.awt.event.ActionEvent;
import modelPackage.Medicament;
import javax.swing.*;
import java.awt.event.ActionListener;

public class PanneauMedicament{

    private FenetreMedicament fenetreMedicament;
    private Controller controller;
    private Medicament medicament;

    private JPanel panneauContainerPrincipal;

    private JButton buttonAnnuler;
    private JButton buttonValider;
    private JTextField textFieldNom;
    private JTextField textFieldStockage;
    private JTextField textFieldDosage;

    public PanneauMedicament(Controller controller) {
        this.controller = controller;
        buttonAnnuler.addActionListener(new ButtonListener());
        buttonValider.addActionListener(new ButtonListener());
    }

    public JPanel getPanneauContainerPrincipal() {
        return panneauContainerPrincipal;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            fenetreMedicament.dispose();
            if (event.getSource() == buttonValider){
                validationFormulaire();
            }
            else if (event.getSource() == buttonAnnuler){
                fenetreMedicament = new FenetreMedicament();
            }
        }
    }

    public void validationFormulaire(){
        //try{

        String nomMedic = textFieldNom.getText();
        String stockageMedic = textFieldStockage.getText();
        String dosageMedic = textFieldDosage.getText();
        Medicament medicament = new Medicament(nomMedic, stockageMedic, dosageMedic);

        /*}
        catch(MedicamentException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }*/
    }

    public Medicament getMedicament(){
        return medicament;
    }
}
