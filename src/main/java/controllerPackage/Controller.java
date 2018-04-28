package controllerPackage;

import businessPackage.Business;
import exceptionPackage.AnimalException;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Animal;

import java.util.ArrayList;

public class Controller {
    private Business business;

    public Controller(){
        business = new Business();
    }

    public ArrayList<Animal>getAnimaux() throws AnimalException, SingletonConnectionException, ProprietaireException {
        return business.getAnimaux();
    }

    public ArrayList<String> getIdentifiantsAnimaux() throws AnimalException, ProprietaireException, SingletonConnectionException {
        return business.getIdentifiantsAnimaux();
    }
}
