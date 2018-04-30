package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.sql.*;
import java.sql.Date;
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
                Veterinaire veterinaire = new Veterinaire();
                veterinaire.setIdentifiantVeto(data.getInt("identifiantVeto"));
                veterinaire.setNom(data.getString("nom"));
                veterinaire.setPrenom(data.getString("prenom"));

                tousLesVeterinaires.add(veterinaire);
            }
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
            GregorianCalendar dateSoin = new GregorianCalendar();
            GregorianCalendar heure = new GregorianCalendar();

            sqlInstruction = "select * from spabd.veterinaire where identifiantVeto = ?";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, identifiantVeto);
            data = statement.executeQuery();

            while (data.next()) {
                veterinaire.setIdentifiantVeto(data.getInt("identifiantVeto"));
                veterinaire.setNom(data.getString("nom"));
                veterinaire.setPrenom(data.getString("prenom"));
            }

            return veterinaire;
        }
        catch (SQLException e) {
            throw new VeterinaireException();
        }
    }

    public String[][] getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut,
               GregorianCalendar dateFin) throws SingletonConnectionException, VeterinaireException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select count(*)" +
                    "from spabd.veterinaire\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto)\n" +
                    "inner join spabd.ordonnance\n" +
                    "on (spabd.soinAvance.numRegistre = spabd.ordonnance.numRegistre)" +
                    "where spabd.ordonnance.dateOrdonnance between ? and ?";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setDate(1, new Date(dateDebut.getTimeInMillis()));
            statement.setDate(2, new Date(dateFin.getTimeInMillis()));
            data = statement.executeQuery();
            data.next();
            Integer nombreDeLignes = data.getInt(1);

            String[][] listeResultatRechercheParDate = new String[nombreDeLignes][];

            sqlInstruction = "select spabd.veterinaire.identifiantVeto, spabd.veterinaire.nom, " +
                    "spabd.ordonnance.dateOrdonnance\n" +
                    "from spabd.veterinaire\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto)\n" +
                    "inner join spabd.ordonnance\n" +
                    "on (spabd.soinAvance.numRegistre = spabd.ordonnance.numRegistre)" +
                    "where spabd.ordonnance.dateOrdonnance between ? and ?";

            statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setDate(1, new Date(dateDebut.getTimeInMillis()));
            statement.setDate(2, new Date(dateFin.getTimeInMillis()));
            data = statement.executeQuery();
            int i = 0;

            while (data.next()) {
                listeResultatRechercheParDate[i] = new String[3];
                listeResultatRechercheParDate[i][0] = Integer.toString(data.getInt(1));
                listeResultatRechercheParDate[i][1] = data.getString(2);
                listeResultatRechercheParDate[i][2] = data.getDate(3).toString();
                i++;
            }
            return listeResultatRechercheParDate;

        } catch (SQLException e) {
            throw new VeterinaireException("Erreur lors de la récupération du résultat de la recherche des vétérinaires" +
                    " en fonction de deux dates");
        }
    }
}
