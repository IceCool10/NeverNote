package signup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.Main;
import login.DBConnect;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by archazey on 5/7/17.
 */
public class Controller implements Initializable{
    @FXML
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public Button backButton;
    public Button registerButton;
    public TextField FirstName;
    public TextField LastName;
    public TextField userid;
    public PasswordField passid;
    public DatePicker BirthDate;
    public TextField email;


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
        registerButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                String _firstNuma = FirstName.getText();
                String _lastName = LastName.getText();
                String _userId = userid.getText();
                String _passId = passid.getText();
                String _email = email.getText();
                LocalDate _birthDate = BirthDate.getValue();

                DBConnect dbConnect = DBConnect.getInstance();
                //CheckAns in caz de eroare pe care ai vrea sa o arunci acum
                // nu stiu in ce ordine sa fie parametri, iti permuti in functie
                boolean checkAns = dbConnect.Signup(_userId,_passId,_firstNuma,_lastName,_email,_birthDate);
                if (checkAns==false)
                {
                    System.out.println("Datele oferite nu sunt bune");
                }
                else {
                    System.out.println("Singed up!!!");
                }
            }
        });


    }
}
