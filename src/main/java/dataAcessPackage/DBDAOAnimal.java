package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.*;

public class DBDAOAnimal implements IAnimal {

    private Connection connectionUnique;
    private String sqlInstruction;

    private ResultSet data;

    public ArrayList<Animal> getAnimaux() throws AnimalException, SingletonConnectionException, ProprietaireException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select * from spabd.animal";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            ArrayList<Animal> tousLesAnimaux = new ArrayList<Animal>();
            while (data.next()) {
                //Not NULL Values
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

                //Nullable Values
                String nom = data.getString("nom");
                if (!data.wasNull()) {
                    animal.setNom(nom);
                }

                Date sqlDateDepart = data.getDate("dateDepart");
                if (!data.wasNull()) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateDepart);
                    animal.setDateDepart(calendar);
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

                Double numTatouage = data.getDouble("numTatouage");
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
                    animal.setProprietaire(propietaire.getUnProprietaire(identifiantProprio, false));
                }

                tousLesAnimaux.add(animal);
            }
            //connectionUnique.close();
            return tousLesAnimaux;
        } catch (SQLException e) {
            throw new AnimalException();
        }
    }

    public Animal getUnAnimal(Integer numRegistre) throws SingletonConnectionException, AnimalException, ProprietaireException {
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

                Date sqlDateDepart = data.getDate("dateDepart");
                if (!data.wasNull()) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateDepart);
                    animal.setDateDepart(calendar);
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

                Double numTatouage = data.getDouble("numTatouage");
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
                    animal.setProprietaire(propietaire.getUnProprietaire(identifiantProprio, false));
                }
            }
            return animal;
        } catch (SQLException e) {
            throw new AnimalException();
        }
    }

    public String[][] getResultatRecherchAnimauxVeterinaire(Veterinaire selectionVeterinaire) throws AnimalException,
            SingletonConnectionException {
        try {
            if (connectionUnique == null) {
                connectionUnique = SingletonConnection.getUniqueInstance();
            }
            sqlInstruction = "select count(*)" +
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

            sqlInstruction = "select spabd.animal.numRegistre, spabd.animal.nom" +
                    "from spabd.veterinaire\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinsAvance.identifiantVeto)\n" +
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
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto\n" +
                    "inner join spabd.animal\n" +
                    "on (spabd.animal.numRegistre = spabd.soinAvance.numRegistre)\n" +
                    "where spabd.veterinaire.identifiantVeto = ?" +
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
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinAvance.identifiantVeto\n" +
                    "inner join spabd.animal\n" +
                    "on (spabd.animal.numRegistre = spabd.soinAvance.numRegistre)\n" +
                    "where spabd.veterinaire.identifiantVeto = ?" +
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
            sqlInstruction = "select count(*)" +
                    "from spabd.veterinaire\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.veterinaire.identifiantVeto = spabd.soinsAvance.identifiantVeto)\n" +
                    "inner join spabd.animal\n" +
                    "on (spabd.animal.numRegistre = spabd.soinAvance.numRegistre)\n" +
                    "where spabd.medicament.identifiantMed = ?;";

            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            statement.setInt(1, selectionMedicament.getIdentifiantMed());
            data = statement.executeQuery();
            data.next();
            Integer nombreDeLignes = data.getInt(1);

            String[][] listeResultatRecherche = new String[nombreDeLignes][];

            sqlInstruction = "select spabd.animal.numRegistre, spabd.animal.nom" +
                    "from spabd.medicament\n" +
                    "inner join spabd.ordonnance\n" +
                    "on (spabd.medicament.identifiantMed = spabd.ordonnance.identifiantMed)\n" +
                    "inner join spabd.soinAvance\n" +
                    "on (spabd.ordonnance.numSoin = spabd.soinAvance.numSoin)\n" +
                    "inner join spabd.animal" +
                    "on spabd.animal.numRegistre = spabd.soinAvance.numRegistre" +
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
}

