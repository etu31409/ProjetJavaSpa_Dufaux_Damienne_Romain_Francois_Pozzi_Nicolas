package dataAcessPackage;

import exceptionPackage.SingletonConnectionException;
import exceptionPackage.SoinException;
import exceptionPackage.VeterinaireException;
import modelPackage.SoinAvance;
import modelPackage.Veterinaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class DBDAOSoinAvance implements ISoinAvance {
    private Connection connectionUnique;
    private String sqlInstruction;
    private ResultSet data;

    //get
    public ArrayList<SoinAvance> getSoinsAvances() throws SoinException, SingletonConnectionException, VeterinaireException {
        try {

            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select * from spabd.soinAvance";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            ArrayList<SoinAvance> tousLesSoins = new ArrayList<SoinAvance>();
            GregorianCalendar dateSoin = new GregorianCalendar();

            while (data.next()) {
                SoinAvance soin = new SoinAvance();
                soin.setNumRegistre(data.getInt("numSoin"));
                soin.setNumRegistre(data.getInt("numRegistre"));
                soin.setIntitule(data.getString("intitule"));
                soin.setPartieDuCorps(data.getString("partieDuCorps"));
                dateSoin.setTime(data.getDate("dateSoin"));
                soin.setDateSoin(dateSoin);
                soin.setVeterinaire(data.getInt("identifiantVeto"));
                soin.setEstUrgent(data.getBoolean("estUrgent"));

                String remarque = data.getString("remarque");
                if (!data.wasNull()) {
                    soin.setRemarque(remarque);
                }
                tousLesSoins.add(soin);
            }

            connectionUnique.close();
            return tousLesSoins;

        } catch (SQLException e) {
            throw new SoinException();
        }
    }

    public SoinAvance getUnSoinAvance(Integer numSoin) throws SingletonConnectionException, SoinException, VeterinaireException {

        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            SoinAvance soin = new SoinAvance();
            GregorianCalendar dateSoin = new GregorianCalendar();

            sqlInstruction = "select * from spabd.soinAvance where numSoin = ?";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, numSoin);
            data = statement.executeQuery();

            while (data.next()) {
                soin.setNumRegistre(data.getInt("numSoin"));
                soin.setNumRegistre(data.getInt("numRegistre"));
                soin.setIntitule(data.getString("intitule"));
                soin.setPartieDuCorps(data.getString("partieDuCorps"));
                dateSoin.setTime(data.getDate("dateSoin"));
                soin.setDateSoin(dateSoin);
                soin.setVeterinaire(data.getInt("identifiantVeto"));
                soin.setEstUrgent(data.getBoolean("estUrgent"));

                String remarque = data.getString("remarque");
                if (!data.wasNull()) {
                    soin.setRemarque(remarque);
                }
            }
            return soin;
        } catch (SQLException e) {
            throw new SoinException();
        }
    }

    public ArrayList<SoinAvance> getSoinsTries(String critere) throws SoinException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            ArrayList<SoinAvance> tousLesSoinsTries = new ArrayList<>();

            if (critere.equals("")){
                critere = "\"\"";
            }
            sqlInstruction = "select * from spabd.soinAvance order by "+ critere + " asc;";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();
            GregorianCalendar dateSoin = new GregorianCalendar();

            while (data.next()) {
                SoinAvance soin = new SoinAvance();
                soin.setNumRegistre(data.getInt("numRegistre"));
                soin.setRemarque(data.getString("remarque"));
                soin.setEstUrgent(data.getBoolean("estUrgent"));
                soin.setPartieDuCorps(data.getString("parttieDuCorps"));
                soin.setVeterinaire(data.getInt("identifiantVeto"));
                dateSoin.setTime(data.getDate("dateSoin"));
                soin.setDateSoin(dateSoin);
                soin.setIntitule(data.getString("intitule"));
                tousLesSoinsTries.add(soin);
            }
            return tousLesSoinsTries;
        } catch (SQLException e) {
            throw new SoinException();
        }
    }

    //ajout
    public void ajouterFicheDeSoins (SoinAvance soinAvance)throws SoinException, SingletonConnectionException{
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "select max(?) from spabd.soinavance;";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setString(1,"numSoin");
            data = preparedStatement.executeQuery();

            sqlInstruction = "insert into spabd.soinavance(numSoin,numRegistre, intitule, partieDuCorps, dateSoin, heure, identifiantVeto, estUrgent, remarque) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,data.getInt(0));
            preparedStatement.setInt(2,soinAvance.getNumRegistre());
            preparedStatement.setString(3,soinAvance.getIntitule());
            preparedStatement.setString(4,soinAvance.getPartieDuCorps());
            preparedStatement.setDate(5,new java.sql.Date(soinAvance.getDateSoin().getTimeInMillis()));
            preparedStatement.setInt(6,soinAvance.getVeterinaire());
            preparedStatement.setBoolean(7,soinAvance.getEstUrgent());
            preparedStatement.setString(8,soinAvance.getRemarque());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SoinException("Erreur lors de l'insertion d'un soin avancé !");
        }
    }
}
