package com.example.registerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    Button update;
    TextView email,name,surname,license;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        update = findViewById(R.id.editProfile);
        databaseReference  = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email2);
        surname = findViewById(R.id.surname);
        name= findViewById(R.id.name);
        license = findViewById(R.id.id2);

        viewData();

        update.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent menu = new Intent(ViewProfile.this, Update.class);
                startActivity(menu);
            }
        });

    }
    private void viewData(){

        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                String  email1 = snapshot.child("email1").getValue().toString();
                String surname1 = snapshot.child("surname").getValue().toString();
                String name1 = snapshot.child("name").getValue().toString();
                String license1 = snapshot.child("license").getValue().toString();

                email.setText(email1);
                surname.setText(surname1);
                name.setText(name1);
                license.setText(license1);






            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Nothing"+databaseError,Toast.LENGTH_SHORT).show();

            }
        });
    }
}