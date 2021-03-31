package com.example.bibliotheque.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bibliotheque.Database.DatabaseHandler;
import com.example.bibliotheque.Object.ObjectStatus;

public class TableStatus extends DatabaseHandler {
    public TableStatus(Context context) {
        super(context);
    }

    public boolean create(ObjectStatus objectStatus){
        ContentValues values = new ContentValues();
        values.put("Name", objectStatus.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessFul = db.insert("status", null, values)>0;
        db.close();
        return createSuccessFul;
    }
}
