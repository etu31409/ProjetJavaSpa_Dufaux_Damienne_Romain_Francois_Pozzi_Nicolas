package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.Ordonnance;

import java.util.ArrayList;

public interface IOrdonnance {
    public ArrayList<Ordonnance> getOrdonnances() throws OrdonnanceException, SingletonConnectionException, MedicamentException, AnimalException, ProprietaireException, VeterinaireException, SoinException;
}
