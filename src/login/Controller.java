package login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller implements Initializable{
    @FXML
    private Button login;
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public Button signup;
    public TextField userid;
    public PasswordField passid;

    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;
        assert login != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";
        assert signup   != null : "fx:id=\"signup\" was not injected: check your FXML file 'simple.fxml'.";

        login.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Login!");
                System.out.println(userid.getText());
                System.out.println(passid.getText());

                DBConnect dbConnect = DBConnect.getInstance();
                dbConnect.checkLogin(userid.getText(),passid.getText());
            }
        });

        signup.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("SignUp!");

                //change the scene to signup
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../signup/signup.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                Main.window.setScene(scene);
            }
        });
    }


}
