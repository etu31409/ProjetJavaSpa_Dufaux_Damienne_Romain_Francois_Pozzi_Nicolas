package controllerPackage;

import businessPackage.Business;
import exceptionPackage.AnimalException;
import modelPackage.Animal;

import java.util.ArrayList;

public class Controller {
    private Business business;

    public Controller(Business business){
        business = new Business();
    }

    /*public Animal getUnAnimal(){
        return business.getUnAnimal();
    }*/

    public ArrayList<Animal>getAnimaux()throws AnimalException {
        return business.getAnimaux();
    }
}
