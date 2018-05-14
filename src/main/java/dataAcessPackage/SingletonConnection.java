package dataAcessPackage;

import exceptionPackage.*;
import java.sql.*;
import java.util.*;

public class SingletonConnection {
    private static Connection connectionUnique;

    public static Connection getUniqueInstance() throws ConnexionException {
        if(connectionUnique == null){
            try{
            connectionUnique = DriverManager.getConnection("jdbc:mysql://localhost:3306/spabd?useSSL=false",
                    "root", "Pn783iOm");
            }
            catch (SQLException exception) {
                throw new ConnexionException();
            }
        }
        return connectionUnique;
    }
}
