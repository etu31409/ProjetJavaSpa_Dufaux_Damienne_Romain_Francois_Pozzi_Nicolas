package dataAcessPackage;

import exceptionPackage.ConnexionException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Veterinaire;
import modelPackage.VeterinaireOrdonnance;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IVeterinaire {

    //get
    ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, ConnexionException;
    Veterinaire getUnVeterinaire(Integer identifiantVeto) throws ConnexionException, VeterinaireException;

    //recherche
    ArrayList<VeterinaireOrdonnance> getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut, GregorianCalendar dateFin)
            throws ConnexionException, VeterinaireException;
}
