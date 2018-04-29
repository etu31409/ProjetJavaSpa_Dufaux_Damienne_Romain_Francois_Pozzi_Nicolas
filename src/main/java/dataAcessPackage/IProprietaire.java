package dataAcessPackage;

import exceptionPackage.ProprietaireException;
import exceptionPackage.SingletonConnectionException;
import modelPackage.Proprietaire;
import modelPackage.Veterinaire;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProprietaire {
    ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException;
    Proprietaire getUnProprietaire(Integer identifiantProprietaire) throws SingletonConnectionException, SQLException, ProprietaireException;
    Proprietaire getUnProprietaire(Integer identifiantProprietaire, boolean fermerConnexion) throws SingletonConnectionException, SQLException, ProprietaireException;

    String[][] getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws ProprietaireException, SingletonConnectionException;
}
