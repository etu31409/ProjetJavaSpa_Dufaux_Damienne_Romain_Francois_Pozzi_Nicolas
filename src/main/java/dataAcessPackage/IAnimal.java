package dataAcessPackage;

import exceptionPackage.AnimalException;
import exceptionPackage.ConnexionException;
import exceptionPackage.ProprietaireException;
import modelPackage.Animal;
import modelPackage.AnimalProprietaire;
import modelPackage.Medicament;
import modelPackage.Veterinaire;

import java.util.ArrayList;

public interface IAnimal {

    //get
    ArrayList<Animal> getAnimaux() throws AnimalException, ConnexionException;
    Animal getUnAnimal(Integer numRegistre) throws ConnexionException, AnimalException;
    ArrayList<AnimalProprietaire>getAnimauxTries(String critere) throws AnimalException, ConnexionException, ProprietaireException;

    //recherches
    ArrayList<Animal> getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            ConnexionException;
    ArrayList<Animal> getResultatRecherchAnimauxMedicamentVeto(Medicament selectionMedicament, Veterinaire selectionVeterinaire)
            throws AnimalException, ConnexionException;
    ArrayList<Animal> getResultatRecherchAnimauxMedicament(Medicament selectionMedicament) throws AnimalException,
            ConnexionException;

    //ajouts
    void ajouterAnimal(Animal animal) throws AnimalException, ConnexionException;

    //supression
    void supprimerAnimal(Integer animal) throws  AnimalException, ConnexionException;

    //modification
    void modifierAnimal(Animal animal) throws  AnimalException, ConnexionException;
}
