package dataAcessPackage;

import exceptionPackage.ConnexionException;
import exceptionPackage.ProprietaireException;
import modelPackage.Proprietaire;
import modelPackage.ProprietaireAnimal;
import modelPackage.Veterinaire;

import java.util.ArrayList;

public interface IProprietaire {

    //get
    ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, ConnexionException;

    //recherche
    ArrayList<ProprietaireAnimal> getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws ProprietaireException,
            ConnexionException;

    //ajout
    void ajouterNouveauProprio(Proprietaire proprietaire)throws ConnexionException, ProprietaireException;
}
