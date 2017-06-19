package agendaescolar.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {

    public static Connection getConnection() {
        Connection connection;
        
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            
            String serverName = "localhost:3306";
            String mydatabase = "agendaescolar";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "gmochi";
            String password = "InspironDell@8";
            
            connection = DriverManager.getConnection(url, username, password);
            
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("[The JDBC driver wasn't found]: \n\n");
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, e);
            
            return null;
        } catch (SQLException ex) {
            System.out.println("[It was impossible to connect to database]: \n\n");
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
    }

}