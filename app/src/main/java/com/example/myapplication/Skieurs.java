package com.example.myapplication;

public class Skieurs {

        private int id;
        private String login;
        private String password;
        private String mail;

        public static final String TABLE_NAME_SKIEURS = "Skieurs";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_MAIL = "mail";



        //Création de la table
        // Create table SQL query
        public static final String CREATE_TABLE_SKIEURS =
                "CREATE TABLE "+ TABLE_NAME_SKIEURS +"( "
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_LOGIN + " TEXT,"
                        + COLUMN_PASSWORD + " TEXT,"
                        + COLUMN_MAIL + " TEXT"
                        + " )";

        //Constructeur


        public Skieurs(int id, String login, String password, String mail){
            this.id = id;
            this.login = login;
            this.password = password;
            this.mail = mail;
        }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
