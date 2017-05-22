package NotesWindow;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class NewNotebookController implements Initializable {
    @FXML
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public TextField notebookName;
    public Button createNotebook;
    public Label warningMessage;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;



    }
}
