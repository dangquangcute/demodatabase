package com.example.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class StudentDB {
    String dbName = "StudentDB.db";
    //Giúp tạo ra CSDL
    Context context;
    SQLiteDatabase db;

    public StudentDB(Context context) {
        this.context = context;
    }

    //code first
    //Hàm này sinh ra CSDL
    public SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS tblStudent(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT);";
        db = openDB();
        db.execSQL(sql);
        db.close();
    }

    public int countStudent() {
        String sql = "SELECT * FROM tblStudent";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        return count;
    }
    //CRUD - INSERT - SELECT - UPDATE - DELETE

    //SELECT * FROM
    //SELECT * FROM WHERE

    public ArrayList<Student> getStudnets() {
        ArrayList<Student> tmp = new ArrayList<>();
        String sql = "SELECT * FROM tblStudent";
        db = openDB();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Student student = new Student(id, name);
            tmp.add(student);
        }
        db.close();
        return tmp;
    }

    public void insertStudent(String name) {
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        db.insert("tblStudent", null, cv);
        db.close();
    }

    public void updateStudent(int id, String newName) {
        db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("NAME", newName);
        db.update("tblStudent", cv, "ID=" + id, null);
        db.close();

    }

    public void delete(int id) {
        db = openDB();
        db.delete("tblStudent", "ID=" + id, null);
        db.close();
    }
}
