package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.Ordonnance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DBDAOOrdonnance implements IOrdonnance{
    private Connection connectionUnique;
    private String sqlInstruction;

    private ResultSet data;

    public ArrayList<Ordonnance> getOrdonnances() throws OrdonnanceException, SingletonConnectionException,
            MedicamentException, AnimalException, ProprietaireException, VeterinaireException, SoinException {
        try {

            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select * from spabd.ordonnance";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            ArrayList<Ordonnance> toutesLesOrdonnances = new ArrayList<Ordonnance>();
            GregorianCalendar dateOrdonnance = new GregorianCalendar();

            while (data.next()) {
                Ordonnance ordonnance = new Ordonnance();
                ISoinAvance soinAvance = new DBDAOSoinAvance();
                ordonnance.setSoinAvance(soinAvance.getUnSoinAvance(data.getInt("numSoin")));
                IAnimal animal = new DBDAOAnimal();
                ordonnance.setAnimal(animal.getUnAnimal(data.getInt("numRegistre")));
                IMedicament medicament = new DBDAOMedicament();
                ordonnance.setMedicament(medicament.getUnMedicament(data.getInt("identifiantMed")));

                dateOrdonnance.setTime(data.getDate("dateOrdonnance"));
                ordonnance.setDateOrdonnance(dateOrdonnance);

                toutesLesOrdonnances.add(ordonnance);
            }

            connectionUnique.close();
            return toutesLesOrdonnances;
        }
        catch (SQLException e) {
            throw new OrdonnanceException();
        }
    }
}
