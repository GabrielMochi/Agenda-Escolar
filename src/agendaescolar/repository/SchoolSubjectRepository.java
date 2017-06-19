package agendaescolar.repository;

import agendaescolar.controller.HomeController;
import agendaescolar.domain.SchoolSubject;
import agendaescolar.service.MySQLConnection;
import static agendaescolar.service.SQLCauses.NO_CONNECTION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchoolSubjectRepository {
    public static void AddSchoolSubject(SchoolSubject schoolSubject) throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            PreparedStatement preparedStatement;
            
            try {
                preparedStatement = connection.prepareStatement(
                    "INSERT INTO schoolsubjects " +
                        "VALUES (DEFAULT, ?, ?)"
                );
                
                preparedStatement.setString(1, schoolSubject.getName());
                preparedStatement.setInt(2, schoolSubject.getGroup_classNumber());
                
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
    
    public static List<SchoolSubject> getSchoolSubjectsByGroupSelected() throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            List<SchoolSubject> schoolSubjects = new ArrayList();
            
            try {
                preparedStatement = connection.prepareStatement(
                    "SELECT " +
                        "id, name, Group_classNumber " +
                    "FROM " +
                        "schoolsubjects " +
                    "WHERE Group_classNumber = ?"
                );
                
                preparedStatement.setInt(1, HomeController.getGroupSelected().getClassNumber());
                
                resultSet = preparedStatement.executeQuery();
                
                while (resultSet.next()) {
                    schoolSubjects.add(new SchoolSubject(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("Group_classNumber")
                    ));
                }
                
                return schoolSubjects;
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
        } else {
            throw new SQLException(NO_CONNECTION);
        }
    }
}
