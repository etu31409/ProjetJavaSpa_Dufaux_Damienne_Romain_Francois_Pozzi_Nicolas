package dataAcessPackage;

import exceptionPackage.AnimalException;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Animal;
import modelPackage.Medicament;
import modelPackage.Veterinaire;

import java.util.ArrayList;

public interface IAnimal {

    //get
    ArrayList<Animal> getAnimaux() throws AnimalException, SingletonConnectionException;
    Animal getUnAnimal(Integer numRegistre) throws SingletonConnectionException, AnimalException;
    ArrayList<Animal>getAnimauxTries(String critere) throws AnimalException, SingletonConnectionException;

    //recherches
    ArrayList<Animal> getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            SingletonConnectionException;
    ArrayList<Animal> getResultatRecherchAnimauxMedicamentVeto(Medicament selectionMedicament, Veterinaire selectionVeterinaire)
            throws AnimalException, SingletonConnectionException;
    ArrayList<Animal> getResultatRecherchAnimauxMedicament(Medicament selectionMedicament) throws AnimalException,
            SingletonConnectionException;

    //ajouts
    void ajouterAnimal(Animal animal) throws AnimalException, SingletonConnectionException;

    //supression
    void supprimerAnimal(Animal animal) throws  AnimalException, SingletonConnectionException;

    //modification
    void modifierAnimal(Animal animal) throws  AnimalException, SingletonConnectionException;
}
