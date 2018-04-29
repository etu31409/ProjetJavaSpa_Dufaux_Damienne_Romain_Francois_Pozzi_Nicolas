package dataAcessPackage;

import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Veterinaire;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IVeterinaire {
    ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, SingletonConnectionException;
    Veterinaire getUnVeterinaire(Integer identifiantVeto) throws SingletonConnectionException, VeterinaireException;
    String[][] getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut,
                GregorianCalendar dateFin) throws SingletonConnectionException, VeterinaireException;
}
