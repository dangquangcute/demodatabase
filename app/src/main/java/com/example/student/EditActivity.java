package com.example.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText etId, etName;
    Button btSave, btCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        btSave = findViewById(R.id.btSave);
        btCancle = findViewById(R.id.btCancle);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");

        etId.setText(student.getId() + "");
        etName.setText(student.getName());

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(Integer.parseInt(etId.getText().toString())
                        , etName.getText().toString());

                Intent intent1 = getIntent();
                intent1.putExtra("student", student);
                setResult(2003,intent1);
                finish();
            }
        });

        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}