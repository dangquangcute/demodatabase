package com.example.student;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.MediaRouteButton;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StudentAdapter.Listener {

    RecyclerView rvStudents;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;
    int index;

    StudentDB studentDB;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == 2003) {
                Student st = (Student) result.getData().getSerializableExtra("student");
                studentDB.updateStudent(st.getId(), st.getName());
                students.clear();
                students.addAll(studentDB.getStudnets());
                studentAdapter.notifyDataSetChanged();
            }
            if (result.getResultCode() == 2004) {
                Student st = (Student) result.getData().getSerializableExtra("student");
                studentDB.insertStudent(st.getName());
                students.clear();
                students.addAll(studentDB.getStudnets());
                studentAdapter.notifyDataSetChanged();
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDB = new StudentDB(MainActivity.this);

        rvStudents = findViewById(R.id.rvStudents);

        students = studentDB.getStudnets();


        studentAdapter = new StudentAdapter(students, this);
        rvStudents.setAdapter(studentAdapter);
        rvStudents.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ;
        //Trang trí dòng ngang
        rvStudents.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnAdd:
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                launcher.launch(intent);
                break;
            case R.id.mnSearch:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnItemClick(Student student) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(student.getName());
        builder.setMessage("Hello " + student.getName());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void OnEditClick(int pos, Student student) {
        index = pos;
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("student", student);
        launcher.launch(intent);
    }

    @Override
    public void OnDelteClick(int pos, Student student) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Bạn có chắc chắn muốn xóa");
        builder.setMessage("ID: " + student.getId() + "\n Name: " + student.getName());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                studentDB.delete(student.getId());
                students.clear();
                students.addAll(studentDB.getStudnets());
                studentAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}