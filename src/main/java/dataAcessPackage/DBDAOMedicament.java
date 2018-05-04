package dataAcessPackage;

import exceptionPackage.*;
import exceptionPackage.MedicamentException;
import modelPackage.Medicament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}

