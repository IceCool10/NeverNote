package dbmodel;

import java.time.LocalDate;

/**
 * Created by root on 5/9/17.
 */
public class Nota {
    private int id;
    private String titlu;
    private String text;
    private LocalDate data;
    private String Notebook;

    public Nota(int id, String titlu, String text, LocalDate data, String Notebook) {
        this.id         =   id;
        this.titlu      =   titlu;
        this.text       =   text;
        this.data       =   data;
        this.Notebook   =   Notebook;
    }

    public int getId() {
        return this.id;
    }

    public String getTitlu() {
        return this.titlu;
    }

    public String getText() {
        return this.text;
    }

    public LocalDate getData() {
        return this.data;
    }

    public String getNotebook() {
        return this.Notebook;
    }

}
