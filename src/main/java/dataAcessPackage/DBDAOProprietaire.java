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

    @Override
    public String[][] getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws ProprietaireException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "select count(*)" +
                    "from spabd.animal\n" +
                    "inner join spabd.proprietaire\n" +
                    "on (spabd.animal.identifiantProprio = spabd.proprietaire.identifiantProprio)\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.soinAvance.numRegistre = spabd.animal.numRegistre)\n" +
                    "inner join spabd.veterinaire\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto)\n" +
                    "where spabd.veterinaire.identifiantVeto = ?;";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, selectionVeterinaire.getIdentifiantVeto());
            data = statement.executeQuery();
            data.next();
            Integer nombreDeLignes = data.getInt(1);

            String[][] listeResultatRecherche = new String[nombreDeLignes][];

            sqlInstruction = "select spabd.animal.numRegistre, spabd.animal.nom, spabd.proprietaire.identifiantProprio, spabd.proprietaire.nom\n" +
                    "from spabd.animal\n" +
                    "inner join spabd.proprietaire\n" +
                    "on (spabd.animal.identifiantProprio = spabd.proprietaire.identifiantProprio)\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.soinAvance.numRegistre = spabd.animal.numRegistre)\n" +
                    "inner join spabd.veterinaire\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto)\n" +
                    "where spabd.veterinaire.identifiantVeto = ?;";
            statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, selectionVeterinaire.getIdentifiantVeto());
            data = statement.executeQuery();
            int i = 0;
            while (data.next()) {
                listeResultatRecherche[i] = new String[4];
                listeResultatRecherche[i][0] =  Integer.toString(data.getInt(1));
                listeResultatRecherche[i][1] = data.getString(2);
                listeResultatRecherche[i][2] = Integer.toString(data.getInt(3));
                listeResultatRecherche[i][3] = data.getString(4);
                i++;
            }
            return listeResultatRecherche;
        }
        catch (SQLException e){
            throw new ProprietaireException("Erreur lors de la récupération de la recherche de propriétaire en fonction du vétérinaire!");
        }

    }

}
