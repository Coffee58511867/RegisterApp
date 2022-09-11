package com.example.registerapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAll extends AppCompatActivity {
    ArrayList<User> UserModel = new ArrayList<User>() ;
    private dbManager dbm;
    ExamAdpter examAdpter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        recyclerView = findViewById(R.id.Exams);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        Cursor cursor = new dbManager(getApplicationContext()).readallUser();                  //Cursor To Load data From the database
        while (cursor.moveToNext()) {
                User model = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5));

            UserModel.add(model);
        }
        examAdpter = new ExamAdpter(UserModel);


        recyclerView.setAdapter(examAdpter);

    }
}
