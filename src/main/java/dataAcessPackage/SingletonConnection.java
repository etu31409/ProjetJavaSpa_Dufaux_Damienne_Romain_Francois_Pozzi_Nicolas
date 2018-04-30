package dataAcessPackage;

import exceptionPackage.*;
import java.sql.*;
import java.util.*;

public class SingletonConnection {
    private static Connection connectionUnique;

    public static Connection getUniqueInstance() throws SingletonConnectionException {
        if(connectionUnique == null){
            try{
                connectionUnique = DriverManager.getConnection("jdbc:mysql://localhost:3306/spabd?useSSL=false",
                        "root", "root");
                // Créer l'instruction SQL avec ? pour éviter injection SQL
            }catch (SQLException exception)
            {
                throw new SingletonConnectionException();
            }
        }
        return connectionUnique;
    }
}
