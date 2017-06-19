package agendaescolar.view;

import static agendaescolar.AgendaEscolar.APP_NAME;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {
    
    public static final String SCENE_HOME_NAME = "Home";
    public static final String SCENE_GROUPCREATION_NAME = "Novo grupo";
    public static final String SCENE_SCHOOLSUBJECTCREATION_NAME = "Nova matéria";
    public static final String SCENE_TASKCREATION_NAME = "Nova tarefa";
    private static Stage stage;
    
    public Main() throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/agendaescolar/view/styles/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(APP_NAME + " | " + SCENE_HOME_NAME);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    
    public static void setTitle(SceneTypes sceneTypes) {
        switch (sceneTypes) {
            case HOME:
                stage.setTitle(APP_NAME + " | " + SCENE_HOME_NAME);
                break;
            case GROUPCREATION:
                stage.setTitle(APP_NAME + " | " + SCENE_HOME_NAME + " | " + SCENE_GROUPCREATION_NAME);
                break;
            case SCHOOLSUBJECTCREATION:
                stage.setTitle(APP_NAME + " | " + SCENE_HOME_NAME + " | " + SCENE_SCHOOLSUBJECTCREATION_NAME);
                break;
            case TASKCREATION:
                stage.setTitle(APP_NAME + " | " + SCENE_HOME_NAME + " | " + SCENE_TASKCREATION_NAME);
                break;
            default:
                stage.setTitle(APP_NAME);
        }
    }
    
    public enum SceneTypes {
        HOME, GROUPCREATION, SCHOOLSUBJECTCREATION, TASKCREATION
    }

}
