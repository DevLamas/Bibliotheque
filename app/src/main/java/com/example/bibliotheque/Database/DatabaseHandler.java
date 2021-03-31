package com.example.bibliotheque.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "Bibliotheque";
    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatus =
            "CREATE TABLE status " +
                "( " +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Name TEXT" +
                ") ; ";

        String sqlUsers =
            "CREATE TABLE users " +
                "( " +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Lastname TEXT, "+
                    "Firstname TEXT, "+
                    "Email TEXT, "+
                    "Password TEXT, "+
                    "StatusId INTEGER, " +
                    "FOREIGN KEY (StatusId) REFERENCES status(Id) " +
                "); ";


        String sqlBooks =
            "CREATE TABLE books " +
                "( " +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Name TEXT, "+
                    "ISBN TEXT, "+
                    "Author TEXT" +
                "); ";

        String sqlLoan =
            " CREATE TABLE loan " +
                "( " +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "UserId INTEGER, "+
                    "BookId INTEGER, "+
                    "BorrowingDate  TEXT, " +
                    "RenderDate TEXT, " +
                    "FOREIGN KEY (UserId) REFERENCES users(Id)," +
                    "FOREIGN KEY (BookId) REFERENCES book(Id) " +
                ") ; ";

        String sqlInsertStatus =
            "INSERT INTO status" +
                "(" +
                    "Name" +
                ")" +
                "VALUES" +
                    "('Administratuer')," +
                    "('Professionnel')," +
                    "('Apprenti')," +
                    "('Etudiant')";
        //String sqlInsertStatus = "INSERT INTO status VALUES ('Administratuer'), ('Professionnel'),('Apprenti'),('Etudiant');";

        String sqlInsertUser =
            "INSERT INTO users" +
                "(" +
                    "Lastname," +
                    "Firstname," +
                    "Email," +
                    "Password," +
                    "StatusId" +
                ")" +
                "VALUES" +
                    "('Admin'," +
                    "'Admin'," +
                    "'admin@admin.fr'," +
                    "'admin'," +
                    "1);";
        //String sqlInsertUser = "INSERT INTO users VALUES ('DUMOND', 'Julien', 'admin@admin.fr', 'admin', 1);";
        db.execSQL(sqlStatus);
        db.execSQL(sqlUsers);
        db.execSQL(sqlBooks);
        db.execSQL(sqlLoan);
        db.execSQL(sqlInsertStatus);
        db.execSQL(sqlInsertUser);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlUsers = "DROP TABLE IF EXISTS users";
        String sqlStatus = "DROP TABLE IF EXISTS status";
        String sqlBooks = "DROP TABLE IF EXISTS books";
        String sqlLoan = "DROP TABLE IF EXISTS loan";
        db.execSQL(sqlStatus);
        db.execSQL(sqlUsers);
        db.execSQL(sqlBooks);
        db.execSQL(sqlLoan);
        onCreate(db);
    }
}
