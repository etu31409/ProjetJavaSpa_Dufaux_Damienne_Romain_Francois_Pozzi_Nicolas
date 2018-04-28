package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DBDAOProprietaire implements IProprietaire{
    private Connection connectionUnique;
    private String sqlInstruction;

    private ResultSet data;

    public  ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException {
        try {
            /*String rue;
            String numero;
            String localite;
            Integer codePostal;
            String pays;*/

            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select * from spabd.proprietaire";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            ArrayList<Proprietaire> tousLesProprietaires = new ArrayList<Proprietaire>();
            while (data.next()) {
                Proprietaire proprietaire = new Proprietaire();
                proprietaire.setIdentifiantProprio(data.getInt("identifiantProprio"));
                proprietaire.setNom(data.getString("nom"));
                proprietaire.setPrenom(data.getString("prenom"));

                tousLesProprietaires.add(proprietaire);
            }

            //connectionUnique.close();
            return tousLesProprietaires;

        } catch (SQLException e) {
            throw new ProprietaireException();
        }
    }

    public Proprietaire getUnProprietaire(Integer identifiantProprietaire) throws SingletonConnectionException, ProprietaireException
    {
       return getUnProprietaire(identifiantProprietaire, true);
    }

    public Proprietaire getUnProprietaire(Integer identifiantProprietaire, boolean fermerConnexion) throws SingletonConnectionException, ProprietaireException {

        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            Proprietaire proprietaire = new Proprietaire();

            sqlInstruction = "select * from spabd.proprietaire where identifiantProprio = ?";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, identifiantProprietaire);
            data = statement.executeQuery();

            while (data.next()) {
                proprietaire.setIdentifiantProprio(data.getInt("identifiantProprio"));
                proprietaire.setNom(data.getString("nom"));
                proprietaire.setPrenom(data.getString("prenom"));
            }
            if (fermerConnexion) {
                //connectionUnique.close();
            }
            return proprietaire;
        }
        catch (SQLException e) {
            throw new ProprietaireException();
        }
    }
}
