package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 450, 450);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        DBConnect dbconnect = DBConnect.getInstance();
        Connection conn = dbconnect.connect();
        dbconnect.interogate("SELECT * FROM Users where Username='test1'");
    }


    public static void main(String[] args) {
        User u1 = new User("U1","123456");
        launch(args);
    }
}
