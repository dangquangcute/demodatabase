package com.example.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText etId, etName;
    Button btSave, btCancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        btSave = findViewById(R.id.btSave);
        btCancle = findViewById(R.id.btCancle);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.length() == 0 || etId.length() == 0) {
                    Toast.makeText(AddActivity.this, "Điền đầy đủ thông tin đi tk cc", Toast.LENGTH_SHORT).show();
                } else {
//                    Student student = new Student(Integer.parseInt(etId.getText().toString())
//                            , etName.getText().toString());
                    Student student = new Student(etName.getText().toString());
                    Intent intent = getIntent();
                    intent.putExtra("student", student);
                    setResult(2004, intent);
                    finish();
                }
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