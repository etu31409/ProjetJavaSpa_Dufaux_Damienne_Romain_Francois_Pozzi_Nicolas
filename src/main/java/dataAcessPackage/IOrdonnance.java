package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.Medicament;
import modelPackage.Ordonnance;
import modelPackage.SoinAvance;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IOrdonnance {

    //get
    /*ArrayList<Ordonnance> getOrdonnances(Integer ficheDeSoin) throws OrdonnanceException, SingletonConnectionException,
            MedicamentException, AnimalException, SoinException;*/

    //ajout
    void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, ConnexionException;

    //suppression
    void supprimerOrdonnance(SoinAvance soin, Medicament medicament) throws OrdonnanceException, ConnexionException;
}
