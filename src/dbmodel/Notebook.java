package dbmodel;

/**
 * Created by root on 5/9/17.
 */
public class Notebook {

    private String Nume;
    private int id;
    private String user;

    public Notebook(String Nume, int id, String user) {
        this.Nume   =   Nume;
        this.id     =   id;
        this.user   =   user;
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

}
