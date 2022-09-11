package com.example.registerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email , password,name,surname,usertype,license;
    Button register;
    private Register user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        name = findViewById(R.id.names);
        surname = findViewById(R.id.lastname);
        license = findViewById(R.id.license);
        usertype = findViewById(R.id.usertype);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString().trim();
                String password1 = password.getText().toString();
                String name1 = name.getText().toString().trim();
                String surname1 = surname.getText().toString().trim();
                String license1 = license.getText().toString().trim();
                String userType1 = usertype.getText().toString().trim();


                if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1) ||
                        TextUtils.isEmpty(name1) || TextUtils.isEmpty(surname1)||
                        TextUtils.isEmpty(license1) || TextUtils.isEmpty(userType1)) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                user = new Register(email1, name1, surname1,userType1,license1, password1);
                registerNewUser(email1,password1);
            }
        });
    }
    private void registerNewUser(String email1, String password1){

        mAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Registration.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Registration Failed!!" + task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public  void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    public  void updateUI(FirebaseUser currentUser){
        if(user != null){
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference();
            String uid = currentUser.getUid();
            DatabaseReference userRef = databaseReference.child("users");
            userRef.child(uid).setValue(user);
        }
    }

}