package com.example.bibliotheque.Model;

import android.content.ContentValues;
import android.content.Context;
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
}
