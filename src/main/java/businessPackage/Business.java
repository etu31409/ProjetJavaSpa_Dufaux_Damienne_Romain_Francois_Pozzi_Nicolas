package businessPackage;

import dataAcessPackage.*;
import exceptionPackage.AnimalException;
import modelPackage.Animal;

import java.util.ArrayList;

public class Business {
    DAO dao;

    public Business() {
        dao = new DBDAO();
    }

    /*public Animal getUnAnimal(){
        return dao.getUnAnimal();
    }*/

    public ArrayList<Animal>getAnimaux()throws AnimalException{
        return dao.getAnimaux();
    }

    public ArrayList<String> getIdentifiantsAnimaux()throws AnimalException{
        ArrayList<String> identifiantsAnimaux = new ArrayList();
        for(Animal a :dao.getAnimaux()){
            identifiantsAnimaux.add(a.getNom() +" " + a.getNumRegistre());
        }
        return identifiantsAnimaux;
    }

    //tache metier
}
