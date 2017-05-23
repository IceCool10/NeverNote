package NotesWindow;


import dbmodel.Nota;
import dbmodel.Notebook;
import dbmodel.Tag;
import login.DBConnect;
import login.User;
import org.controlsfx.control.CheckComboBox;

import java.util.ArrayList;
import java.util.HashMap;

public class StaticMethods {

    static void PopulateNotebookCheckBox(User user, CheckComboBox notebooksChoiceBox){
        notebooksChoiceBox.getItems().remove(0, notebooksChoiceBox.getItems().size());

        ArrayList<Notebook> notebooks = DBConnect.getAllNotebooks(user.getUsername());
        HashMap<String, Boolean> H = new HashMap<>();
        for(Notebook nb : notebooks) notebooksChoiceBox.getItems().add(nb.getNume());
    }

    static void PopulateTagCheckBox(User user, CheckComboBox tagsChoiceBox){
        tagsChoiceBox.getItems().remove(0, tagsChoiceBox.getItems().size());

        ArrayList<Notebook> notebooks = DBConnect.getAllNotebooks(user.getUsername());
        HashMap<String, Boolean> H = new HashMap<>();
        for(Notebook nb : notebooks) {
            ArrayList<Nota>  note = nb.getNotes();
            for(Nota nota : note) {
                for(Tag tag : nota.tags) {
                    H.put(tag.getNume(), true);
                }
            }
        }

        for (String x : H.keySet()) tagsChoiceBox.getItems().add(x);
    }
}
