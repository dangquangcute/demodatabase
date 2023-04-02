package com.example.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentVH>{

    ArrayList<Student> students;
    Listener listener;


    public StudentAdapter(ArrayList<Student> students, Listener listener) {
        this.students = students;
        this.listener = listener;

    }


    @NonNull
    @Override
    public StudentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_row, parent, false);
        return new StudentVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentVH holder, int position) {
        Student student = students.get(position);
        holder.tvId.setText(student.getId() + "");
        holder.tvName.setText(student.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClick(student);
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnEditClick(position, student);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnDelteClick(position, student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }



    class StudentVH extends RecyclerView.ViewHolder {
        TextView tvId, tvName;
        ImageView ivEdit, ivDelete;

        public StudentVH(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }



    public interface Listener {
        void OnItemClick(Student student);

        void OnEditClick(int pos, Student student);

        void OnDelteClick(int pos, Student student);
    }
}

