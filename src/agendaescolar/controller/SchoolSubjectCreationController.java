package agendaescolar.controller;

import agendaescolar.domain.SchoolSubject;
import agendaescolar.repository.SchoolSubjectRepository;
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

public class SchoolSubjectCreationController implements Initializable {

    @FXML private VBox vBox;
    @FXML private TextField nameTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.setTitle(Main.SceneTypes.SCHOOLSUBJECTCREATION);
    }
    
    @FXML
    private void handleCreateButton() throws IOException {
        nameTextField.setText(nameTextField.getText().trim());
        
        if (!nameTextField.getText().isEmpty()) {
            SchoolSubject schoolSubject = new SchoolSubject(0, nameTextField.getText(), HomeController.getGroupSelected().getClassNumber());
            try {
                SchoolSubjectRepository.AddSchoolSubject(schoolSubject);
                JOptionPane.showMessageDialog(null, "O grupo foi criado com sucesso!");
                handleCancelButton();
            } catch (SQLException e) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.INFO, null, e);
                JOptionPane.showMessageDialog(null, "Desculpe, não foi possível criar a matéria.");
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
