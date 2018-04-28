package dataAcessPackage;

import exceptionPackage.AnimalException;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Animal;
import modelPackage.Veterinaire;

import java.util.ArrayList;

public interface IAnimal {
    ArrayList<Animal> getAnimaux() throws AnimalException, SingletonConnectionException, SingletonConnectionException, ProprietaireException;
}
