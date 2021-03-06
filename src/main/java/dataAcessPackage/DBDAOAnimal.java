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

    //get
    public ArrayList<Animal> getAnimaux() throws AnimalException, ConnexionException {
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

                String localisationTatouage = data.getString("localisationTatouage");
                if (!data.wasNull()) {
                    animal.setLocalisationTatouage(localisationTatouage);
                }

                Integer identifiantProprio = data.getInt("identifiantProprio");
                if (!data.wasNull()) {
                    animal.setProprietaire(identifiantProprio);
                }
                tousLesAnimaux.add(animal);
            }
            return tousLesAnimaux;
        } catch (SQLException e) {
            throw new AnimalException();
        }
    }

    public Animal getUnAnimal(Integer numRegistre) throws ConnexionException, AnimalException {
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
                    animal.setProprietaire(identifiantProprio);
                }
            }
            return animal;
        } catch (SQLException e) {
            throw new AnimalException();
        }
    }

    public ArrayList<AnimalProprietaire> getAnimauxTries(String critere)
            throws AnimalException, ConnexionException, ProprietaireException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            ArrayList<AnimalProprietaire> tousLesAnimauxTries = new ArrayList<>();

            String ordre = "asc";
            String critereColonne;
            if(critere.equals("Aucun tri"))
                critereColonne = "\"\"";
            else if (critere.equals("Date d'arrivée")){
                critereColonne = "dateArrivee";
                ordre = "desc";
            }
            else if (critere.equals("Date de naissance")) {
                critereColonne = "dateNaissance";
                ordre = "desc";
            }
            else if (critere.equals("Nom"))
                critereColonne = "nom";
            else if (critere.equals("Identifiant de l'animal"))
                critereColonne = "numRegistre";
            else if (critere.equals("Poids"))
                critereColonne = "poids";
            else
                critereColonne = "espece";

            sqlInstruction = "select * from spabd.animal " +
                    " order by "+ critereColonne + " " + ordre + ";";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            while (data.next()) {
                AnimalProprietaire animalProprietaire = new AnimalProprietaire();
                GregorianCalendar dateArrivee = new GregorianCalendar();
                animalProprietaire.setNumRegistre(data.getInt("numRegistre"));
                dateArrivee.setTime(data.getDate("dateArrivee"));
                animalProprietaire.setDateArrivee(dateArrivee);
                animalProprietaire.setEspece(data.getString("espece"));
                animalProprietaire.setRace(data.getString("race"));
                animalProprietaire.setSexe(data.getString("sexe"));
                animalProprietaire.setEstSterilise(data.getBoolean("estSterilise"));
                animalProprietaire.setCouleurDePeau(data.getString("couleurDePeau"));
                animalProprietaire.setPoids(data.getDouble("poids"));

                String nom = data.getString("nom");
                if (!data.wasNull()) {
                    animalProprietaire.setNom(nom);
                }
                Date sqlDateNaissance = data.getDate("dateNaissance");
                if (!data.wasNull()) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateNaissance);
                    animalProprietaire.setDateNaissance(calendar);
                }

                Integer numPuce = data.getInt("numPuce");
                if (!data.wasNull()) {
                    animalProprietaire.setNumPuce(numPuce);
                }

                String localisationPuce = data.getString("localisationPuce");
                if (!data.wasNull()) {
                    animalProprietaire.setLocalisationPuce(localisationPuce);
                }

                java.sql.Date sqlDateAttributionPuce = data.getDate("dateAttributionPuce");
                if (!data.wasNull()) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateAttributionPuce);
                    animalProprietaire.setDateAttributionPuce(calendar);
                }

                Integer numTatouage = data.getInt("numTatouage");
                if (!data.wasNull()) {
                    animalProprietaire.setNumTatouage(numTatouage);
                }

                String localisationTatouage = data.getString("localisationTatouage");
                if (!data.wasNull()) {
                    animalProprietaire.setLocalisationTatouage(localisationTatouage);
                }

                Integer identifiantProprio = data.getInt("identifiantProprio");
                if (!data.wasNull()) {
                    IProprietaire modeleProprio = new DBDAOProprietaire();
                    Proprietaire proprio = modeleProprio.getUnProprietaire(identifiantProprio);
                    animalProprietaire.setNomProprio(proprio.getNom());
                    animalProprietaire.setPrenomProprio(proprio.getPrenom());
                }

                tousLesAnimauxTries.add(animalProprietaire);
            }
            return tousLesAnimauxTries;
        } catch (SQLException e) {
            throw new AnimalException();
        } catch (ProprietaireException e) {
            throw new ProprietaireException();
        }
    }

    //recherches
    public ArrayList<Animal> getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            ConnexionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            ArrayList<Animal> listeResultatRecherche = new ArrayList<>();

            sqlInstruction = "select distinct spabd.animal.numRegistre, spabd.animal.nom\n, spabd.animal.espece, " +
                    "spabd.animal.race, spabd.soinAvance.dateSoin \n" +
                    "from spabd.veterinaire \n" +
                    "inner join spabd.soinAvance \n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto) \n" +
                    "inner join spabd.animal \n" +
                    "on (spabd.animal.numRegistre = spabd.soinAvance.numRegistre) \n" +
                    "where spabd.veterinaire.identifiantVeto = ?;";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, selectionVeterinaire.getIdentifiantVeto());
            data = statement.executeQuery();

            while (data.next()) {
                Animal a = new Animal();
                a.setNumRegistre(data.getInt(1));
                a.setNom(data.getString(2));
                a.setEspece(data.getString(3));
                a.setRace(data.getString(4));
                listeResultatRecherche.add(a);
            }
            return listeResultatRecherche;
        } catch (SQLException e) {
            throw new AnimalException("Erreur lors de la récupération de la recherche des animaux en fonction du vétérinaire!");
        }
    }

    public ArrayList<Animal> getResultatRecherchAnimauxMedicamentVeto(Medicament selectionMedicament,
                               Veterinaire selectionVeterinaire) throws AnimalException, ConnexionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            ArrayList<Animal> listeResultatRecherche = new ArrayList<>();

            sqlInstruction = "select spabd.animal.numRegistre, spabd.animal.nom, spabd.animal.espece, spabd.animal.race \n" +
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
            statement.setInt(1, selectionVeterinaire.getIdentifiantVeto());
            statement.setInt(2, selectionMedicament.getIdentifiantMed());
            data = statement.executeQuery();

            while (data.next()) {
                Animal a = new Animal();
                a.setNumRegistre(data.getInt(1));
                a.setNom(data.getString(2));
                a.setEspece(data.getString(3));
                a.setRace(data.getString(4));
                listeResultatRecherche.add(a);
            }
            return listeResultatRecherche;
        } catch (SQLException e) {
            throw new AnimalException("Erreur lors de la récupération de la recherche des animaux en fonction d'un " +
                    "vétérinaire et d'un médicament!");
        }
    }

    public ArrayList<Animal> getResultatRecherchAnimauxMedicament(Medicament selectionMedicament) throws AnimalException,
            ConnexionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            ArrayList<Animal> listeResultatRecherche = new ArrayList<>();

            sqlInstruction = "select distinct spabd.animal.numRegistre, spabd.animal.nom\n, spabd.animal.espece, spabd.animal.race \n" +
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

            while (data.next()) {
                Animal a = new Animal();
                a.setNumRegistre(data.getInt(1));
                a.setNom(data.getString(2));
                a.setEspece(data.getString(3));
                a.setRace(data.getString(4));
                listeResultatRecherche.add(a);
            }
            return listeResultatRecherche;
        } catch (SQLException e) {
            throw new AnimalException("Erreur lors de la récupération de la recherche des animaux en fonction du médicament!");
        }
    }

    //ajout/suppression/modification
    public void ajouterAnimal(Animal animal) throws ConnexionException, AnimalException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "insert into animal(numRegistre, dateArrivee, espece, race, sexe,estSterilise,couleurDePeau," +
                    "poids,nom, dateNaissance, numPuce, localisationPuce, dateAttributionPuce, numTatouage, " +
                    "localisationTatouage, identifiantProprio)"+
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
                preparedStatement.setInt(16,animal.getProprietaire());
            }
            else{
                preparedStatement.setNull(16, Types.INTEGER);
            }
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new AnimalException("Impossible d'ajouter l'animal");
        }
    }

    public void supprimerAnimal(Integer animal) throws AnimalException, ConnexionException{
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "delete from spabd.Ordonnance where numRegistre = ?;";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, animal);
            preparedStatement.executeUpdate();
            sqlInstruction = "delete from spabd.SoinAvance where numRegistre = ?;";
            preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, animal);
            preparedStatement.executeUpdate();
            sqlInstruction = "delete from spabd.Animal where numRegistre = ?;";
            preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, animal);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new AnimalException("Impossible de supprimer l'animal");
        }
    }

    public void modifierAnimal(Animal animal) throws  AnimalException, ConnexionException{
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "update animal set espece = ?, race = ?, sexe = ?, estSterilise = ?," +
                    "couleurDePeau = ?, poids = ?, nom = ?, dateNaissance = ?, numPuce = ?, localisationPuce = ?, " +
                    "dateAttributionPuce = ?, numTatouage = ?, localisationTatouage = ?," +
                    "identifiantProprio = ? where numRegistre = ?";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, animal.getEspece());
            preparedStatement.setString(2, animal.getRace());
            preparedStatement.setString(3, animal.getSexe());
            preparedStatement.setBoolean(4, animal.isEstSterilise());
            preparedStatement.setString(5, animal.getCouleurDePeau());
            preparedStatement.setDouble(6, animal.getPoids());

            if(animal.getNom() != null){
                preparedStatement.setString(7, animal.getNom());
            }
            else{
                preparedStatement.setNull(7, Types.VARCHAR);
            }
            if(animal.getDateNaissance() != null){
                preparedStatement.setDate(8, new java.sql.Date(animal.getDateNaissance().getTimeInMillis()));
            }
            else{
                preparedStatement.setNull(8, Types.DATE);
            }
            if(animal.getNumPuce() != null){
                preparedStatement.setInt(9, animal.getNumPuce());
            }
            else{
                preparedStatement.setNull(9, Types.INTEGER);
            }
            if(animal.getLocalisationPuce() != null){
                preparedStatement.setString(10, animal.getLocalisationPuce());
            }
            else{
                preparedStatement.setNull(10, Types.VARCHAR);
            }
            if(animal.getDateAttributionPuce() != null){
                preparedStatement.setDate(11,new java.sql.Date(animal.getDateAttributionPuce().getTimeInMillis()));
            }
            else{
                preparedStatement.setNull(11, Types.DATE);
            }
            if(animal.getNumTatouage() != null){
                preparedStatement.setInt(12,animal.getNumTatouage());
            }
            else{
                preparedStatement.setNull(12, Types.INTEGER);
            }
            if(animal.getLocalisationTatouage() != null){
                preparedStatement.setString(13,animal.getLocalisationTatouage());
            }
            else{
                preparedStatement.setNull(13, Types.VARCHAR);
            }
            if(animal.getProprietaire() != null){
                preparedStatement.setInt(14,animal.getProprietaire());
            }
            else{
                preparedStatement.setNull(14, Types.INTEGER);
            }
            preparedStatement.setInt(15, animal.getNumRegistre());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new AnimalException("Impossible de modifier l'animal");
        }
    }
}

