package com.example.student;

import android.app.Application;

public class App extends Application {

    StudentDB studentDB;

    @Override
    public void onCreate() {
        super.onCreate();
        studentDB = new StudentDB(this);
        studentDB.createTable();

        if(studentDB.countStudent() == 0){
            studentDB.insertStudent("nho");
            studentDB.insertStudent("Triều");
            studentDB.insertStudent("Đức");
            studentDB.insertStudent("Ái");
            studentDB.insertStudent("siuuuuuuuuuu");
        }
    }

}
