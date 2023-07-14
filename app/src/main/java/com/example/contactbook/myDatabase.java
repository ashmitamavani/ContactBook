package com.example.contactbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDatabase extends SQLiteOpenHelper{
    public myDatabase( Context context) {
        super(context, "CONTACTBOOK", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE Contacts (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, NUMBER TEXT, IMGURI TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddContacts(String name, String surname, String number, String imagePath) {
        String query = "INSERT INTO Contacts(NAME,SURNAME,NUMBER,IMGURI) VALUES ('"+name+"','"+surname+"','"+number+"','"+imagePath+"')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public Cursor ShowContacts() {
        String query = "SELECT * FROM Contacts";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public void UpdateContact(int id,String name, String surname, String number) {
        String query = "UPDATE Contacts set NAME='"+name+"',SURNAME='"+surname+"',NUMBER='"+number+"' WHERE ID="+id+"";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public void DeleteContact(int id) {
        String query = "DELETE FROM Contacts WHERE ID="+id+"";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }
}
