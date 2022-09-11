package com.example.registerapp;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Update extends AppCompatActivity {

    EditText Name,Surname,licence,Email;
    private FirebaseAuth mAuth;
    private DatabaseReference myUsersDatabase;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        myUsersDatabase= FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();
        update = findViewById(R.id.update);


        Email = findViewById(R.id.username);
        Name = findViewById(R.id.name);
        licence =  findViewById(R.id.national);
        Surname= findViewById(R.id.surname);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = Email.getText().toString().trim();
                String Name1 = Name.getText().toString();
                String id = licence.getText().toString();
                String Surname1 = Surname.getText().toString();

                if(TextUtils.isEmpty(email1)){
                    Toast.makeText(getApplicationContext(),"Email Address is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Name1)){
                    Toast.makeText(getApplicationContext(),"First Name is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(id)){
                    Toast.makeText(getApplicationContext(),"National id is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Surname1)){
                    Toast.makeText(getApplicationContext(),"Surname is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String,Object> hashMap = new HashMap <>();
                hashMap.put("email1",email1);
                hashMap.put("name",Name1);
                hashMap.put("license",id);
                hashMap.put("surname",Surname1);



                myUsersDatabase.child(mAuth.getCurrentUser().getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Failed to Update",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}