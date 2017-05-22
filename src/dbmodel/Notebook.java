package dbmodel;

import java.sql.*;

import login.DBConnect;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by root on 5/9/17.
 */
public class Notebook {

    private String Nume;
    private int id;
    private String user;
    public ArrayList<Nota> notes;



    public Notebook(String Nume, int id, String user) throws SQLException {
        this.Nume   =   Nume;
        this.id     =   id;
        this.user   =   user;
        this.notes  =  new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public String getNume() {
        return this.Nume;
    }

    public String getUser() {
        return this.user;
    }

    public ArrayList<Nota> getNotes() {
        /*
        for(Nota nota : this.notes) {
            System.out.println(nota.getId() + " " + nota.getData() + " " + nota.getTitlu() + " " + nota.getText());
            System.out.print("Tags : ");
            for(Tag tag : nota.tags) {
                System.out.print(tag.getNume() + " ");
            }
        }
        */
        return this.notes;
    }

}
