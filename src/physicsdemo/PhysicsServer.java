/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicsdemo;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import simulation.Simulation;

public class PhysicsServer extends Application {

    @Override
    public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ServerFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Game Server");
        // Closing the main window does is not sufficient to completely kill
        // the application, since we are going to be running a server thread.
        // Calling System.exit() should do the trick.
        stage.setOnCloseRequest(event->System.exit(0));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
