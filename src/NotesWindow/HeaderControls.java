package NotesWindow;


import com.sun.org.apache.bcel.internal.generic.POP;
import dbmodel.Nota;
import dbmodel.Notebook;
import dbmodel.Tag;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import login.DBConnect;
import login.Main;
import login.User;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HeaderControls implements Initializable{
    @FXML
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public CheckComboBox notebooksChoiceBox, tagsChoiceBox;
    public Label usernameLabel;
    public Button searchNotes, newNotebook, newNote;
    public static ScrollPane scrollPane;
    public static User user = DBConnect.getUser();
    public static CheckComboBox notebooksChoiceBoxClone, tagsChoiceBoxClone;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;

        //set label text to username
        usernameLabel.setText(user.getUsername());

        //copy ChoiceBox to static clones for use in other controllers
        notebooksChoiceBoxClone = notebooksChoiceBox;
        tagsChoiceBoxClone = tagsChoiceBox;

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

        //populate checkComboBoxes
        StaticMethods.PopulateCheckBox(user, notebooksChoiceBox, tagsChoiceBox);
    }
}
