package dataAcessPackage;

import exceptionPackage.*;
import java.sql.*;

public class SingletonConnection {
    private static Connection connectionUnique;

    public static Connection getUniqueInstance() throws ConnexionException {
        if(connectionUnique == null){
            try{
            connectionUnique = DriverManager.getConnection("jdbc:mysql://localhost:3306/spabd?useSSL=false",
                    "root", "root");
            }
            catch (SQLException exception) {
                throw new ConnexionException();
            }
        }
        return connectionUnique;
    }
}
