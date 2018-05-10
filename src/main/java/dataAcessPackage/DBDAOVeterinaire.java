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

    //get
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

    //recherche
    public ArrayList<VeterinaireOrdonnance> getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut, GregorianCalendar dateFin)
            throws SingletonConnectionException, VeterinaireException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            ArrayList<VeterinaireOrdonnance>listeResultatRecherche = new ArrayList<>();

            sqlInstruction = "select distinct spabd.veterinaire.identifiantVeto, spabd.veterinaire.prenom, spabd.veterinaire.nom, " +
                    "spabd.soinAvance.dateSoin\n" +
                    "from spabd.veterinaire\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto)\n" +
                    "inner join spabd.ordonnance\n" +
                    "on spabd.ordonnance.numSoin = spabd.soinAvance.numSoin\n" +
                    "where spabd.soinAvance.dateSoin between ? and ?";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setDate(1, new Date(dateDebut.getTimeInMillis()));
            statement.setDate(2, new Date(dateFin.getTimeInMillis()));
            data = statement.executeQuery();

            while (data.next()) {
                GregorianCalendar dateSoin = new GregorianCalendar();
                VeterinaireOrdonnance veto = new VeterinaireOrdonnance();
                veto.setIdentifiantVeto(data.getInt(1));
                veto.setPrenomVeto(data.getString(2));
                veto.setNomVeto(data.getString(3));
                dateSoin.setTime(data.getDate(4));
                veto.setDateOrdonnance(dateSoin);

                listeResultatRecherche.add(veto);
            }
            return listeResultatRecherche;

        } catch (SQLException e) {
            throw new VeterinaireException("Erreur lors de la récupération du résultat de la recherche des vétérinaires" +
                    " en fonction de deux dates");
        }
    }
}
