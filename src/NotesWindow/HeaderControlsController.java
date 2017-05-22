package NotesWindow;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import login.DBConnect;
import login.Main;
import login.User;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HeaderControlsController implements Initializable{
    @FXML
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public CheckComboBox notebooksChoiceBox, tagsChoiceBox;
    public Label usernameLabel;
    public Button searchNotes, newNotebook, newNote;

    private User user = DBConnect.getUser();

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;

        //set label text to username
        this.usernameLabel.setText(user.getUsername());

        //create new notebook event
        newNotebook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //create new window
                Stage createNotebookWindow = new Stage();
                createNotebookWindow.setTitle("Create a new notebook for user " + user.getUsername());

                //load scene
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../NotesWindow/newNotebook.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                createNotebookWindow.setScene(scene);

                createNotebookWindow.show();
            }
        });

        //create new noteevent
        newNote.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //create new window
                Stage createNoteWindow = new Stage();
                createNoteWindow.setTitle("Create a new note for user " + user.getUsername());

                //load scene
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../NotesWindow/newNote.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                createNoteWindow.setScene(scene);

                createNoteWindow.show();
            }
        });
    }
}
