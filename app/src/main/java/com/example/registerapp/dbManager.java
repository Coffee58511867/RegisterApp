package com.example.registerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbManager extends SQLiteOpenHelper {
    //database name
    private static String dbname = "Training";
    public  final String Table_name = "RegisterUser";   //table name to store Exam details
    public static String COL_1 = "Name";
    public static String COL_2 = "Surname";
    public static String COL_3 = "National";
    public static String COL_4 = "Phone";
    public static String COL_5 = "Email";
    public static String COL_6 = "Date";




    public dbManager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {                                           //sql query to create tables
        db.execSQL("CREATE TABLE IF NOT EXISTS "+Table_name+"(Name text ,Surname text , National text, Phone text,Email text,Date text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {



        db.execSQL("drop table if exists "+Table_name);

                                                                     //executes the sql command
        onCreate(db);

    }


    public boolean insert(String Name, String Surname, String National, String Phone, String Email, String Date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Name);
        contentValues.put(COL_2,Surname);
        contentValues.put(COL_3,National);
        contentValues.put(COL_4,Phone);                                  //Insert Exam details into database
        contentValues.put(COL_5,Email);
        contentValues.put(COL_6,Date);
        long ins = db.insert(Table_name,null,contentValues);
        if(ins == -1)return false;
        else return true;
    }

    public Cursor getData(String email){
        SQLiteDatabase db = this.getWritableDatabase() ;
        String query = "SELECT * FROM "+Table_name+" WHERE Email = '"+email+"'";                //Sql to get data from studentExams
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public boolean updateData(String Name, String Surname, String National, String Phone, String Email, String Date)
    {
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues cv = new ContentValues();
        cv.put(COL_1 , Name);
        cv.put(COL_2, Surname);                                         //updating Exam details
        cv.put(COL_3,National);
        cv.put(COL_4,Phone);
        cv.put(COL_6,Email);
        cv.put(COL_5, Date);


        db.update("RegisterUser" , cv, "Email=?", new String[]{Email} );
        return true;

    }

    public Integer deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase() ;                                 //method that deletes Exams
        return  db.delete("RegisterUser", "Email = ? ",new String[]{id});

    }

    public Cursor readallUser() {

        SQLiteDatabase db = this.getWritableDatabase() ;                                         //sql to read all student exams
        Cursor cursor = db.rawQuery("SELECT * FROM "+Table_name, null);
        return cursor;
    }


}
