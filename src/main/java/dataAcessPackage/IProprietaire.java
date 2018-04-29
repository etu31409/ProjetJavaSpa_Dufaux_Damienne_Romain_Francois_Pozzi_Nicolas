package dataAcessPackage;

import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Proprietaire;
import modelPackage.Veterinaire;
import modelPackage.modelJointure.AnimalProprietaireRecherche;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProprietaire {
    ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException;
    Proprietaire getUnProprietaire(Integer identifiantProprietaire) throws SingletonConnectionException, SQLException, ProprietaireException;
    Proprietaire getUnProprietaire(Integer identifiantProprietaire, boolean fermerConnexion) throws SingletonConnectionException, SQLException, ProprietaireException;

    ArrayList<AnimalProprietaireRecherche> getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws ProprietaireException, SingletonConnectionException;
}
