package dataAcessPackage;

import exceptionPackage.SingletonConnectionException;
import exceptionPackage.SoinException;
import exceptionPackage.VeterinaireException;
import modelPackage.SoinAvance;

import java.util.ArrayList;

public interface ISoinAvance {
    ArrayList<SoinAvance> getSoinsAvances() throws SoinException, SingletonConnectionException, VeterinaireException;
    SoinAvance getUnSoinAvance(Integer numSoin) throws SingletonConnectionException, SoinException, VeterinaireException;
}
