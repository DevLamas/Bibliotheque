package com.example.bibliotheque.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bibliotheque.Database.DatabaseHandler;
import com.example.bibliotheque.Object.ObjectLoan;

public class TableLoan extends DatabaseHandler {
    public TableLoan(Context context) {
        super(context);
    }

    public boolean create(ObjectLoan objectLoan){
        ContentValues values = new ContentValues();
        values.put("UserId", objectLoan.getUserId());
        values.put("BookId", objectLoan.getBookId());
        values.put("BorrowingDate", objectLoan.getBorrowingDate());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessFul = db.insert("loan", null, values)>0;
        db.close();
        return createSuccessFul;
    }
}
