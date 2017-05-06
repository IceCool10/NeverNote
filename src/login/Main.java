package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;

public class Main extends Application {

    static Stage window;

    @Override
    public void start(Stage wind) throws Exception{
        window = wind; //initialize main window
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        window.setTitle("NeverNote");
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
        DBConnect dbconnect = DBConnect.getInstance();
        Connection conn = dbconnect.connect();
        dbconnect.interogate("SELECT * FROM Users where Username='test1'");
    }


    public static void main(String[] args) {
        User u1 = new User("U1","123456");
        launch(args);
    }
}
