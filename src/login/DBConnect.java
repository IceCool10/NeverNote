package login;
import java.sql.DriverManager;
import java.sql.Connection;
import java.lang.StringBuilder;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by C on 3/25/2017.
 */
public class DBConnect {
    private static String databaseURL = "jdbc:mysql://52.166.74.242:3306/Nevernote";
    private static String user = "luckystrike";
    private static String password = "madafaka123";
    private static Connection conn = null;
    private static DBConnect instance = null;

    //SINGLETON

    private DBConnect() { }

    public static DBConnect getInstance() {
        if ( instance == null )
            return new DBConnect();
        return instance;
    }

    //verificam daca credentialele de login au fost corecte
    public boolean checkLogin(String Username, String Password) {
        //check if username and password are not empty
        if (Username.length() == 0 || Password.length() == 0)
        {
            System.err.println("Username or Password are empty!");
            return false;
        }

        try {
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT Password, Salt FROM Users WHERE Username = ? LIMIT 1");
                preparedStatement.setString(1, Username);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (!resultSet.first()) {
                    System.err.println("Username not found!");
                    return false;
                } else {
                    User verifyUser = new User(Username, Password, resultSet.getString("Salt"));
                    if (verifyUser.getHash().equals(resultSet.getString("Password"))) {
                        System.out.println("Logged in");
                        return true;
                    } else {
                        System.err.println("Wrong Password!");
                        return false;
                    }

                }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    //functie de signup
    public boolean Signup(String Username,String Password,String FirstName,String Lastname,String Email,LocalDate birthDate) {
        try {
            PreparedStatement preparedStatementVerify = conn.prepareStatement("SELECT * FROM Users WHERE Username = ? LIMIT 1");
            preparedStatementVerify.setString(1, Username);
            ResultSet resultSet = preparedStatementVerify.executeQuery(); // verificam daca exista userul in baza de date

            if (!resultSet.first()) {
                User newUser = new User(Username, Password);
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Users (Username,Password,Salt,firstname,lastname,birth,email) Values (?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1,Username);
                preparedStatement.setString(2,newUser.getHash());
                preparedStatement.setString(3,new StringBuilder(newUser.getSalt().toString()).reverse().toString());
                preparedStatement.setString(4,FirstName);
                preparedStatement.setString(5,Lastname);
                preparedStatement.setString(6,birthDate.toString());
                preparedStatement.setString(7,Email);
                preparedStatement.executeUpdate();
                return true;
            }
            else {
                System.err.println("Username already exists!");
                return false;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }


    // folosit pentru query-uri exacte fara parametri
    public ResultSet interogate(String query) throws Exception {

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println(resultSet.first());
        if(!resultSet.first())
            return null;
        if ( conn != null ) {
            /**
            while(resultSet.next()) {
                System.out.println(resultSet.getRow());
                System.out.println("Username : " + resultSet.getString("Username"));
                System.out.println("Password : " + resultSet.getString("Password"));
            }
             **/
        }
        else {
            System.out.println("Connection error!");
        }
        return resultSet;
    }

    //Constructor
    public Connection connect() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            }
            catch(ClassNotFoundException ex){
                System.out.println("Could not find database driver class");
                ex.printStackTrace();
            }
            catch(Exception ex) {
                System.out.println("Error");
                ex.printStackTrace();
            }
            conn = DriverManager.getConnection(databaseURL, user, password);
            if (conn != null) {
                System.out.println("Connected to the database");
            }
            return conn;
        }
            catch(SQLException ex){
                System.out.println("An error occurred. Maybe user/password is invalid");
                ex.printStackTrace();
            }
        return null;
    }
}
