package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.sql.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.*;

public class DBDAOAnimal implements IAnimal {

    private Connection connectionUnique;
    private String sqlInstruction;
    private ResultSet data;

    public ArrayList<Animal> getAnimaux() throws AnimalException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select * from spabd.animal";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            ArrayList<Animal> tousLesAnimaux = new ArrayList<Animal>();
            while (data.next()) {

                Animal animal = new Animal();
                GregorianCalendar dateArrivee = new GregorianCalendar();

                animal.setNumRegistre(data.getInt("numRegistre"));
                dateArrivee.setTime(data.getDate("dateArrivee"));
                animal.setDateArrivee(dateArrivee);
                animal.setEspece(data.getString("espece"));
                animal.setRace(data.getString("race"));
                animal.setSexe(data.getString("sexe"));
                animal.setEstSterilise(data.getBoolean("estSterilise"));
                animal.setCouleurDePeau(data.getString("couleurDePeau"));
                animal.setPoids(data.getDouble("poids"));

                String nom = data.getString("nom");
                if (!data.wasNull()) {
                    animal.setNom(nom);
                }

                Date sqlDateNaissance = data.getDate("dateNaissance");
                if (!data.wasNull()) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateNaissance);
                    animal.setDateNaissance(calendar);
                }

                Integer numPuce = data.getInt("numPuce");
                if (!data.wasNull()) {
                    animal.setNumPuce(numPuce);
                }

                String localisationPuce = data.getString("localisationPuce");
                if (!data.wasNull()) {
                    animal.setLocalisationPuce(localisationPuce);
                }

                java.sql.Date sqlDateAttributionPuce = data.getDate("dateAttributionPuce");
                if (!data.wasNull()) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateAttributionPuce);
                    animal.setDateAttributionPuce(calendar);
                }

                Integer numTatouage = data.getInt("numTatouage");
                if (!data.wasNull()) {
                    animal.setNumTatouage(numTatouage);
                }

                String localisationTatouage = data.getString("localisationPuce");
                if (!data.wasNull()) {
                    animal.setLocalisationTatouage(localisationTatouage);
                }

                Integer identifiantProprio = data.getInt("identifiantProprio");
                if (!data.wasNull()) {
                    IProprietaire propietaire = new DBDAOProprietaire();
                    animal.setProprietaire(propietaire.getUnProprietaire(identifiantProprio));
                }
                tousLesAnimaux.add(animal);
            }
            return tousLesAnimaux;
        } catch (SQLException e) {
            throw new AnimalException();
        } catch (ProprietaireException e) {
            throw new AnimalException("Erreur lors de la récupération du propriétaire de l'animal");
        }
    }

    public Animal getUnAnimal(Integer numRegistre) throws SingletonConnectionException, AnimalException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            Animal animal = new Animal();
            GregorianCalendar dateArrivee = new GregorianCalendar();

            sqlInstruction = "select * from spabd.animal where numRegistre = ?";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, numRegistre);
            data = statement.executeQuery();

            while (data.next()) {

                animal.setNumRegistre(data.getInt("numRegistre"));
                dateArrivee.setTime(data.getDate("dateArrivee"));
                animal.setDateArrivee(dateArrivee);
                animal.setEspece(data.getString("espece"));
                animal.setRace(data.getString("race"));
                animal.setSexe(data.getString("sexe"));
                animal.setEstSterilise(data.getBoolean("estSterilise"));
                animal.setCouleurDePeau(data.getString("couleurDePeau"));
                animal.setPoids(data.getDouble("poids"));

                String nom = data.getString("nom");
                if (!data.wasNull()) {
                    animal.setNom(nom);
                }

                Date sqlDateNaissance = data.getDate("dateNaissance");
                if (!data.wasNull()) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateNaissance);
                    animal.setDateNaissance(calendar);
                }

                Integer numPuce = data.getInt("numPuce");
                if (!data.wasNull()) {
                    animal.setNumPuce(numPuce);
                }

                String localisationPuce = data.getString("localisationPuce");
                if (!data.wasNull()) {
                    animal.setLocalisationPuce(localisationPuce);
                }

                java.sql.Date sqlDateAttributionPuce = data.getDate("dateAttributionPuce");
                if (!data.wasNull()) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateAttributionPuce);
                    animal.setDateAttributionPuce(calendar);
                }

                Integer numTatouage = data.getInt("numTatouage");
                if (!data.wasNull()) {
                    animal.setNumTatouage(numTatouage);
                }

                String localisationTatouage = data.getString("localisationPuce");
                if (!data.wasNull()) {
                    animal.setLocalisationTatouage(localisationTatouage);
                }

                Integer identifiantProprio = data.getInt("identifiantProprio");
                if (!data.wasNull()) {
                    IProprietaire propietaire = new DBDAOProprietaire();
                    animal.setProprietaire(propietaire.getUnProprietaire(identifiantProprio));
                }
            }
            return animal;
        } catch (SQLException e) {
            throw new AnimalException();
        } catch (ProprietaireException e) {
            throw new AnimalException("Erreur lors de la récupération du propriétaire de l'animal");
        }
    }

    public String[][] getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "select count(*)\n" +
                    "from spabd.veterinaire\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto)\n" +
                    "inner join spabd.animal\n" +
                    "on (spabd.animal.numRegistre = spabd.soinAvance.numRegistre)\n" +
                    "where spabd.veterinaire.identifiantVeto = ?;";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, selectionVeterinaire.getIdentifiantVeto());
            data = statement.executeQuery();
            data.next();
            Integer nombreDeLignes = data.getInt(1);

            String[][] listeResultatRecherche = new String[nombreDeLignes][];

            sqlInstruction = "select spabd.animal.numRegistre, spabd.animal.nom\n" +
                    "from spabd.veterinaire\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto)\n" +
                    "inner join spabd.animal\n" +
                    "on (spabd.animal.numRegistre = spabd.soinAvance.numRegistre)\n" +
                    "where spabd.veterinaire.identifiantVeto = ?;";
            statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, selectionVeterinaire.getIdentifiantVeto());
            data = statement.executeQuery();
            int i = 0;
            while (data.next()) {
                listeResultatRecherche[i] = new String[2];
                listeResultatRecherche[i][0] = Integer.toString(data.getInt(1));
                listeResultatRecherche[i][1] = data.getString(2);
                i++;
            }
            return listeResultatRecherche;
        } catch (SQLException e) {
            throw new AnimalException("Erreur lors de la récupération de la recherche des animaux en fonction du vétérinaire!");
        }
    }

    public String[][] getResultatRecherchAnimauxMedicamentVeto(Medicament selectionMedicament,
                               Veterinaire selectionVeterinaire) throws AnimalException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "select count(*)\n" +
                    "from spabd.medicament\n" +
                    "inner join spabd.ordonnance\n" +
                    "on (spabd.medicament.identifiantMed = spabd.ordonnance.identifiantMed)\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.ordonnance.numSoin = spabd.soinAvance.numSoin)\n" +
                    "inner join spabd.veterinaire\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto)\n" +
                    "inner join spabd.animal\n" +
                    "on (spabd.animal.numRegistre = spabd.soinAvance.numRegistre)\n" +
                    "AND (spabd.ordonnance.numRegistre = spabd.animal.numregistre)\n" +
                    "where spabd.veterinaire.identifiantVeto = ?\n" +
                    "AND spabd.medicament.identifiantMed = ?;";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, selectionMedicament.getIdentifiantMed());
            statement.setInt(2, selectionVeterinaire.getIdentifiantVeto());
            data = statement.executeQuery();
            data.next();
            Integer nombreDeLignes = data.getInt(1);

            String[][] listeResultatRecherche = new String[nombreDeLignes][];

            sqlInstruction = "select spabd.animal.numRegistre, spabd.animal.nom\n" +
                    "from spabd.medicament\n" +
                    "inner join spabd.ordonnance\n" +
                    "on (spabd.medicament.identifiantMed = spabd.ordonnance.identifiantMed)\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.ordonnance.numSoin = spabd.soinAvance.numSoin)\n" +
                    "inner join spabd.veterinaire\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto)\n" +
                    "inner join spabd.animal\n" +
                    "on (spabd.animal.numRegistre = spabd.soinAvance.numRegistre)\n" +
                    "AND (spabd.ordonnance.numRegistre = spabd.animal.numregistre)\n" +
                    "where spabd.veterinaire.identifiantVeto = ?\n" +
                    "AND spabd.medicament.identifiantMed = ?;";

            statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, selectionMedicament.getIdentifiantMed());
            statement.setInt(2, selectionVeterinaire.getIdentifiantVeto());
            data = statement.executeQuery();
            int i = 0;
            while (data.next()) {
                listeResultatRecherche[i] = new String[2];
                listeResultatRecherche[i][0] = Integer.toString(data.getInt(1));
                listeResultatRecherche[i][1] = data.getString(2);
                i++;
            }
            return listeResultatRecherche;
        } catch (SQLException e) {
            throw new AnimalException("Erreur lors de la récupération de la recherche des animaux en fonction du " +
                    "vétérinaire et d'un médicament!");
        }
    }

    public String[][] getResultatRecherchAnimauxMedicament(Medicament selectionMedicament) throws AnimalException,
            SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "select count(*)\n" +
                    "from spabd.medicament\n" +
                    "inner join spabd.ordonnance\n" +
                    "on (spabd.medicament.identifiantMed = spabd.ordonnance.identifiantMed)\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.ordonnance.numSoin = spabd.soinAvance.numSoin)\n" +
                    "inner join spabd.animal\n" +
                    "on spabd.animal.numRegistre = spabd.soinAvance.numRegistre\n" +
                    "AND (spabd.ordonnance.numRegistre = spabd.animal.numregistre)\n" +
                    "where spabd.medicament.identifiantMed = ?;";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, selectionMedicament.getIdentifiantMed());
            data = statement.executeQuery();
            data.next();
            Integer nombreDeLignes = data.getInt(1);

            String[][] listeResultatRecherche = new String[nombreDeLignes][];

            sqlInstruction = "select spabd.animal.numRegistre, spabd.animal.nom\n" +
                    "from spabd.medicament\n" +
                    "inner join spabd.ordonnance\n" +
                    "on (spabd.medicament.identifiantMed = spabd.ordonnance.identifiantMed)\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.ordonnance.numSoin = spabd.soinAvance.numSoin)\n" +
                    "inner join spabd.animal\n" +
                    "on spabd.animal.numRegistre = spabd.soinAvance.numRegistre\n" +
                    "AND (spabd.ordonnance.numRegistre = spabd.animal.numregistre)\n" +
                    "where spabd.medicament.identifiantMed = ?;";

            statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, selectionMedicament.getIdentifiantMed());
            data = statement.executeQuery();
            int i = 0;
            while (data.next()) {
                listeResultatRecherche[i] = new String[2];
                listeResultatRecherche[i][0] = Integer.toString(data.getInt(1));
                listeResultatRecherche[i][1] = data.getString(2);
                i++;
            }
            return listeResultatRecherche;
        } catch (SQLException e) {
            throw new AnimalException("Erreur lors de la récupération de la recherche des animaux en fonction du médicament!");
        }
    }

    public void ajouterAnimal(Animal animal) throws SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "insert into animal(numRegistre," +
                    " dateArrivee," +
                    " espece," +
                    " race," +
                    " sexe," +
                    "estSterilise," +
                    "couleurDePeau," +
                    "poids," +
                    "nom, dateNaissance, numPuce, localisationPuce, dateAttributionPuce, numTatouage, localisationTatouage, identifiantProprio)"+
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            java.sql.Date sqlDate = new java.sql.Date(GregorianCalendar.getInstance().getTimeInMillis());
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3, animal.getEspece());
            preparedStatement.setString(4, animal.getRace());
            preparedStatement.setString(5, animal.getSexe());
            preparedStatement.setBoolean(6, animal.isEstSterilise());
            preparedStatement.setString(7, animal.getCouleurDePeau());
            preparedStatement.setDouble(8, animal.getPoids());
            //facultatifs
            if(animal.getNom() != null){
                preparedStatement.setString(9, animal.getNom());
            }
            else{
                preparedStatement.setNull(9, Types.VARCHAR);
            }
            if(animal.getDateNaissance() != null){
                preparedStatement.setDate(10, new java.sql.Date(animal.getDateNaissance().getTimeInMillis()));
            }
            else{
                preparedStatement.setNull(10, Types.DATE);
            }
            if(animal.getNumPuce() != null){
                preparedStatement.setInt(11, animal.getNumPuce());
            }
            else{
                preparedStatement.setNull(11, Types.INTEGER);
            }
            if(animal.getLocalisationPuce() != null){
                preparedStatement.setString(12, animal.getLocalisationPuce());
            }
            else{
                preparedStatement.setNull(12, Types.VARCHAR);
            }
            if(animal.getDateAttributionPuce() != null){
                preparedStatement.setDate(13,new java.sql.Date(animal.getDateAttributionPuce().getTimeInMillis()));
            }
            else{
                preparedStatement.setNull(13, Types.DATE);
            }
            if(animal.getNumTatouage() != null){
                preparedStatement.setInt(14,animal.getNumPuce());
            }
            else{
                preparedStatement.setNull(14, Types.INTEGER);
            }
            if(animal.getLocalisationTatouage() != null){
                preparedStatement.setString(15,animal.getLocalisationTatouage());
            }
            else{
                preparedStatement.setNull(15, Types.VARCHAR);
            }
            if(animal.getProprietaire() != null){
                preparedStatement.setInt(16,animal.getProprietaire().getIdentifiantProprio());
            }
            else{
                preparedStatement.setNull(16, Types.INTEGER);
            }
            preparedStatement.executeUpdate();
        }
        catch(SingletonConnectionException exception){throw new SingletonConnectionException();}
        catch (SQLException e) {
            System.out.println("SQL exception" + e.getMessage());
        }
        catch(Exception exception){System.out.println(exception.getMessage());}
    }

    public String[][] getAnimauxTries(String critere) throws AnimalException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select count(*) from spabd.animal";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();
            data.next();
            Integer nombreDeLignes = data.getInt(1);
            String[][] tousLesAnimauxTries = new String[nombreDeLignes][];
            if (critere.equals("")){
                critere = "\"\"";
            }
            sqlInstruction = "select * from spabd.animal order by "+ critere + " asc;";
            statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            int i = 0;
            while (data.next()) {
                tousLesAnimauxTries[i] = new String[16];
                tousLesAnimauxTries[i][0] = Integer.toString(data.getInt("numRegistre"));
                tousLesAnimauxTries[i][1] = data.getDate("dateArrivee").toString();
                tousLesAnimauxTries[i][2] = data.getString("nom");
                tousLesAnimauxTries[i][3] = data.getString("espece");
                tousLesAnimauxTries[i][4] = data.getString("race");
                tousLesAnimauxTries[i][5] = data.getString("sexe");
                tousLesAnimauxTries[i][6] = Boolean.toString(data.getBoolean("estSterilise"));
                tousLesAnimauxTries[i][7] = data.getString("couleurDePeau");
                Date dateNaissance= data.getDate("dateNaissance");
                if(!data.wasNull())
                    tousLesAnimauxTries[i][8] = data.getDate("dateNaissance").toString();
                else
                    tousLesAnimauxTries[i][8] = null;
                tousLesAnimauxTries[i][9] = Double.toString(data.getDouble("poids"));
                Integer identifiantProprio = data.getInt("identifiantProprio");
                if(!data.wasNull())
                    tousLesAnimauxTries[i][10] = Integer.toString(data.getInt("identifiantProprio"));
                else
                    tousLesAnimauxTries[i][10] = null;
                Integer numPuce = data.getInt("numPuce");
                if(!data.wasNull())
                    tousLesAnimauxTries[i][11] = Integer.toString(data.getInt("numPuce"));
                else
                    tousLesAnimauxTries[i][11] = null;
                tousLesAnimauxTries[i][12] = data.getString("localisationPuce");
                Date dateAttributionPuce = data.getDate("dateAttributionPuce");
                if(!data.wasNull())
                    tousLesAnimauxTries[i][13] = data.getDate("dateAttributionPuce").toString();
                else
                    tousLesAnimauxTries[i][13] = null;
                Integer numTatouage = data.getInt("numTatouage");
                if(!data.wasNull())
                    tousLesAnimauxTries[i][14] = Integer.toString(data.getInt("numTatouage"));
                else
                    tousLesAnimauxTries[i][14] = null;

                tousLesAnimauxTries[i][15] = data.getString("localisationTatouage");
                i++;
            }
            return tousLesAnimauxTries;
        } catch (SQLException e) {
            throw new AnimalException();
        }
    }
}

