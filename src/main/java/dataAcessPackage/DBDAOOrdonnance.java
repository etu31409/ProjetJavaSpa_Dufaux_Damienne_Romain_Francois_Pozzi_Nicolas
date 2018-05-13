package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.Ordonnance;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DBDAOOrdonnance implements IOrdonnance{
    private Connection connectionUnique;
    private String sqlInstruction;
    private ResultSet data;

    //get
    public ArrayList<Ordonnance> getOrdonnances() throws OrdonnanceException, SingletonConnectionException,
            MedicamentException, AnimalException, SoinException {
        try {

            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select * from spabd.ordonnance";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            ArrayList<Ordonnance> toutesLesOrdonnances = new ArrayList<Ordonnance>();

            while (data.next()) {
                Ordonnance ordonnance = new Ordonnance();
                ISoinAvance soinAvance = new DBDAOSoinAvance();
                ordonnance.setSoinAvance(soinAvance.getUnSoinAvance(data.getInt("numSoin")).getNumSoin());
                IAnimal animal = new DBDAOAnimal();
                ordonnance.setNumRegistre(animal.getUnAnimal(data.getInt("numRegistre")).getNumRegistre());
                IMedicament medicament = new DBDAOMedicament();
                ordonnance.setMedicament(medicament.getUnMedicament(data.getInt("identifiantMed")).getIdentifiantMed());

                toutesLesOrdonnances.add(ordonnance);
            }
            return toutesLesOrdonnances;
        }
        catch (SQLException e) {
            throw new OrdonnanceException();
        }
    }

    //ajout
    public void ajouterOrdonnance(Ordonnance ordonnance) throws OrdonnanceException {
        try{
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "insert into spabd.ordonnance(numRegistre, numSoin, identifiantMed) values (?, ?, ?);";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,ordonnance.getNumRegistre());
            preparedStatement.setInt(2,ordonnance.getSoinAvance());
            preparedStatement.setInt(3, ordonnance.getMedicament());

            preparedStatement.executeUpdate();
        }catch (Exception e) {
            throw new OrdonnanceException("Problème lors de l'insertion de l'ordonnance");
        }
    }
}
