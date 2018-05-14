package dataAcessPackage;

import exceptionPackage.ConnexionException;
import exceptionPackage.MedicamentException;
import modelPackage.Medicament;
import modelPackage.StatMedicament;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IMedicament {

    //get
    ArrayList<Medicament> getMedicaments() throws MedicamentException, ConnexionException;
    Medicament getUnMedicament(int identifiantMed)throws MedicamentException, ConnexionException;

    ArrayList<Medicament> getMedicamentsDeLaFiche(Integer numSoin) throws MedicamentException, ConnexionException;

    //ajout
    void ajouterMedicament(Medicament medicament) throws MedicamentException, ConnexionException;

    //tâche métier
    ArrayList<StatMedicament> getMedicamentsEntreDeuxDates(GregorianCalendar dateDebutZoneRecherche, GregorianCalendar dateFinZoneRecherche)
            throws ConnexionException, MedicamentException;

}
