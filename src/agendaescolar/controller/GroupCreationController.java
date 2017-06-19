package agendaescolar.controller;

import agendaescolar.domain.Group;
import agendaescolar.domain.SchoolBoy;
import agendaescolar.repository.GroupRepository;
import agendaescolar.repository.SchoolBoyRepository;
import agendaescolar.service.LogUser;
import agendaescolar.view.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

public class GroupCreationController implements Initializable {

    @FXML private VBox vBox;
    @FXML private TextField classNumberTextField;
    @FXML private TextField classNameTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.setTitle(Main.SceneTypes.GROUPCREATION);
    }
    
    @FXML
    private void handleCreateButton() throws IOException {
        classNumberTextField.setText(classNumberTextField.getText().trim());
        classNameTextField.setText(classNameTextField.getText().trim());
        
        if (!classNumberTextField.getText().isEmpty() &&
                !classNameTextField.getText().isEmpty()) {
            Group group = new Group(Integer.parseInt(classNumberTextField.getText()), classNameTextField.getText(), LogUser.getLoggedUser());
            try {
                GroupRepository.AddGroup(group);
                SchoolBoyRepository.AddSchoolBoy(new SchoolBoy(group.getStudentAdmin_Student_Enchiridion(), group.getClassNumber()));
                JOptionPane.showMessageDialog(null, "O grupo foi criado com sucesso!");
                handleCancelButton();
            } catch (SQLException e) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.INFO, null, e);
                JOptionPane.showMessageDialog(null, "Desculpe, não foi possível criar o grupo.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Campos vázios", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @FXML
    private void handleCancelButton() throws IOException {
        AnchorPane homeAcAnchorPane = FXMLLoader.load(getClass().getResource("/agendaescolar/view/styles/Home.fxml"));
        vBox.getChildren().setAll(homeAcAnchorPane);
    }
    
}
