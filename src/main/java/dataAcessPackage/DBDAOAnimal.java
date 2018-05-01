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

    public void ajouterAnimal(Animal animal) throws AnimalException, SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            /*sqlInstruction = "insert into animal values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, animal.getNom());
            preparedStatement.setDate(2, new java.sql.Date(animal.getDateArrivee().getTimeInMillis()));
            preparedStatement.setString(3, animal.getEspece());
            preparedStatement.setString(4, animal.getRace());
            preparedStatement.setString(5, animal.getSexe());
            preparedStatement.setBoolean(6, animal.isEstSterilise());
            preparedStatement.setString(7, animal.getCouleurDePeau());
            preparedStatement.setDate(8, new java.sql.Date(animal.getDateNaissance().getTimeInMillis()));
            preparedStatement.setInt(9, animal.getNumPuce());
            preparedStatement.setString(10, animal.getLocalisationPuce());
            preparedStatement.setDate(11, new java.sql.Date(animal.getDateAttributionPuce().getTimeInMillis()));
            preparedStatement.setInt(12, animal.getNumTatouage());
            preparedStatement.setString(13, animal.getLocalisationTatouage());
            preparedStatement.setDouble(14, animal.getPoids());
            preparedStatement.setInt(15, animal.getProprietaire().getIdentifiantProprio());

            data = preparedStatement.executeQuery();*/

            sqlInstruction = "insert into animal(nom," +
                    " dateArrivee," +
                    " espece," +
                    " race," +
                    " sexe," +
                    "estSterilise," +
                    " couleurDePeau," +
                    " dateNaissance," +
                    " numPuce," +
                    " localisationPuce," +
                    " dateAttributionPuce," +
                    " numTatouage," +
                    " localisationTatouage," +
                    " poids," +
                    " identifiantProprio) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connectionUnique.prepareStatement(sqlInstruction);

            preparedStatement.setString(1, animal.getNom());
            java.sql.Date sqlDateAttivee = new java.sql.Date(animal.getDateArrivee().getTimeInMillis());
            preparedStatement.setDate(2, sqlDateAttivee);
            preparedStatement.setString(4, animal.getEspece());
            preparedStatement.setString(5, animal.getRace());
            preparedStatement.setString(6, animal.getSexe());
            preparedStatement.setBoolean(7, animal.isEstSterilise());
            java.sql.Date sqlDateNaissance = new java.sql.Date(animal.getDateNaissance().getTimeInMillis());
            preparedStatement.setDate(8, sqlDateNaissance);
            preparedStatement.setInt(9, animal.getNumPuce());
            preparedStatement.setString(10, animal.getLocalisationPuce());
            java.sql.Date sqlDateAttributionPuce = new java.sql.Date(animal.getDateAttributionPuce().getTimeInMillis());
            preparedStatement.setDate(11, sqlDateAttributionPuce);
            preparedStatement.setDouble(12, animal.getNumTatouage());
            preparedStatement.setString(13, animal.getLocalisationTatouage());
            preparedStatement.setDouble(14, animal.getPoids());
            preparedStatement.setNull(15, Types.INTEGER);
            preparedStatement.executeUpdate();
            //preparedStatement.setInt(15, animal.getProprietaire());

        } catch (SQLException e) {
            throw new AnimalException("Erreur lors de l'insertion de l'animal !");
        }
    }
}

