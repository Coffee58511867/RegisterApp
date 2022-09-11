package com.example.registerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    Button register, login;
    EditText username, password;
    private FirebaseAuth mAuth;
    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgot = findViewById(R.id.forgot);
        mAuth = FirebaseAuth.getInstance();
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Forgot.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email1, password1;
                email1 = username.getText().toString().trim();
                password1 = password.getText().toString();

                mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                           // verifyUserType();
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(MainActivity.this, Home.class);
                            startActivity(myIntent);



                        } else {
                            Toast.makeText(getApplicationContext(), "Login failed, Password or username is incorrect", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });
    }
    private void verifyUserType(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String user = currentUser.getUid();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("users").child(user);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userType = dataSnapshot.child("userType").getValue(String.class);
                if(userType != null && userType.equals("Student")){
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(MainActivity.this, Home.class);
                    startActivity(myIntent);
                }else if(userType != null && userType.equals("Lecture")){
//                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
//                   // Intent myIntent = new Intent(MainActivity.this, Lecture.class);
//                    startActivity(myIntent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "You are not authorized", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}