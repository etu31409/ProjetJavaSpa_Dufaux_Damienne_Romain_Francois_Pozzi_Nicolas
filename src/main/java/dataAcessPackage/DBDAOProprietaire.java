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

    //get
    public  ArrayList<Proprietaire> getProprietaires() throws ProprietaireException, SingletonConnectionException {
        try {
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

            return tousLesProprietaires;

        } catch (SQLException e) {
            throw new ProprietaireException("Erreur lors de la récupération des propriétaires");
        }
    }

    public Proprietaire getUnProprietaire(Integer identifiantProprietaire)
            throws SingletonConnectionException, ProprietaireException {

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
            return proprietaire;
        }
        catch (SQLException e) {
            throw new ProprietaireException();
        }
        catch (SingletonConnectionException e){
            throw new SingletonConnectionException();
        }
    }

    //recherche
    public ArrayList<ProprietaireAnimal> getResultatRechercheProprietaire(Veterinaire selectionVeterinaire) throws ProprietaireException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            ArrayList<ProprietaireAnimal> listeResultatRecherche = new ArrayList<ProprietaireAnimal>();

            sqlInstruction = "select spabd.animal.numRegistre, spabd.animal.nom, spabd.proprietaire.identifiantProprio, \n" +
                    "spabd.proprietaire.nom\n" +
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

            while (data.next()) {
                ProprietaireAnimal proprietaireAnimal = new ProprietaireAnimal();
                proprietaireAnimal.setNumRegistreAnimal(data.getInt(1));
                proprietaireAnimal.setNomAnimal(data.getString(2));
                proprietaireAnimal.setIdentifiantProprio(data.getInt(3));
                proprietaireAnimal.setNomProprio(data.getString(4));
                listeResultatRecherche.add(proprietaireAnimal);
            }

            return listeResultatRecherche;
        } catch (SQLException e) {
            throw new ProprietaireException("Erreur lors de la récupération de la recherche de propriétaire en fonction du vétérinaire!");
        }
    }

    //ajout
    public void ajouterNouveauProprio(Proprietaire proprietaire) throws SingletonConnectionException, ProprietaireException{
        try {
            if (proprietaire != null && !proprietaire.getPrenom().isEmpty() && !proprietaire.getNom().isEmpty()) {
                if (connectionUnique == null) {
                    connectionUnique = SingletonConnection.getUniqueInstance();
                }
                sqlInstruction = "insert into spabd.proprietaire(nom, prenom) values (?, ?);";
                PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
                statement.setString(1, proprietaire.getNom());
                statement.setString(2, proprietaire.getPrenom());
                statement.executeUpdate();
            }
            else{
                throw new ProprietaireException("Les informations reçues semblent incorrectes...");
            }
        }catch(SQLException e){
            throw  new ProprietaireException("Erreur lors de l'insertion du nouveau propriétaire");
        }
    }
}
