package com.example.registerapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegisterUsers extends AppCompatActivity {
    EditText email, name, surname, phone, national;
    TextView date;
    Button register, view , delete , update;
    dbManager mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_users);

        email = findViewById(R.id.username);

        register = findViewById(R.id.register);
        name = findViewById(R.id.names);
        surname = findViewById(R.id.lastname);
        national = findViewById(R.id.license);
        phone = findViewById(R.id.phone);
        view = findViewById(R.id.view);
        delete = findViewById(R.id.delete);
        date = findViewById(R.id.date);
        update = findViewById(R.id.update);
        mdb = new dbManager(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterUsers.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "-" + (month + 1) + "-" + year);                             //sets the selected date as test for button
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Surname = surname.getText().toString();
                String Nationa = national.getText().toString();
                String Phone = phone.getText().toString();
                String Email = email.getText().toString();
                String Date = date.getText().toString();


                if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Surname) || TextUtils.isEmpty(Nationa) || TextUtils.isEmpty(Phone) || TextUtils.isEmpty(Email)) {
                    Toast.makeText(RegisterUsers.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Date.equals("SELECT DATE")){
                    Toast.makeText(RegisterUsers.this,"Please select date of registration", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    boolean isInserted = mdb.insert(Name, Surname, Nationa, Phone, Email, Date);
                    if (isInserted == true) {
                        Toast.makeText(getApplicationContext(), "User Registration Successfully", Toast.LENGTH_LONG).show();


                    } else
                        Toast.makeText(getApplicationContext(), "User could not be registered", Toast.LENGTH_LONG).show();


                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = email.getText().toString();

                if (id.equals(String.valueOf(" "))) {
                    email.setError("Enter email address to get Data");
                } else {
                    Cursor res = mdb.getData(id);
                    String data = null;
                    if (res.moveToFirst()) {
                        data = "Name    : " + res.getString(0) + "\n\n" +
                                "Surname    : " + res.getString(1) + "\n\n" +
                                "National : " + res.getString(2) + "\n\n" +
                                "Phone    : " + res.getString(3) + "\n\n" +
                                "Email    : " + res.getString(4) + "\n\n" +
                                "Date : " + res.getString(5) + "\n\n" ;

                    }
                    showMessage("User Info", data);
                }
            }

        });

//        viewallExams.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(TimeTable.this,ViewExam.class);
//                startActivity(i);
//            }
//        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Surname = surname.getText().toString();
                String Nationa = national.getText().toString();
                String Phone = phone.getText().toString();
                String Email = email.getText().toString();
                String Date = date.getText().toString();

                if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Surname) || TextUtils.isEmpty(Nationa) || TextUtils.isEmpty(Phone) || TextUtils.isEmpty(Email)) {
                    Toast.makeText(RegisterUsers.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    boolean isUpdate = mdb.updateData(Name,Surname, Nationa, Phone,  Date, Email);
                    if (isUpdate == true)
                        Toast.makeText(getApplicationContext(), "User is updated", Toast.LENGTH_LONG).show();


                    else
                        Toast.makeText(getApplicationContext(), "User not updated", Toast.LENGTH_LONG).show();



                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = email.getText().toString();
                Integer deleteExam = mdb.deleteUser(id);

                final AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterUsers.this);
                dialog.setTitle("DELETE User!!!");
                dialog.setMessage("Are you sure you want to delete this user ? ");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (TextUtils.isEmpty(id)) {
                            Toast.makeText(getApplicationContext(), "Please enter email address to delete", Toast.LENGTH_LONG).show();


                        } else {
                            if (deleteExam > 0)
                                Toast.makeText(getApplicationContext(), "User Deleted Sussessful", Toast.LENGTH_LONG).show();


                            else
                                Toast.makeText(getApplicationContext(), "User could not be deleted", Toast.LENGTH_LONG).show();


                        }                                         //deletes all data in table reminders

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
        });






    }
    private void showMessage (String tittle, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(tittle);
        builder.setMessage(message);
        builder.show();
    }
}
