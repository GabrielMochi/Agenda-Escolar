/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaescolar;

import agendaescolar.view.Root;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel Mochi
 */
public class AgendaEscolar extends Application {

    public static final String APP_NAME = "Agenda Escolar";
    
    @Override
    public void start(Stage stage) throws Exception {
        Root root = new Root(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
