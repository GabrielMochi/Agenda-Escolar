package agendaescolar.view;

import static agendaescolar.AgendaEscolar.APP_NAME;
import agendaescolar.domain.Task;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TaskView {
    
    public static final String SCENE_TASK_NAME = "Tarefa";

    public TaskView(Task task) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/agendaescolar/view/styles/Task.fxml"));
        Scene scene = new Scene(root);
        
        Node sectionLabel = scene.lookup("#sectionLabel");
        Node postLabel = scene.lookup("#postLabel");
        Node deliveryLabel = scene.lookup("#deliveryLabel");
        Node notesTextArea = scene.lookup("#notesTextArea");
        Node descriptionTextArea = scene.lookup("#descriptionTextArea");
        ((Label) sectionLabel).setText(task.getGroupSection());
        ((Label) postLabel).setText(task.getPostDate());
        ((Label) deliveryLabel).setText(task.getDeliveryDate());
        ((TextArea) notesTextArea).setText(task.getNotes());
        ((TextArea) descriptionTextArea).setText(task.getDescription());
        
        stage.setScene(scene);
        stage.setTitle(APP_NAME + " | " + SCENE_TASK_NAME);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    
}
