package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.*;
import modelPackage.modelJointure.VeterinaireSoinAvanceOrdonnanceRecherche;

import java.sql.*;
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

    public ArrayList<VeterinaireSoinAvanceOrdonnanceRecherche> getResultatRechercheVeterinaireDate(GregorianCalendar dateDebut,
               GregorianCalendar dateFin) throws SingletonConnectionException, VeterinaireException, OrdonnanceException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            ArrayList<VeterinaireSoinAvanceOrdonnanceRecherche> resultatRechercheParDate = new ArrayList<VeterinaireSoinAvanceOrdonnanceRecherche>();

            sqlInstruction = "select spabd.veterinaire.identifiantVeto, spabd.veterinaire.nom, " +
                    "spabd.ordonnance.dateOrdonnance\n" +
                    "from spabd.veterinaire\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto)\n" +
                    "inner join spabd.ordonnance\n" +
                    "on (spabd.soinAvance.numRegistre = spabd.ordonnance.numRegistre)" +
                    "where spabd.ordonnance.dateOrdonnance between ? and ?";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);

            statement.setTime(1, (Time) dateDebut.getTime());
            statement.setTime(2, (Time) dateFin.getTime());
            data = statement.executeQuery();

            while (data.next()) {
                VeterinaireSoinAvanceOrdonnanceRecherche vso = new VeterinaireSoinAvanceOrdonnanceRecherche();
                vso.setIdVeterinaire(data.getInt("identifiantVeto"));
                vso.setNomVeterinaire(data.getString("nom"));
                GregorianCalendar date = new GregorianCalendar();
                date.setTime(data.getDate("dateOrdonnance"));
                vso.setDateOrdonnance(date);

                resultatRechercheParDate.add(vso);
            }
            return resultatRechercheParDate;

        } catch (SQLException e) {
            throw new VeterinaireException("Erreur lors de la récupération du résultat de la recherche");
        }
    }
}
