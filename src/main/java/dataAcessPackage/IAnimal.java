package dataAcessPackage;

import exceptionPackage.AnimalException;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Animal;
import modelPackage.Medicament;
import modelPackage.Veterinaire;

import java.util.ArrayList;

public interface IAnimal {
    ArrayList<Animal> getAnimaux() throws AnimalException, SingletonConnectionException;
    Animal getUnAnimal(Integer numRegistre) throws SingletonConnectionException, AnimalException;
    ArrayList<Animal> getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            SingletonConnectionException;
    ArrayList<Animal> getResultatRecherchAnimauxMedicamentVeto(Medicament selectionMedicament, Veterinaire selectionVeterinaire)
            throws AnimalException, SingletonConnectionException;
    ArrayList<Animal> getResultatRecherchAnimauxMedicament(Medicament selectionMedicament) throws AnimalException,
            SingletonConnectionException;
    void ajouterAnimal(Animal animal) throws AnimalException, SingletonConnectionException;
    ArrayList<Animal>getAnimauxTries(String critere) throws AnimalException, SingletonConnectionException;
}
