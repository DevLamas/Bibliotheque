package com.example.bibliotheque.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bibliotheque.Database.DatabaseHandler;
import com.example.bibliotheque.Object.ObjectBook;

public class TableBook extends DatabaseHandler {
    public TableBook(Context context) {
        super(context);
    }

    public boolean create(ObjectBook objectBook){
        ContentValues values = new ContentValues();
        values.put("Name", objectBook.getName());
        values.put("ISBN", objectBook.getISBN());
        values.put("Author", objectBook.getAuthor());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessFul = db.insert("books", null, values)>0;
        db.close();
        return createSuccessFul;
    }
}
