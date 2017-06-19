package agendaescolar.repository;

import agendaescolar.controller.HomeController;
import agendaescolar.domain.Task;
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

public class TaskRepository {
    
    public static void AddTask(Task task) throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            PreparedStatement preparedStatement;
            
            try {
                preparedStatement = connection.prepareStatement(
                    "INSERT INTO tasks " +
                        "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)"
                );
                
                preparedStatement.setString(1, task.getGroupSection());
                preparedStatement.setString(2, task.getDescription());
                preparedStatement.setString(3, task.getNotes());
                preparedStatement.setString(4, task.getPostDate());
                preparedStatement.setString(5, task.getDeliveryDate());
                preparedStatement.setInt(6, task.getSchoolSubject_id());
                
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
    
    public static List<Task> getTasksBySchoolSubject() throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            List<Task> tasks = new ArrayList();
            
            try {
                preparedStatement = connection.prepareStatement(
                    "SELECT " +
                        "id, groupSection, description, notes, postDate, deliveryDate, Schoolsubject_id " +
                    "FROM " +
                        "tasks " +
                    "WHERE Schoolsubject_id = ?"
                );
                
                preparedStatement.setInt(1, HomeController.getSchoolSubjectSelected().getId());
                
                resultSet = preparedStatement.executeQuery();
                
                while (resultSet.next()) {
                    tasks.add(new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("groupSection"),
                        resultSet.getString("description"),
                        resultSet.getString("notes"),
                        resultSet.getString("deliveryDate"),
                        resultSet.getInt("Schoolsubject_id")
                    ));
                }
                
                return tasks;
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
