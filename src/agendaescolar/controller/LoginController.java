package agendaescolar.controller;

import agendaescolar.repository.StudentRepository;
import agendaescolar.service.LogUser;
import static agendaescolar.service.SQLCauses.NOT_FOUND;
import agendaescolar.view.Main;
import agendaescolar.view.Root;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {
    
    @FXML private AnchorPane anchorPane;
    @FXML private TextField enchiridionTextField;
    @FXML private PasswordField passwordTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Root.setTitle(Root.SceneTypes.LOGIN);
    }
    
    @FXML
    private void handleLoginButton() throws IOException {
        enchiridionTextField.setText(enchiridionTextField.getText().trim());
        passwordTextField.setText(passwordTextField.getText().trim());
        
        if (!enchiridionTextField.getText().isEmpty() &&
                !passwordTextField.getText().isEmpty()) {
            try {
                StudentRepository.ConsultLoginStudent(enchiridionTextField.getText(), passwordTextField.getText());
                ((Stage) anchorPane.getScene().getWindow()).close();
                LogUser.logInUser(enchiridionTextField.getText());
                Main main = new Main();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, (e.getMessage().equals(NOT_FOUND)) ? "Este usuário ou esta senha não existem." : "Um erro interno ocorreu durante o processo de login!", "Erro login", JOptionPane.ERROR_MESSAGE);
                
                if (!e.getMessage().equals(NOT_FOUND))
                    ((Stage) anchorPane.getScene().getWindow()).close();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Campos vázios", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @FXML
    private void handleRegisterButton() throws IOException {
        AnchorPane registerAcAnchorPane = FXMLLoader.load(getClass().getResource("/agendaescolar/view/styles/Register.fxml"));
        anchorPane.getChildren().setAll(registerAcAnchorPane);
    }
    
}
