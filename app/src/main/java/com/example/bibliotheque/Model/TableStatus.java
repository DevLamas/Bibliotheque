package com.example.bibliotheque.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bibliotheque.Database.DatabaseHandler;
import com.example.bibliotheque.Object.ObjectStatus;

import java.util.ArrayList;
import java.util.List;

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
    public List<ObjectStatus> read() {
        List<ObjectStatus> recordsList = new ArrayList<ObjectStatus>();

        String sql = "SELECT * FROM status ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getColumnIndex("Id");
                String StatusName =
                        cursor.getString(cursor.getColumnIndex("Name"));
                ObjectStatus objectStatus = new ObjectStatus();
                objectStatus.setId(id);
                objectStatus.setName(StatusName);
                recordsList.add(objectStatus);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return recordsList;
    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;
        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("status", "id = '" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM status";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }


    public ObjectStatus readSingleRecord(int studentId) {
        ObjectStatus ObjectStatus = null;
        String sql = "SELECT * FROM status WHERE id = " + studentId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex("Id");
            String studentName =
                    cursor.getString(cursor.getColumnIndex("Name"));

            ObjectStatus = new ObjectStatus();
            ObjectStatus.setId(id);
            ObjectStatus.setName(studentName);

        }
        cursor.close();
        db.close();
        return ObjectStatus;
    }

    public boolean update(ObjectStatus ObjectStatus) {
        ContentValues values = new ContentValues();
        values.put("Name", ObjectStatus.getName());

        String where = "Id = ?";
        String[] whereArgs = { Integer.toString(ObjectStatus.getId()) };
        SQLiteDatabase db = this.getWritableDatabase();
        boolean updateSuccessful = db.update("status", values, where, whereArgs) > 0;
        db.close();
        return updateSuccessful;
    }
}
