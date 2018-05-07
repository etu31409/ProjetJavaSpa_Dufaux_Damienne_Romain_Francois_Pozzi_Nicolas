package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.Ordonnance;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IOrdonnance {
    ArrayList<Ordonnance> getOrdonnances() throws OrdonnanceException, SingletonConnectionException,
            MedicamentException, AnimalException, ProprietaireException, VeterinaireException, SoinException;
    void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, SingletonConnectionException;

}
