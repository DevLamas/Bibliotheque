package com.example.bibliotheque.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "Biblotheque";
    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "";
        sql =
            "CREATE TABLE status " +
                "( " +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Name TEXT" +
                ") ; ";

        sql +=
            " CREATE TABLE users " +
                "( " +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Lastname TEXT, "+
                    "Firstname TEXT, "+
                    "Email TEXT, "+
                    "Password TEXT, "+
                    "StatusId INTEGER, " +
                    "FOREIGN KEY (StatusId) REFERENCES status(Id) " +
                ") ; ";


        sql +=
            " CREATE TABLE books " +
                "( " +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Name TEXT, "+
                    "ISBN TEXT, "+
                    "Author TEXT" +
                ") ; ";

        sql +=
            " CREATE TABLE loan " +
                "( " +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "UserId INTEGER, "+
                    "BookId INTEGER, "+
                    "BorrowingDate  TEXT, " +
                    "RenderDate TEXT, " +
                    "FOREIGN KEY (UserId) REFERENCES users(Id)" +
                    "FOREIGN KEY (BookId) REFERENCES book(Id) " +
                ") ; ";

        sql +=
            "INSERT INTO status" +
                "(" +
                    "Name" +
                ")" +
                "VALUES" +
                    "('Administratuer')," +
                    "('Professionnel')," +
                    "('Apprenti')," +
                    "('Etudiant')";

        sql +=
            "INSERT INTO users" +
                "(" +
                    "Lastname" +
                    "Firstname" +
                    "Email" +
                    "Password" +
                    "StatusId" +
                ")" +
                "VALUES" +
                "(" +
                    "'Admin'" +
                    "'Admin'" +
                    "'admin@admin.fr'" +
                    "'admin'" +
                    "1" +
                ")";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS students";
        db.execSQL(sql);
        onCreate(db);
    }
}
