package devmodels;


import dbmodel.Nota;
import dbmodel.Notebook;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import login.DBConnect;

public class UInote {
    public AnchorPane anc = new AnchorPane();
    public Label title = new Label();
    public TextArea text = new TextArea();

    public UInote(Nota note, VBox container){
        anc.prefWidthProperty().bind(container.widthProperty());

        //add notes to AnchorPane
        title.setText(note.getTitlu());
        title.setId("username");
        AnchorPane.setTopAnchor(title, 15.0);
        AnchorPane.setLeftAnchor(title, 15.0);

        //add text area
        text.setText(note.getText());
        text.setPrefHeight(300);
        text.prefWidthProperty().bind(anc.widthProperty());
        AnchorPane.setTopAnchor(text, 70.0);
        AnchorPane.setLeftAnchor(text, 15.0);
        AnchorPane.setRightAnchor(text, 15.0);

        //add save button
        Button savebtn = new Button();
        savebtn.setText("Save");
        savebtn.setId("savebtn");
        savebtn.setCursor(Cursor.HAND);
        AnchorPane.setTopAnchor(savebtn, 400.0);
        AnchorPane.setLeftAnchor(savebtn, 15.0);
        savebtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBConnect.updateNote(text.getText(), note.getNume());
            }
        });

        anc.getChildren().addAll(text, title, savebtn);
    }
}
