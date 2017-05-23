package NotesWindow;


import dbmodel.Nota;
import dbmodel.Notebook;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import login.DBConnect;
import login.User;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;

public class NewNoteController implements Initializable {
    @FXML
    private URL fxmlFileLocation;
    private ResourceBundle resource;
    public TextField noteTitle;
    public TextArea noteText;
    public ComboBox notebookChoice;
    public ComboBox tagsSetter;
    public Button submitNote;
    public Label warningMessage;

    private User user = DBConnect.getUser();

    private boolean CheckNote(){

        if (noteTitle.getText().length() == 0) return false;

        //check if notebook was chosen
        if (notebookChoice.getSelectionModel().isEmpty() == true)
            return false;

        //check if notebook name alreadt exists
        ArrayList<Notebook> notebooks = DBConnect.getAllNotebooks(user.getUsername());
        for (Notebook nb : notebooks){
            ArrayList<Nota>  notes = nb.getNotes();
            for (Nota nota : notes)
                if (noteTitle.equals(nota.getNume()) == true)
                    return false;
        }

        return true;
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resource) {
        this.fxmlFileLocation = fxmlFileLocation;
        this.resource = resource;

        //populate CheckComboBox with notebooks
        ArrayList<Notebook> notebooks = DBConnect.getAllNotebooks(user.getUsername());
        for (Notebook nb : notebooks)
            notebookChoice.getItems().add(nb.getNume());

        submitNote.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                boolean check = CheckNote();

                if (check == true) {
                    warningMessage.setText("Note created!");
                    warningMessage.setTextFill(Color.valueOf("#69e760"));
                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    String notebook = notebookChoice.getSelectionModel().getSelectedItem().toString();
                    DBConnect.insertNote(noteTitle.getText(), noteTitle.getText(), noteText.getText(), sqlDate, notebook);

                    //insert tag for note
                    Object item = tagsSetter.getSelectionModel().getSelectedItem();
                    if (item != null && item.toString().length() > 0)
                        try {
                            System.out.println(item.toString());
                            DBConnect.insertTag(item.toString(), DBConnect.getNota(noteTitle.getText()));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                }
                else{
                    warningMessage.setText("Invalid Note!");
                    warningMessage.setTextFill(Color.valueOf("#f53636"));
                }
            }


        });


    }
}
