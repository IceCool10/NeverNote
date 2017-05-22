package NotesWindow;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.*;
import java.net.URL;
import javafx.scene.*;
import login.DBConnect;
import login.User;

import java.util.ResourceBundle;

public class NewNotebookController implements Initializable {
    @FXML
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public TextField notebookName;
    public Button createNotebook;
    public Label warningMessage;
    private User user = DBConnect.getUser();

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;

        createNotebook.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                DBConnect.insertNotebook(notebookName.getText(),user.getUsername());
            }
        });



    }


}
