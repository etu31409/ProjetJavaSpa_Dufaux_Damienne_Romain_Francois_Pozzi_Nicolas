package dataAcessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO {
    //Animal getUnAnimal();
    ArrayList<Animal>getAnimaux() throws AnimalException;
}
