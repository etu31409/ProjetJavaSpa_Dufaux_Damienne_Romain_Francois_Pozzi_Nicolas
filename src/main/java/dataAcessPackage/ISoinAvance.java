package dataAcessPackage;

import exceptionPackage.ConnexionException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.SoinException;
import exceptionPackage.VeterinaireException;
import modelPackage.SoinAvance;

import java.util.ArrayList;

public interface ISoinAvance {

    //get
    ArrayList<SoinAvance> getSoinsAvances() throws SoinException, ConnexionException, VeterinaireException;
    SoinAvance getUnSoinAvance(Integer numRegistre) throws SoinException, ConnexionException;
    ArrayList<SoinAvance> getSoinsTries(String critere) throws SoinException, ConnexionException, VeterinaireException;

    //ajout
    Integer ajouterFicheDeSoins (SoinAvance soinAvance)throws SoinException, ConnexionException;

    //Suppression
    void supprimerSoin(SoinAvance soin) throws SoinException, ConnexionException;

    //Modification
    void modifierSoin(SoinAvance soin) throws SoinException, ConnexionException;
}
