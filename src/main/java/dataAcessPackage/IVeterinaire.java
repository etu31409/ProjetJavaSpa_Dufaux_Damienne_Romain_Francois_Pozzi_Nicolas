package dataAcessPackage;

import exceptionPackage.SingletonConnectionException;
import exceptionPackage.VeterinaireException;
import modelPackage.Veterinaire;

import java.util.ArrayList;

public interface IVeterinaire {
    ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, SingletonConnectionException;
    Veterinaire getUnVeterinaire(Integer identifiantVeto) throws SingletonConnectionException, VeterinaireException;
}
