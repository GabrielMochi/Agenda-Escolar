package agendaescolar.repository;

import agendaescolar.domain.SchoolBoy;
import agendaescolar.service.MySQLConnection;
import static agendaescolar.service.SQLCauses.NO_CONNECTION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchoolBoyRepository {
    
    public static void AddSchoolBoy(SchoolBoy schoolBoy) throws SQLException {
        Connection connection = MySQLConnection.getConnection();
        
        if (connection != null) {
            PreparedStatement preparedStatement;
            
            try {
                preparedStatement = connection.prepareStatement(
                    "INSERT INTO schoolboys " +
                        "VALUES (?, ?)"
                );
                
                preparedStatement.setInt(1, schoolBoy.getGroup_classNumber());
                preparedStatement.setString(2, schoolBoy.getStudent_enchiridion());
                
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
}
