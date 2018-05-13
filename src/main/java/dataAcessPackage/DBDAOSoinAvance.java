package dataAcessPackage;

import exceptionPackage.SingletonConnectionException;
import exceptionPackage.SoinException;
import exceptionPackage.VeterinaireException;
import modelPackage.SoinAvance;

import java.sql.*;
import java.util.ArrayList;
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

            while (data.next()) {
                GregorianCalendar dateSoin = new GregorianCalendar();
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

    public SoinAvance getUnSoinAvance(Integer numRegistre) throws SoinException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            GregorianCalendar dateSoin = new GregorianCalendar();
            SoinAvance soin = new SoinAvance();
            sqlInstruction = "select * from spabd.soinAvance where numRegistre = ?";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, numRegistre);
            data = statement.executeQuery();
            while (data.next()) {
                soin.setNumSoin(data.getInt("numSoin"));
                soin.setNumRegistre(data.getInt("numRegistre"));
                soin.setIntitule(data.getString("intitule"));
                soin.setPartieDuCorps(data.getString("partieDuCorps"));
                soin.setVeterinaire(data.getInt("identifiantVeto"));
                soin.setEstUrgent(data.getBoolean("estUrgent"));
                dateSoin.setTime(data.getDate("dateSoin"));
                soin.setDateSoin(dateSoin);
                String remarque = data.getString("remarque");
                if(!data.wasNull()){
                    soin.setRemarque(remarque);
                }
            }
            return soin;
        }

        catch (Exception e) {
            throw new SoinException("Erreur lors de la récupération d'un soin");
        }
    }

    public ArrayList<SoinAvance> getSoinsTries(String critere) throws SoinException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            ArrayList<SoinAvance> tousLesSoinsTries = new ArrayList<>();

            String ordre = "asc";
            String critereColonne;
            if(critere.equals("Aucun tri"))
                critereColonne = "\"\"";
            else if (critere.equals("Date du soin")){
                critereColonne = "dateSoin";
                ordre = "desc";
            }
            else if (critere.equals("Identifiant du vétérinaire"))
                critereColonne = "identifiantVeto";
            else if (critere.equals("Identifiant de l'animal"))
                critereColonne = "numRegistre";
            else
                critereColonne = "numSoin";

            sqlInstruction = "select * from spabd.soinAvance order by "+ critereColonne + " " + ordre + ";";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            while (data.next()) {
                GregorianCalendar dateSoin = new GregorianCalendar();
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

    //ajout/suppression/modification
    public Integer ajouterFicheDeSoins (SoinAvance soinAvance)throws SoinException, SingletonConnectionException{
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select max(numSoin) as maximum from spabd.soinavance;";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            data = preparedStatement.executeQuery();
            data.next();
            Integer numSoinMax = data.getInt("maximum");

            sqlInstruction = "insert into spabd.soinavance(numSoin,numRegistre, intitule, partieDuCorps, dateSoin, " +
                    "identifiantVeto, estUrgent, remarque) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connectionUnique.prepareStatement(sqlInstruction);

            preparedStatement.setInt(1, (++numSoinMax));
            preparedStatement.setInt(2, soinAvance.getNumRegistre());
            preparedStatement.setString(3, soinAvance.getIntitule());
            preparedStatement.setString(4, soinAvance.getPartieDuCorps());
            preparedStatement.setDate(5, new java.sql.Date(soinAvance.getDateSoin().getTimeInMillis()));
            preparedStatement.setInt(6, soinAvance.getVeterinaire());
            preparedStatement.setBoolean(7, soinAvance.getEstUrgent());
            if(soinAvance.getRemarque() != null){
                preparedStatement.setString(8, soinAvance.getRemarque());
            }
            else{
                preparedStatement.setNull(8, Types.VARCHAR);
            }
            preparedStatement.executeUpdate();
            return numSoinMax;

        } catch (SQLException e) {
            throw new SoinException("Erreur lors de l'insertion d'un soin avancé");
        }
    }

    public void supprimerSoin(SoinAvance soin) throws SoinException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            //Recuperer toutes les ordonnaces qui sont liées à la fiche de soins
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
        }catch (SQLException e) {
            throw new SoinException("Erreur lors de la suppression du soin !");
        }
    }

    public void modifierSoin(SoinAvance soin) throws SoinException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "update soinavance set numRegistre = ?, intitule = ?, partieDuCorps = ?," +
                    "dateSoin = ?, identifiantVeto = ?, estUrgent = ?, remarque = ? where numSoin = ?";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,soin.getNumRegistre());
            preparedStatement.setString(2,soin.getIntitule());
            preparedStatement.setString(3,soin.getPartieDuCorps());
            preparedStatement.setDate(4,new java.sql.Date(soin.getDateSoin().getTimeInMillis()));
            preparedStatement.setInt(5,soin.getVeterinaire());
            preparedStatement.setBoolean(6,soin.getEstUrgent());
            if(soin.getRemarque() != null){
                preparedStatement.setString(7,soin.getRemarque());
            }
            else{
                preparedStatement.setNull(7, Types.VARCHAR);
            }
            preparedStatement.setInt(8, soin.getNumSoin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
                throw new SoinException("Erreur lors de la modification de la fiche de soins");
        }
    }

}
