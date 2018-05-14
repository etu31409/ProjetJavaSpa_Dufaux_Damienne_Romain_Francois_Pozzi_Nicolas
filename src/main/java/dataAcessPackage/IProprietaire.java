package dataAcessPackage;

import exceptionPackage.ConnexionException;
import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Proprietaire;
import modelPackage.ProprietaireAnimal;
import modelPackage.Veterinaire;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProprietaire {

    //get
    ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, ConnexionException;
    public Proprietaire getUnProprietaire(Integer identifiantProprietaire) throws ConnexionException, ProprietaireException;

    //recherche
    ArrayList<ProprietaireAnimal> getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws ProprietaireException,
            ConnexionException;

    //ajout
    void ajouterNouveauProprio(Proprietaire proprietaire)throws ConnexionException, ProprietaireException;
}
