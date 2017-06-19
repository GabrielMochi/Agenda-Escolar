package agendaescolar.controller;

import agendaescolar.domain.Task;
import agendaescolar.repository.TaskRepository;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

public class TaskCreationController implements Initializable {

    @FXML private VBox vBox;
    @FXML private TextField groupScetionTextField;
    @FXML private TextArea descriptionTextArea;
    @FXML private TextArea notesTextArea;
    @FXML private TextField deliveryDateTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.setTitle(Main.SceneTypes.TASKCREATION);
    }
    
    @FXML
    private void handleCreateButton() throws IOException {
        groupScetionTextField.setText(groupScetionTextField.getText().trim());
        deliveryDateTextField.setText(deliveryDateTextField.getText().trim());
        
        if (!groupScetionTextField.getText().isEmpty() && 
                !descriptionTextArea.getText().isEmpty() && 
                !notesTextArea.getText().isEmpty() && 
                !deliveryDateTextField.getText().isEmpty()) {
            Task task = new Task(0, groupScetionTextField.getText(), descriptionTextArea.getText(), notesTextArea.getText(), deliveryDateTextField.getText(), HomeController.getSchoolSubjectSelected().getId());
            try {
                TaskRepository.AddTask(task);
                JOptionPane.showMessageDialog(null, "A tarefa foi criada com sucesso!");
                handleCancelButton();
            } catch (SQLException e) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.INFO, null, e);
                JOptionPane.showMessageDialog(null, "Desculpe, não foi possível criar a tarefa.");
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
