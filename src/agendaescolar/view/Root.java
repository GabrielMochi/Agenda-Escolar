/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaescolar.view;

import static agendaescolar.AgendaEscolar.APP_NAME;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel Mochi
 */
public class Root {
    
    public static final String SCENE_LOGIN_NAME = "Login";
    public static final String SCENE_REGISTER_NAME = "Register";
    private static Stage stage;
    
    public Root(Stage stage) throws Exception {
        Root.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/agendaescolar/view/styles/Login.fxml"));
        Scene scene = new Scene(root);
        Root.stage.setScene(scene);
        Root.stage.setTitle(APP_NAME + " | " + SCENE_LOGIN_NAME);
        Root.stage.setResizable(false);
        Root.stage.centerOnScreen();
        Root.stage.show();
    }

    public static void setTitle(SceneTypes sceneTypes) {
        switch (sceneTypes) {
            case LOGIN:
                stage.setTitle(APP_NAME + " | " + SCENE_LOGIN_NAME);
                break;
            case REGISTER:
                stage.setTitle(APP_NAME + " | " + SCENE_REGISTER_NAME);
                break;
            default:
                stage.setTitle(APP_NAME);
                break;
        }
    }
    
    public enum SceneTypes {
        LOGIN, REGISTER
    }

}
