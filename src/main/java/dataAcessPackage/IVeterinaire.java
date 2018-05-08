package dataAcessPackage;

import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Veterinaire;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IVeterinaire {

    //get
    ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, SingletonConnectionException;
    Veterinaire getUnVeterinaire(Integer identifiantVeto) throws SingletonConnectionException, VeterinaireException;

    //recherche
    ArrayList<Veterinaire> getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut, GregorianCalendar dateFin)
            throws SingletonConnectionException, VeterinaireException;
}
