package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DBDAOVeterinaire implements IVeterinaire{
    private Connection connectionUnique;
    private String sqlInstruction;

    private ResultSet data;

    public  ArrayList<Veterinaire> getVeterinaires() throws VeterinaireException, SingletonConnectionException {
        try {

            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select * from spabd.veterinaire";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            ArrayList<Veterinaire>  tousLesVeterinaires = new ArrayList<Veterinaire>();
            while (data.next()) {
                Veterinaire veteriniare = new Veterinaire();
                veteriniare.setIdentifiantVeto(data.getInt("identifiantVeto"));
                veteriniare.setNom(data.getString("nom"));
                veteriniare.setPrenom(data.getString("prenom"));

                tousLesVeterinaires.add(veteriniare);
            }

            //connectionUnique.close();
            return tousLesVeterinaires;

        } catch (SQLException e) {
            throw new VeterinaireException();
        }
    }

    public Veterinaire getUnVeterinaire(Integer identifiantVeto) throws SingletonConnectionException, VeterinaireException {

        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            Veterinaire veterinaire = new Veterinaire();

            sqlInstruction = "select * from spabd.veterinaire where identifiantVeto = ?";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, identifiantVeto);
            data = statement.executeQuery();

            while (data.next()) {
                veterinaire.setIdentifiantVeto(data.getInt("identifiantVeto"));
                veterinaire.setNom(data.getString("nom"));
                veterinaire.setPrenom(data.getString("prenom"));
            }

            connectionUnique.close();
            return veterinaire;
        }
        catch (SQLException e) {
            throw new VeterinaireException();
        }
    }
}
