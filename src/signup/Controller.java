package signup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import login.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by archazey on 5/7/17.
 */
public class Controller implements Initializable{
    @FXML
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public Button backButton;

    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;

        backButton.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   //load login page
                   Parent root = null;
                   try {
                       root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   Scene scene = new Scene(root);
                   Main.window.setScene(scene);
               }
           }
        );

    }
}
