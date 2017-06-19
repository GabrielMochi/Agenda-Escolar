package agendaescolar.controller;

import agendaescolar.domain.Student;
import agendaescolar.repository.StudentRepository;
import agendaescolar.service.LogUser;
import static agendaescolar.service.SQLCauses.NO_CONNECTION;
import agendaescolar.view.Main;
import agendaescolar.view.Root;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class RegisterController implements Initializable {

    @FXML private AnchorPane anchorPane;
    @FXML private TextField enchiridionTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField confirmPasswordTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Root.setTitle(Root.SceneTypes.REGISTER);
    }
    
    @FXML
    private void handleCreateButton() throws IOException {
        enchiridionTextField.setText(enchiridionTextField.getText().trim());
        nameTextField.setText(nameTextField.getText().trim());
        emailTextField.setText(emailTextField.getText().trim());
        passwordTextField.setText(passwordTextField.getText().trim());
        confirmPasswordTextField.setText(confirmPasswordTextField.getText().trim());
        
        if (passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
            if (!enchiridionTextField.getText().isEmpty() &&
                    !nameTextField.getText().isEmpty() &&
                    !emailTextField.getText().isEmpty() &&
                    !passwordTextField.getText().isEmpty()) {
                Student student = new Student(enchiridionTextField.getText().trim(), nameTextField.getText().trim(), emailTextField.getText().trim(), passwordTextField.getText().trim());
                try {
                    StudentRepository.AddStudent(student);
                    JOptionPane.showMessageDialog(null, "Parabéns, você foi registrado com sucesso!");
                    ((Stage) anchorPane.getScene().getWindow()).close();
                    LogUser.logInUser(student.getEnchiridion());
                    Main main = new Main();
                } catch (SQLException e) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.INFO, null, e);
                    
                    if (!e.getMessage().equals(NO_CONNECTION))
                        JOptionPane.showMessageDialog(null, "Desculpe, não foi possível lhe registrar.");
                    else {
                        JOptionPane.showMessageDialog(null, "Desculpe, estamos tendo problemas com a conexão do servidor.", "Conexão não estabelecida", JOptionPane.ERROR_MESSAGE);
                        ((Stage) anchorPane.getScene().getWindow()).close();
                    }
                }
            } else
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Campos vázios", JOptionPane.ERROR_MESSAGE);
        } else
            JOptionPane.showMessageDialog(null, "As senhas não se correspondem", "Senhas incoerentes", JOptionPane.ERROR_MESSAGE);
    }
    
    @FXML
    private void handleCancelButton() throws IOException {
        AnchorPane loginAcAnchorPane = FXMLLoader.load(getClass().getResource("/agendaescolar/view/styles/Login.fxml"));
        anchorPane.getChildren().setAll(loginAcAnchorPane);
    }
    
}
