/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Kristian Nielsen
 */
public class DBConnector {

    private static final String url = "jdbc:mysql://localhost:3306/lindholm?zeroDateTimeBehavior=convertToNull";
    private static final String username = "root";
    private static final String password = "pass";
    private static final String PU_NAME = "LindholmPU";

    private static EntityManagerFactory entityManagerFactory;
    private static Connection connection;

    private DBConnector() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                System.out.println("SQL Connection failed, make sure values (url, username and password) are all correct and that database (" + url + ") is accesible");
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            try{
                entityManagerFactory = Persistence.createEntityManagerFactory(PU_NAME);
            }catch(Exception e){
                System.out.println("Failed initializing EntityManagerFactory with PU Name ("+PU_NAME+")");
                e.printStackTrace();
            }
        }
        return entityManagerFactory.createEntityManager();
    }

}
