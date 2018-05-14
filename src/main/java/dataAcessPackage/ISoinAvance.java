package dataAcessPackage;

import exceptionPackage.AnimalException;
import exceptionPackage.ConnexionException;
import exceptionPackage.SoinException;
import exceptionPackage.VeterinaireException;
import modelPackage.SoinAnimalVeto;
import modelPackage.SoinAvance;

import java.util.ArrayList;

public interface ISoinAvance {

    //get
    SoinAvance getUnSoinAvance(Integer numSoin) throws SoinException, ConnexionException;
    ArrayList<SoinAnimalVeto> getSoinsTries(String critere) throws SoinException, ConnexionException, VeterinaireException, AnimalException;

    //ajout
    Integer ajouterFicheDeSoins (SoinAvance soinAvance)throws SoinException, ConnexionException;

    //Suppression
    void supprimerSoin(Integer soin) throws SoinException, ConnexionException;

    //Modification
    void modifierSoin(SoinAvance soin) throws SoinException, ConnexionException;
}
