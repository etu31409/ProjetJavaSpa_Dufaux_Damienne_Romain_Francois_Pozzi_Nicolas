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
                IVeterinaire veterinaire = new DBDAOVeterinaire();
                soin.setVeterinaire(veterinaire.getUnVeterinaire(data.getInt("identifiantVeto")));
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
                IVeterinaire veterinaire = new DBDAOVeterinaire();
                soin.setVeterinaire(veterinaire.getUnVeterinaire(data.getInt("identifiantVeto")));
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

    public String[][] getSoinsTries(String critere) throws SoinException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select count(*) from spabd.soinAvance";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();
            data.next();
            Integer nombreDeLignes = data.getInt(1);
            String[][] tousLesSoinsTries = new String[nombreDeLignes][];
            if (critere.equals("")){
                critere = "\"\"";
            }
            sqlInstruction = "select numSoin, numRegistre, intitule, partieDuCorps, dateSoin, identifiantVeto, estUrgent, remarque " +
                    "from spabd.soinAvance order by "+ critere + " asc;";
            statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            int i = 0;
            while (data.next()) {
                tousLesSoinsTries[i] = new String[8];
                tousLesSoinsTries[i][0] = Integer.toString(data.getInt("numSoin"));
                tousLesSoinsTries[i][1] = Integer.toString(data.getInt("numRegistre"));
                tousLesSoinsTries[i][2] = data.getString("intitule");
                tousLesSoinsTries[i][3] = data.getString("partieDuCorps");
                tousLesSoinsTries[i][4] = data.getDate("dateSoin").toString();
                tousLesSoinsTries[i][5] = Integer.toString(data.getInt("identifiantVeto"));
                tousLesSoinsTries[i][6] = Boolean.toString(data.getBoolean("estUrgent"));
                tousLesSoinsTries[i][7] = data.getString("remarque");
                i++;
            }
            return tousLesSoinsTries;
        } catch (SQLException e) {
            throw new SoinException();
        }
    }
}
