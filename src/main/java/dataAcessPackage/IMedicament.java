package dataAcessPackage;

import exceptionPackage.MedicamentException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Medicament;
import java.util.ArrayList;

public interface IMedicament {
    ArrayList<Medicament> getMedicaments() throws MedicamentException, SingletonConnectionException;
}
