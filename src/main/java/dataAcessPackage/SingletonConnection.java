package dataAcessPackage;

import java.sql.*;
import java.util.*;

public class SingletonConnection {
    private static Connection connectionUnique;

    public static Connection getUniqueInstance(){
        if(connectionUnique == null){
            try{
                connectionUnique = DriverManager.getConnection("jdbc:mysql://localhost:3306/spabd?useSSL=false",
                        "root", "Pn783iOm");
                // Créer l'instruction SQL avec ? pour éviter injection SQL
            }catch (SQLException exception) { System.out.println(exception.getMessage()); }
        }
        return connectionUnique;
    }
}
