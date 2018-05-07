package dataAcessPackage;

import exceptionPackage.*;
import exceptionPackage.MedicamentException;
import modelPackage.Medicament;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DBDAOMedicament implements IMedicament {
    private Connection connectionUnique;
    private String sqlInstruction;

    private ResultSet data;

    public ArrayList<Medicament> getMedicaments() throws MedicamentException, SingletonConnectionException {
        try {

            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select * from spabd.medicament";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            ArrayList<Medicament> tousLesMedicaments = new ArrayList<Medicament>();
            while (data.next()) {
                Medicament medicament = new Medicament();

                medicament.setIdentifiantMed(data.getInt("identifiantMed"));
                medicament.setStockage(data.getString("stockage"));
                medicament.setDosage(data.getString("dosage"));
                medicament.setNomMedic(data.getString("nomMedic"));
                tousLesMedicaments.add(medicament);
            }

            return tousLesMedicaments;

        } catch (SQLException e) {
            throw new MedicamentException();
        }
    }

    public Medicament getUnMedicament(int identifiantMed)throws SingletonConnectionException, MedicamentException{
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select * from spabd.medicament where identifiantMed = ?";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, identifiantMed);
            data = statement.executeQuery();

            Medicament medicament = new Medicament();
            GregorianCalendar datePreparation = new GregorianCalendar();

            while (data.next()) {
                medicament.setIdentifiantMed(data.getInt("identifiantMed"));
                medicament.setStockage(data.getString("stockage"));
                medicament.setDosage(data.getString("dosage"));
                medicament.setNomMedic(data.getString("nomMedic"));
            }
            return medicament;
        }
        catch (SQLException e) {
            throw new MedicamentException();
        }

    }

    public void ajouterMedicament(Medicament medicament) throws MedicamentException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "insert into animal values (?, ?, ?);";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);

        } catch (SQLException e) {
            throw new MedicamentException("Erreur lors de l'insertion du médicament !");
        }
    }

    public String [][] getOrdonnancesEntreDeuxDates(GregorianCalendar dateDebutZoneRecherche, GregorianCalendar dateFinZoneRecherche)
            throws SingletonConnectionException, OrdonnanceException, MedicamentException{
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select count(*)" +
                    "from spabd.ordonnance\n" +
                    "inner join spabd.medicaments\n" +
                    "on (spabd.ordonnance.identifiantMed = spabd.medicament.identifiantMed)\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.ordonnance.numSoin = spabd.soinAvance.numSoin)\n" +
                    "where spabd.ordonnance.dateOrdonnance between ? and ?";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setDate(1, new Date(dateDebutZoneRecherche.getTimeInMillis()));
            statement.setDate(2, new Date(dateFinZoneRecherche.getTimeInMillis()));
            data = statement.executeQuery();
            data.next();
            Integer nombreDeLignes = data.getInt(1);

            String[][] listeResultatRechercheOrdonnances = new String[nombreDeLignes][];

            sqlInstruction = "select spabd.medicament.nom, spabd.soinAvance.dateSoin, " +
                    "from spabd.ordonnance\n" +
                    "inner join spabd.medicaments\n" +
                    "on (spabd.ordonnance.identifiantMed = spabd.medicament.identifiantMed)\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.ordonnance.numSoin = spabd.soinAvance.numSoin)\n" +
                    "where spabd.ordonnance.dateOrdonnance between ? and ?";

            statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setDate(1, new Date(dateDebutZoneRecherche.getTimeInMillis()));
            statement.setDate(2, new Date(dateFinZoneRecherche.getTimeInMillis()));
            data = statement.executeQuery();
            int i = 0;

            while (data.next()) {
                listeResultatRechercheOrdonnances[i] = new String[3];
                listeResultatRechercheOrdonnances[i][0] = data.getString(1);
                listeResultatRechercheOrdonnances[i][1] = data.getDate(2).toString();
                i++;
            }
            return listeResultatRechercheOrdonnances;

        } catch (SQLException e) {
            throw new OrdonnanceException("Erreur lors de la récupération du résultat de la recherche des medicaments" +
                    " préscrits entre deux dates");
        }
    }
}

