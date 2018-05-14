package dataAcessPackage;

import exceptionPackage.MedicamentException;
import exceptionPackage.OrdonnanceException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Medicament;
import modelPackage.StatMedicament;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IMedicament {

    //get
    ArrayList<Medicament> getMedicaments() throws MedicamentException, SingletonConnectionException;
    Medicament getUnMedicament(int identifiantMed)throws MedicamentException;

    ArrayList<Medicament> getMedicamentsDeLaFiche(Integer numSoin) throws MedicamentException;

    //ajout
    void ajouterMedicament(Medicament medicament) throws MedicamentException, SingletonConnectionException;

    //tâche métier
    ArrayList<StatMedicament> getMedicamentsEntreDeuxDates(GregorianCalendar dateDebutZoneRecherche, GregorianCalendar dateFinZoneRecherche)
            throws SingletonConnectionException, MedicamentException;

}
