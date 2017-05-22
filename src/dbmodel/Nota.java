package dbmodel;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by root on 5/14/17.
 */
public class Nota {

        public ArrayList<Tag> tags;


        private int id;
        private String titlu;
        private String text;
        private String nume;
        private Date data;
        private String Notebook;

        public Nota() {
            this.tags   =   new ArrayList<>();
        }

        public Nota(int id, String nume,String titlu, String text, Date data, String Notebook) {
            this.id         =   id;
            this.nume       =   nume;
            this.titlu      =   titlu;
            this.text       =   text;
            this.data       =   data;
            this.Notebook   =   Notebook;
            this.tags       =   new ArrayList<>();
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

        public Date getData() {
            return this.data;
        }

        public String getNume() {return this.nume;}

        public String getNotebook() {
            return this.Notebook;
        }

    }
