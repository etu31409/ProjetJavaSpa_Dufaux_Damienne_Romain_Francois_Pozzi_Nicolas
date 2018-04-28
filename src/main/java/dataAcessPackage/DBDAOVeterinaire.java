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

    public ArrayList<Veterinaire> getIdentifiantsVeterinaires()throws VeterinaireException, SingletonConnectionException{
        return null;
    }
}
