package dataAcessPackage;

import exceptionPackage.ConnexionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Veterinaire;
import modelPackage.VeterinaireOrdonnance;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IVeterinaire {

    //get
    ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, ConnexionException;

    //recherche
    ArrayList<VeterinaireOrdonnance> getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut, GregorianCalendar dateFin)
            throws ConnexionException, VeterinaireException;
}
