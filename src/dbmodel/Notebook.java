package dbmodel;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by root on 5/9/17.
 */
public class Notebook {

    private String Nume;
    private int id;
    private String user;
    private ArrayList<Nota> notes;

    public class Nota {

        private ArrayList<Tag> tags;

        public class Tag {
            private String Nume;

            public Tag(String Nume) {
                this.Nume   =   Nume;
            }

            public String getNume() {
                return this.Nume;
            }
        }

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
