package businessPackage;

import dataAcessPackage.*;
import exceptionPackage.AnimalException;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Animal;

import java.util.ArrayList;

public class Business {
    IAnimal daoAnimal;

    public Business() {
        daoAnimal = new DBDAOAnimal();
    }

    public ArrayList<Animal>getAnimaux() throws AnimalException, SingletonConnectionException, ProprietaireException {
        return daoAnimal.getAnimaux();
    }

    public ArrayList<String> getIdentifiantsAnimaux() throws AnimalException, SingletonConnectionException, ProprietaireException {
        ArrayList<String> identifiantsAnimaux = new ArrayList();
        for(Animal a :daoAnimal.getAnimaux()){
            identifiantsAnimaux.add(a.getNom() +" " + a.getNumRegistre());
        }
        return identifiantsAnimaux;
    }

    //tache metier
}
