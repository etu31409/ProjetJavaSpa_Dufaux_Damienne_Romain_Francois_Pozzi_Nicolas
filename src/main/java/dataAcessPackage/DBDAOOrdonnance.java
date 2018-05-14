package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.Medicament;
import modelPackage.Ordonnance;
import modelPackage.SoinAvance;

import java.sql.*;

public class DBDAOOrdonnance implements IOrdonnance {
    private Connection connectionUnique;
    private String sqlInstruction;

    //ajout
    public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException, ConnexionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "insert into spabd.ordonnance(numRegistre, numSoin, identifiantMed) values (?, ?, ?);";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, ordonnance.getNumRegistre());
            preparedStatement.setInt(2, ordonnance.getSoinAvance());
            preparedStatement.setInt(3, ordonnance.getMedicament());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new OrdonnanceException("Problème lors de l'insertion de l'ordonnance");
        }
    }

    //suppression
    public void supprimerOrdonnance(SoinAvance soin, Medicament medicament) throws OrdonnanceException, ConnexionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "delete from spabd.Ordonnance where numRegistre = ? and numSoin = ? and identifiantMed = ?;";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, soin.getNumRegistre());
            preparedStatement.setInt(2, soin.getNumSoin());
            preparedStatement.setInt(3, medicament.getIdentifiantMed());

            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
                throw new OrdonnanceException("Problème lors de la suppression de l'ordonnance");
        }
    }
}