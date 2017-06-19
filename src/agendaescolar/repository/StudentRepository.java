package agendaescolar.repository;

import agendaescolar.service.MySQLConnection;
import agendaescolar.domain.Student;
import static agendaescolar.service.SQLCauses.NO_CONNECTION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static agendaescolar.service.SQLCauses.NOT_FOUND;

public class StudentRepository {
    
    public static void AddStudent(Student student) throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            PreparedStatement preparedStatement;
            
            try {
                preparedStatement = connection.prepareStatement(
                    "INSERT INTO students " +
                        "VALUES (?, ?, ?, ?)"
                );
                
                preparedStatement.setString(1, student.getEnchiridion());
                preparedStatement.setString(2, student.getName());
                preparedStatement.setString(3, student.getEmail());
                preparedStatement.setString(4, student.getPassword());
                
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("[Problem while executing the query]:" + e.getMessage());
                throw e;
            } finally {
                try {
                    connection.close();
                    System.out.println("[Connection closed!]");
                } catch (SQLException ex) {
                    System.out.println("[Problem while closing the connection]:\n");
                    Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else
            throw new SQLException(NO_CONNECTION);
    }
    
    public static void ConsultLoginStudent(String enchiridion, String password) throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            
            try {
                preparedStatement = connection.prepareStatement(
                    "SELECT enchiridion, password FROM students " +
                        "WHERE enchiridion = ? AND password = ?"
                );
                
                preparedStatement.setString(1, enchiridion);
                preparedStatement.setString(2, password);
                
                resultSet = preparedStatement.executeQuery();
                
                if (!resultSet.next())
                    throw new SQLException(NOT_FOUND);
            } catch (SQLException e) {
                System.out.println("[Error while validating]:\n" + e.getMessage());
                throw e;
            } finally {
                try {
                    connection.close();
                    System.out.println("[Connection closed!]");
                } catch (SQLException ex) {
                    System.out.println("[Problem while closing the connection]:\n");
                    Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
