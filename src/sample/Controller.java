package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller implements Initializable{
    @FXML
    private Button myButton;
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public Button signup;
    public TextField userid;
    public PasswordField passid;

    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;
        assert myButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";
        assert signup   != null : "fx:id=\"signup\" was not injected: check your FXML file 'simple.fxml'.";

        myButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Buton!");
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
                DBConnect dbConnect = DBConnect.getInstance();
                dbConnect.Signup(userid.getText(),passid.getText()); // tb inlocuit cu input-ul din pagina de signup
            }
        });
    }


}
