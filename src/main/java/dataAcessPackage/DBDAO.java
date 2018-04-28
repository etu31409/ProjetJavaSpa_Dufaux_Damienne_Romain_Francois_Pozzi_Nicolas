package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.*;

public class DBDAO implements DAO{

    private Connection connectionUnique;
    private String sqlInstruction;

    private ResultSet data;

    public  ArrayList<Animal> getAnimaux() throws AnimalException{
        try{
            String nom;
            GregorianCalendar dateArrivee = new GregorianCalendar();
            GregorianCalendar dateDepart = new GregorianCalendar();
            GregorianCalendar dateNaissance = new GregorianCalendar();
            Double numPuce;
            String localisationPuce;
            GregorianCalendar dateAttributionPuce = new GregorianCalendar();
            Double numTatouage;
            String localisationTatouage;
            Proprietaire identifiantProprio;

            if(connectionUnique == null){
                connectionUnique = SingletonConnection.getUniqueInstance();
            }

            sqlInstruction = "select * from spabd.animal";
            PreparedStatement statement = connectionUnique.prepareStatement(sqlInstruction);
            data = statement.executeQuery();

            ArrayList<Animal> tousLesAnimaux = new ArrayList<Animal>();
            while (data.next()) {
                Animal animal = new Animal();
                animal.setNumRegistre(data.getInt("numRegistre"));
                dateArrivee.setTime( data.getDate("dateArrivee"));
                animal.setDateArrivee(dateArrivee);
                animal.setEspece( data.getString("espece"));
                animal.setRace(data.getString("race"));
                animal.setSexe(data.getString("sexe"));
                animal.setEstSterilise(data.getBoolean("estSterilise"));
                animal.setCouleurDePeau(data.getString("couleurDePeau"));
                animal.setPoids(data.getDouble("poids"));

                nom = data.getString("nom");
                if(!data.wasNull()){
                    animal.setNom(nom);
                }

                java.sql.Date sqlDateDepart = data.getDate("dateDepart");
                dateDepart.setTime(sqlDateDepart);
                if(!data.wasNull()){
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateDepart);
                    animal.setDateDepart(calendar);
                }

                java.sql.Date sqlDateNaissance = data.getDate("dateNaissance");
                dateNaissance.setTime(sqlDateNaissance);
                if(!data.wasNull()){
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateNaissance);
                    animal.setDateNaissance(calendar);
                }

                numPuce = data.getDouble("numPuce");
                if(!data.wasNull()){
                    animal.setNumPuce(numPuce);
                }

                localisationPuce = data.getString("localisationPuce");
                if(!data.wasNull()){
                    animal.setLocalisationPuce(localisationPuce);
                }

                java.sql.Date sqlDateAttributionPuce = data.getDate("dateAttributionPuce");
                dateAttributionPuce.setTime(sqlDateAttributionPuce);
                if(!data.wasNull()){
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(sqlDateAttributionPuce);
                    animal.setDateAttributionPuce(calendar);
                }

                numTatouage = data.getDouble("numTatouage");
                if(!data.wasNull()){
                    animal.setNumTatouage(numTatouage);
                }

                localisationTatouage = data.getString("localisationPuce");
                if(!data.wasNull()){
                    animal.setLocalisationTatouage(localisationTatouage);
                }

                tousLesAnimaux.add(animal);
            }
            connectionUnique.close();
            return tousLesAnimaux;
        }
        catch(SQLException e) {
            throw new AnimalException();
        }
    }
    //test
}
