package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.Ordonnance;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IOrdonnance {

    //get
    ArrayList<Ordonnance> getOrdonnances() throws OrdonnanceException, SingletonConnectionException,
            MedicamentException, AnimalException, ProprietaireException, VeterinaireException, SoinException;

    //ajout
    void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException;

}
