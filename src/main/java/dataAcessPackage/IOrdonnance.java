package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.Medicament;
import modelPackage.Ordonnance;
import modelPackage.SoinAvance;

public interface IOrdonnance {

    //ajout
    void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, ConnexionException;

    //suppression
    void supprimerOrdonnance(SoinAvance soin, Medicament medicament) throws OrdonnanceException, ConnexionException;

    //modification
    void modifierOrdonnances(SoinAvance nouveauSoin) throws OrdonnanceException, ConnexionException;
}
