package login;
import java.sql.DriverManager;
import java.sql.Connection;
import java.lang.StringBuilder;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ArrayList;
import dbmodel.*;

import static login.DBConnect.getAllNotes;

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
                    resultSet.close();
                    return false;
                } else {
                    User verifyUser = new User(Username, Password, resultSet.getString("Salt"));
                    if (verifyUser.getHash().equals(resultSet.getString("Password"))) {
                        System.out.println("Logged in");
                        resultSet.close();
                        return true;
                    } else {
                        System.err.println("Wrong Password!");
                        resultSet.close();
                        return false;
                    }

                }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public static void getAllNotes(ArrayList<Notebook> notebooks) throws SQLException {

        for (Notebook nb : notebooks) {
            PreparedStatement getNotes = conn.prepareStatement("SELECT * FROM Note WHERE Notebook = ?");
            getNotes.setString(1, nb.getNume());
            ResultSet Notes = getNotes.executeQuery();
            int index = 0;

            if (Notes.first()) {
                Notes.beforeFirst();
                while (Notes.next()) {
                    int id = Notes.getInt("Id");
                    String titlu = Notes.getString("Titlu");
                    String text  = Notes.getString("Text");
                    java.sql.Date date = Notes.getDate("Data");
                    String Notebook    = Notes.getString("Notebook");
                    System.out.println(id + " " + titlu + " " + text + " " + date + " " + Notebook);
                    nb.notes.add(new Nota(id,titlu,text,date,Notebook));
                    System.out.println(notebooks.get(index).notes.get(0).getId());
                    PreparedStatement getTags = conn.prepareStatement("SELECT * from TagInfo WHERE IdNota = ?");
                    getTags.setInt(1, Notes.getInt("id"));
                    ResultSet Tags = getTags.executeQuery();
                    if (Tags.next()) {
                        do {
                            Nota nt = new Nota();

                            notebooks.get(index).notes.get(index).tags.add(new Tag(Tags.getString("NumeTag")));//Taguri
                        } while (Tags.next());
                    } else {
                        System.err.println("No tags");
                    }
                    getTags.close();
                }
            }
            index++;
        }
    }
    public static boolean insertNotebook(String Nume,String Username) {
        try {
            PreparedStatement insertNotebook = conn.prepareStatement("INSERT INTO Notebook (Nume,user) VALUES (?,?)");
            insertNotebook.setString(1,Nume);
            insertNotebook.setString(2,Username);
            int result = insertNotebook.executeUpdate();
            if(result > 0) {
                System.out.println("Notebook inserted");
                return true;
            }
            else {
                System.err.println("Error on insertion");
                return false;
            }
        }
        catch (Exception ex) {
            System.out.println("Eroare");
            return false;
        }
    }

    public static ArrayList<Notebook> getAllNotebooks(String Username) {
        ArrayList<Notebook> notebooks = new ArrayList<>();
        try {
            PreparedStatement getNotebooks  =   conn.prepareStatement("SELECT * FROM Notebook WHERE user = ?");
            getNotebooks.setString(1,Username);
            ResultSet Notebooks             =   getNotebooks.executeQuery();
            if(Notebooks.next()) {
                Notebooks.beforeFirst();
                while (Notebooks.next()) {
                    String nume = Notebooks.getString("Nume");
                    System.out.println(nume);
                    notebooks.add(new Notebook(nume,Notebooks.getInt("id"),Username));
                }
                getNotebooks.close();
                getAllNotes(notebooks);
                return notebooks;
            }

            else {
                System.err.println("No notebooks");
                return null;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
/*


 */
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
                resultSet.close();
                return true;
            }
            else {
                System.err.println("Username already exists!");
                resultSet.close();
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
            if (conn != null) {
                return this.conn;
            }
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
            return conn;
        }
            catch(SQLException ex){
                System.out.println("An error occurred. Maybe user/password is invalid");
                ex.printStackTrace();
            }
        return null;
    }

}
