package NotesWindow;


import dbmodel.Notebook;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.*;
import java.net.URL;
import javafx.scene.*;
import javafx.scene.paint.Color;
import login.DBConnect;
import login.User;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewNotebookController implements Initializable {
    @FXML
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public TextField notebookName;
    public Button createNotebook;
    public Label warningMessage;
    private User user = DBConnect.getUser();

    boolean CheckNotebook(String x){
        ArrayList<Notebook> notebooks = DBConnect.getAllNotebooks(user.getUsername());

        boolean ans = false;
        for (Notebook nb : notebooks)
            if (nb.getNume().equals(x) == true)
                ans = true;

        return ans;
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;

        createNotebook.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                boolean check = CheckNotebook(notebookName.getText());

                if (check == false) {
                    warningMessage.setText("Notebook " + notebookName.getText() + " was created successfully!");
                    warningMessage.setTextFill(Color.valueOf("#69e760"));

                    DBConnect.insertNotebook(notebookName.getText(), user.getUsername());

                    //repopulate
                    StaticMethods.PopulateCheckBox(HeaderControls.user, HeaderControls.notebooksChoiceBoxClone, HeaderControls.tagsChoiceBoxClone);
                }
                else {
                    warningMessage.setText("Notebook already exists!");
                    warningMessage.setTextFill(Color.valueOf("#f53636"));
                }
            }
        });



    }


}
