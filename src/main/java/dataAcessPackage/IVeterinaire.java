package dataAcessPackage;

import exceptionPackage.OrdonnanceException;
import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Veterinaire;
import modelPackage.modelJointure.VeterinaireSoinAvanceOrdonnanceRecherche;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IVeterinaire {
    ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, SingletonConnectionException;
    Veterinaire getUnVeterinaire(Integer identifiantVeto) throws SingletonConnectionException, VeterinaireException;
    String[][] getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut,
                GregorianCalendar dateFin) throws SingletonConnectionException, VeterinaireException;
}
