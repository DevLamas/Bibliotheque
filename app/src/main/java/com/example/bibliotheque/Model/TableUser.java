package com.example.bibliotheque.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bibliotheque.Database.DatabaseHandler;
import com.example.bibliotheque.Object.ObjectUser;

public class TableUser extends DatabaseHandler {
    public TableUser(Context context) {
        super(context);
    }

    public boolean create(ObjectUser objectUser){
        ContentValues values = new ContentValues();
        values.put("Lastname", objectUser.getLastname());
        values.put("Firstname", objectUser.getFirstname());
        values.put("Email", objectUser.getEmail());
        values.put("Password", objectUser.getPassword());
        values.put("StatusId", objectUser.getStatusid());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessFul = db.insert("users", null, values)>0;
        db.close();
        return createSuccessFul;
    }


    public ObjectUser findLoginAndPassword(String login, String password){
        ObjectUser objectUser = null;

        String sql = "SELECT * FROM users WHERE Email = '" + login + "' AND Password = '" + password + "'";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("Id"));
            String userFirstname = cursor.getString(cursor.getColumnIndex("Firstname"));
            String userLastname = cursor.getString(cursor.getColumnIndex("Lastname"));
            String userEmail = cursor.getString(cursor.getColumnIndex("Email"));
            //String userPassword = cursor.getString(cursor.getColumnIndex("Password"));
            int userStatutId = cursor.getInt(cursor.getColumnIndex("StatusId"));

            objectUser = new ObjectUser();
            objectUser.setId(id);
            objectUser.setFirstname(userFirstname);
            objectUser.setLastname(userLastname);
            objectUser.setEmail(userEmail);
            objectUser.setStatusid(userStatutId);
        }
        cursor.close();
        db.close();

        return objectUser;
    }
}
