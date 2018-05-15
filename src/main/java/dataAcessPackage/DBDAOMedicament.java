package dataAcessPackage;

import exceptionPackage.*;
import exceptionPackage.MedicamentException;
import modelPackage.Medicament;
import modelPackage.StatMedicament;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DBDAOMedicament implements IMedicament {
    private Connection connectionUnique;
    private String sqlInstruction;
    private ResultSet data;

    //get
    public ArrayList<Medicament> getMedicaments() throws MedicamentException, ConnexionException {
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

    public ArrayList <Medicament> getMedicamentsDeLaFiche(Integer ficheDeSoin)throws MedicamentException, ConnexionException{
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "select * from spabd.medicament inner join spabd.ordonnance " +
                    "on (medicament.identifiantMed = ordonnance.identifiantMed) " +
                    "inner join spabd.soinAvance " +
                    "on (ordonnance.numSoin = soinAvance.numSoin) " +
                    "where ordonnance.numSoin = ?;";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, ficheDeSoin);
            data = statement.executeQuery();

            ArrayList<Medicament> listeMedicamensDeLaFicheDeSoin = new ArrayList<>();

            while (data.next()) {
                Medicament medicament = new Medicament();
                medicament.setIdentifiantMed(data.getInt("identifiantMed"));
                medicament.setStockage(data.getString("stockage"));
                medicament.setDosage(data.getString("dosage"));
                medicament.setNomMedic(data.getString("nomMedic"));
                listeMedicamensDeLaFicheDeSoin.add(medicament);
            }
            return listeMedicamensDeLaFicheDeSoin;
        }
        catch (SQLException e) {
            throw new MedicamentException("Impossible de récupérer un médicament dans la base de données");
        }
    }

    //ajout
    public void ajouterMedicament(Medicament medicament) throws MedicamentException, ConnexionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "insert into spabd.medicament(nomMedic, stockage, dosage) values (?, ?, ?);";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setString(1,medicament.getNomMedic());
            preparedStatement.setString(2,medicament.getDosage());
            preparedStatement.setString(3,medicament.getStockage());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new MedicamentException("Erreur lors de l'insertion du médicament !");
        }
    }

    //tâche metier
    public ArrayList<StatMedicament> getMedicamentsEntreDeuxDates(GregorianCalendar dateDebutZoneRecherche,
                                                                  GregorianCalendar dateFinZoneRecherche)
            throws ConnexionException, MedicamentException{
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            ArrayList<StatMedicament> listeResultatRechercheOrdonnances = new ArrayList<>();

            sqlInstruction = "select spabd.medicament.nomMedic \n" +
                    "from spabd.ordonnance \n" +
                    "inner join spabd.medicament \n" +
                    "on (spabd.ordonnance.identifiantMed = spabd.medicament.identifiantMed) \n" +
                    "inner join spabd.soinAvance \n" +
                    "on (spabd.ordonnance.numSoin = spabd.soinAvance.numSoin) \n" +
                    "where spabd.soinAvance.dateSoin between ? and ?";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setDate(1, new Date(dateDebutZoneRecherche.getTimeInMillis()));
            statement.setDate(2, new Date(dateFinZoneRecherche.getTimeInMillis()));
            data = statement.executeQuery();

            while (data.next()) {
                StatMedicament statMedic = new StatMedicament();
                statMedic.setNomMedic(data.getString(1));
                listeResultatRechercheOrdonnances.add(statMedic);
            }
            return listeResultatRechercheOrdonnances;

        } catch (SQLException e) {
            throw new MedicamentException("Erreur lors de la récupération du résultat de la recherche des medicaments" +
                    " préscrits entre deux dates");
        }
    }
}

