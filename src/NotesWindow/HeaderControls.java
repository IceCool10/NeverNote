package NotesWindow;


import dbmodel.Nota;
import dbmodel.Notebook;
import dbmodel.Tag;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import login.DBConnect;
import login.User;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HeaderControls implements Initializable{
    @FXML
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public CheckComboBox notebooksChoiceBox, tagsChoiceBox;
    public Label usernameLabel;
    private User user = DBConnect.getUser();

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;
        this.usernameLabel.setText(user.getUsername());
        ArrayList<Notebook> notebooks = DBConnect.getAllNotebooks(user.getUsername());
        for(Notebook nb : notebooks) {
            ArrayList<Nota>  note = nb.getNotes();
            for(Nota nota : note) {
                notebooksChoiceBox.getItems().add(nota.getNume());
                for(Tag tag : nota.tags) {
                    tagsChoiceBox.getItems().add(tag.getNume());
                }
            }
        }
    }
}
