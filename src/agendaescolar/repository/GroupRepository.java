package agendaescolar.repository;

import agendaescolar.domain.Group;
import agendaescolar.service.LogUser;
import agendaescolar.service.MySQLConnection;
import static agendaescolar.service.SQLCauses.NO_CONNECTION;
import static agendaescolar.service.SQLCauses.NOT_FOUND;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupRepository {
    
    public static void AddGroup(Group group) throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            PreparedStatement preparedStatement;
            
            try {
                preparedStatement = connection.prepareStatement(
                    "INSERT INTO groups " +
                        "VALUES (?, ?, ?)"
                );
                
                preparedStatement.setInt(1, group.getClassNumber());
                preparedStatement.setString(2, group.getClassName());
                preparedStatement.setString(3, group.getStudentAdmin_Student_Enchiridion());
                
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
    
    public static List<Group> getGroupsByUserLogged() throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            List<Group> groups = new ArrayList();
            int rows = 0;
            
            try {
                preparedStatement = connection.prepareStatement(
                    "SELECT " +
                        "g.classNumber, g.className, g.studentAdmin_Student_Enchiridion " +
                    "FROM " +
                        "groups g " +
                        "JOIN schoolboys sb ON g.classNumber = sb.Group_classNumber " +
                        "JOIN students s ON s.enchiridion = sb.Student_enchiridion " +
                    "WHERE s.enchiridion = ?"
                );
                
                preparedStatement.setString(1, LogUser.getLoggedUser());
                
                resultSet = preparedStatement.executeQuery();
                
                while (resultSet.next()) {
                    groups.add(new Group(
                        resultSet.getInt("classNumber"),
                        resultSet.getString("className"),
                        resultSet.getString("studentAdmin_Student_Enchiridion")
                    ));
                    rows++;
                }
                
                return groups;
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
