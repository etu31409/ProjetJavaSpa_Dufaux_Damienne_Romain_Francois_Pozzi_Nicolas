package dataAcessPackage;

import exceptionPackage.MedicamentException;
import exceptionPackage.OrdonnanceException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Medicament;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IMedicament {
    ArrayList<Medicament> getMedicaments() throws MedicamentException, SingletonConnectionException;
    Medicament getUnMedicament(int identifiantMed)throws SingletonConnectionException, MedicamentException;
    String [][] getOrdonnancesEntreDeuxDates(GregorianCalendar dateDebutZoneRecherche, GregorianCalendar dateFinZoneRecherche)
            throws SingletonConnectionException, OrdonnanceException, MedicamentException;
}
