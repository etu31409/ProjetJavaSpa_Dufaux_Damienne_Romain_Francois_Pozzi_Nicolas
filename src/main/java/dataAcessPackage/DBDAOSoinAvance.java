package dataAcessPackage;

import exceptionPackage.SingletonConnectionException;
import exceptionPackage.SoinException;
import exceptionPackage.VeterinaireException;
import modelPackage.SoinAvance;
import modelPackage.Veterinaire;

import java.sql.*;
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

    public SoinAvance getUnSoinAvance(Integer numSoin) throws SoinException {

        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            GregorianCalendar dateSoin = new GregorianCalendar();
            SoinAvance soin = new SoinAvance();
            sqlInstruction = "select * from spabd.soinAvance where numRegistre = ?";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, numSoin);
            data = statement.executeQuery();
            while (data.next()) {
                soin.setNumSoin(data.getInt("numSoin"));
                soin.setNumRegistre(data.getInt("numRegistre"));
                soin.setRemarque(data.getString("remarque"));
                soin.setEstUrgent(data.getBoolean("estUrgent"));
                soin.setPartieDuCorps(data.getString("partieDuCorps"));
                soin.setVeterinaire(data.getInt("identifiantVeto"));
                dateSoin.setTime(data.getDate("dateSoin"));
                soin.setDateSoin(dateSoin);
                soin.setIntitule(data.getString("intitule"));
            }
            return soin;
        } catch (Exception e) {
            throw new SoinException("Erreur lors de la récupération d'un soin");
        }
    }

    public ArrayList<SoinAvance> getSoinsTries(String critere) throws SoinException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            ArrayList<SoinAvance> tousLesSoinsTries = new ArrayList<>();

            String critereColonne;
            if(critere.equals("Aucun tri"))
                critereColonne = "\"\"";
            else if (critere.equals("Date du soin"))
                critereColonne = "dateSoin";
            else if (critere.equals("Identifiant du vétérinaire"))
                critereColonne = "identifiantVeto";
            else if (critere.equals("Identifiant de l'animal"))
                critereColonne = "numRegistre";
            else
                critereColonne = "numSoin";

            sqlInstruction = "select * from spabd.soinAvance order by "+ critereColonne + " asc;";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();
            GregorianCalendar dateSoin = new GregorianCalendar();

            while (data.next()) {
                SoinAvance soin = new SoinAvance();
                soin.setNumSoin(data.getInt("numSoin"));
                soin.setNumRegistre(data.getInt("numRegistre"));
                soin.setRemarque(data.getString("remarque"));
                soin.setEstUrgent(data.getBoolean("estUrgent"));
                soin.setPartieDuCorps(data.getString("partieDuCorps"));
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

            sqlInstruction = "insert into spabd.soinavance(numSoin,numRegistre, intitule, partieDuCorps, dateSoin, identifiantVeto, estUrgent, remarque) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            int numSoin = 34; //TODO
            /*data.next();
            String chaine = data.getString("numSoin");
            System.out.println(chaine);
            numSoin = Integer.parseInt(chaine);*/

            preparedStatement.setInt(1, numSoin);
            preparedStatement.setInt(2,soinAvance.getNumRegistre());
            preparedStatement.setString(3,soinAvance.getIntitule());
            preparedStatement.setString(4,soinAvance.getPartieDuCorps());
            preparedStatement.setDate(5,new java.sql.Date(soinAvance.getDateSoin().getTimeInMillis()));
            preparedStatement.setInt(6,soinAvance.getVeterinaire());
            preparedStatement.setBoolean(7,soinAvance.getEstUrgent());
            preparedStatement.setString(8,soinAvance.getRemarque());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SoinException("Erreur lors de l'insertion d'un soin avancé");
        }
    }

    public void supprimerSoin(SoinAvance soin) throws SoinException{
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            //Recuperer toutes les ordonnaces qui sont liés à la fiche de soins
            sqlInstruction = "select * from ordonnance where numSoin = ?";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,soin.getNumSoin());
            data = preparedStatement.executeQuery();
            // Supprimer les ordonnances liées à la fiche de soin
            sqlInstruction = "delete from ordonnance where numSoin = ? and numRegistre = ? and identifiantMed = ?";
            preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            while (data.next()) {
                preparedStatement.setInt(1,soin.getNumSoin());
                preparedStatement.setInt(2,soin.getNumRegistre());
                preparedStatement.setInt(3,data.getInt("identifiantMed"));
                preparedStatement.executeUpdate();
            }
            //Supprimer la fiche de soins
            sqlInstruction = "delete from soinavance where numSoin = ?";
            preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, soin.getNumSoin());
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception){throw new SoinException(exception.getMessage());}
        catch(Exception exception){throw new SoinException("Erreur lors de la suppression de la fiche de soins");}
    }

}
