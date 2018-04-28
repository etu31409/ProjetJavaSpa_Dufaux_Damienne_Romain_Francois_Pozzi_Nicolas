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

    public  ArrayList<Animal> getAnimaux() throws AnimalException, SingletonConnectionException, ProprietaireException{
        try{
            if(connectionUnique == null) {
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
                dateArrivee.setTime( data.getDate("dateArrivee"));
                animal.setDateArrivee(dateArrivee);
                animal.setEspece( data.getString("espece"));
                animal.setRace(data.getString("race"));
                animal.setSexe(data.getString("sexe"));
                animal.setEstSterilise(data.getBoolean("estSterilise"));
                animal.setCouleurDePeau(data.getString("couleurDePeau"));
                animal.setPoids(data.getDouble("poids"));

                //Nullable Values
                String nom = data.getString("nom");
                if(!data.wasNull()){
                    animal.setNom(nom);
                }

                Date sqlDateDepart = data.getDate("dateDepart");
                if(!data.wasNull()){
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateDepart);
                    animal.setDateDepart(calendar);
                }

                Date sqlDateNaissance = data.getDate("dateNaissance");
                if(!data.wasNull()){
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateNaissance);
                    animal.setDateNaissance(calendar);
                }

                Double numPuce = data.getDouble("numPuce");
                if(!data.wasNull()){
                    animal.setNumPuce(numPuce);
                }

                String localisationPuce = data.getString("localisationPuce");
                if(!data.wasNull()){
                    animal.setLocalisationPuce(localisationPuce);
                }

                java.sql.Date sqlDateAttributionPuce = data.getDate("dateAttributionPuce");
                if(!data.wasNull()){
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateAttributionPuce);
                    animal.setDateAttributionPuce(calendar);
                }

                Double numTatouage = data.getDouble("numTatouage");
                if(!data.wasNull()){
                    animal.setNumTatouage(numTatouage);
                }

                String localisationTatouage = data.getString("localisationPuce");
                if(!data.wasNull()){
                    animal.setLocalisationTatouage(localisationTatouage);
                }

                Integer identifiantProprio =  data.getInt("identifiantProprio");
                if(!data.wasNull()){
                    IProprietaire propietaire = new DBDAOProprietaire();
                    animal.setProprietaire(propietaire.getUnProprietaire(identifiantProprio,false));
                }

                tousLesAnimaux.add(animal);
            }
            //connectionUnique.close();
            return tousLesAnimaux;
        }
        catch(SQLException e) {
            throw new AnimalException();
        }
    }
}
