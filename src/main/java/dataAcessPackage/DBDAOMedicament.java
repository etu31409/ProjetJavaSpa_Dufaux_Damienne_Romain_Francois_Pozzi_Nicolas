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

public class DBDAOMedicament {
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
                GregorianCalendar datePreparation = new GregorianCalendar();

                medicament.setIdentifiantMed(data.getInt("identifiantMed"));
                datePreparation.setTime( data.getDate("dateArrivee"));
                medicament.setDatePreparation(datePreparation);
                medicament.setStockage(data.getString("stockage"));
                medicament.setPosologie(data.getString("posologie"));
                medicament.setNomMedic(data.getString("nomMedic"));
                tousLesMedicaments.add(medicament);
            }

            connectionUnique.close();
            return tousLesMedicaments;

        } catch (SQLException e) {
            throw new MedicamentException();
        }
    }
}
