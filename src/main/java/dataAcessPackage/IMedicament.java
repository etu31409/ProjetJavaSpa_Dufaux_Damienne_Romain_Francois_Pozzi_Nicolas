package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.Medicament;

import java.util.ArrayList;

public interface IMedicament {
    ArrayList<Medicament> getMedicaments() throws MedicamentException, SingletonConnectionException;
}
