package login;

import dbmodel.Nota;
import dbmodel.Notebook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.*;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller implements Initializable{
    @FXML
    private Button login;
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public Label login_warning;
    public Button signup;
    public TextField userid;
    public PasswordField passid;

    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;

        login.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Login!");
                System.out.println(userid.getText());
                System.out.println(passid.getText());

                //TEST BD
                DBConnect dbConnect = DBConnect.getInstance();
                dbConnect.insertNotebook("Test3","test5");
                ArrayList<Notebook> notebooks = dbConnect.getAllNotebooks("test5");
                for(Notebook nb : notebooks) {
                    ArrayList<Nota>  note = nb.getNotes();
                    for(Nota nota : note) {
                        System.out.println(nota.getId() + " " + nota.getNume() + " " + nota.getText() + " " + nota.getTitlu() + " " + nota.getData());
                       // dbConnect.insertTag("Tag3",nota);
                    }
                }
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                dbConnect.updateNote("New Text","Nume2");
                //dbConnect.insertNote("Titlu2","TEXT2",sqlDate,"Test1");

                //
                boolean checkAns = dbConnect.checkLogin(userid.getText(),passid.getText());

                if (checkAns == false)
                {
                    login_warning.setText("Username or Password incorrect!");
                    login_warning.setTextFill(Color.valueOf("#f53636"));
                }
                else
                {
                    login_warning.setText("Successful Login!");
                    login_warning.setTextFill(Color.valueOf("#69e760"));
                }
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
