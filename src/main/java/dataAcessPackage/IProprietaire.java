package dataAcessPackage;

import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Proprietaire;
import modelPackage.ProprietaireAnimal;
import modelPackage.Veterinaire;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProprietaire {

    //get
    ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException;
    Proprietaire getUnProprietaire(Integer identifiantProprietaire)
            throws SingletonConnectionException, ProprietaireException;

    //recherche
    ArrayList<ProprietaireAnimal> getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws ProprietaireException,
            SingletonConnectionException;

    //ajout
    void ajouterNouveauProprio(Proprietaire proprietaire)throws SingletonConnectionException, ProprietaireException;
}
