package dataAcessPackage;

import exceptionPackage.SingletonConnectionException;
import exceptionPackage.SoinException;
import exceptionPackage.VeterinaireException;
import modelPackage.SoinAvance;

import java.util.ArrayList;

public interface ISoinAvance {

    //get
    ArrayList<SoinAvance> getSoinsAvances() throws SoinException, SingletonConnectionException, VeterinaireException;
    SoinAvance getUnSoinAvance(Integer numSoin) throws SoinException;
    ArrayList<SoinAvance> getSoinsTries(String critere) throws SoinException, SingletonConnectionException, VeterinaireException;

    //ajout
    void ajouterFicheDeSoins (SoinAvance soinAvance)throws SoinException, SingletonConnectionException;

    //Suppression
    void supprimerSoin(SoinAvance soin) throws SoinException;
}
