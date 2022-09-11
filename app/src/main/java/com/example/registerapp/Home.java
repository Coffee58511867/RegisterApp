package com.example.registerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    private DatabaseReference myUsersDatabase;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button view = findViewById(R.id.viewProfile);
        Button update = findViewById(R.id.editProfile);
        Button delete = findViewById(R.id.deleteProfile);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent course = new Intent(Home.this, com.example.registerapp.ViewProfile.class);
                startActivity(course);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cour= new Intent(Home.this, com.example.registerapp.Update.class);
                startActivity(cour);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.view:
                Intent course = new Intent(Home.this,ViewProfile.class);
                startActivity(course);
                break;
            case R.id.update:
                Intent cour= new Intent(Home.this, Update.class);
                startActivity(cour);
                break;
            case R.id.delete:
              deleteUser();
                break;
            case R.id.register:
                Intent reg= new Intent(Home.this, RegisterUsers.class);
                startActivity(reg);
                break;
            case R.id.viewall:
                Intent re= new Intent(Home.this, ViewAll.class);
                startActivity(re);
                break;
            case R.id.logout:
               logout();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteUser(){
        mAuth = FirebaseAuth.getInstance();
        myUsersDatabase= FirebaseDatabase.getInstance().getReference().child("users");
        myUsersDatabase.child(mAuth.getCurrentUser().getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Records deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Record not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void logout(){                                  //logout method
        final AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
        dialog.setTitle("Logout?");
        dialog.setMessage("Are you sure you want to log out");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent prof = new Intent(Home.this,MainActivity.class);
                startActivity(prof);

            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });
        dialog.create().show();
    }

}