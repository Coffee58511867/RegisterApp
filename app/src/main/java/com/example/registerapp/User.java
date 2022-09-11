package com.example.registerapp;

public class User{
    private String name, surname, national ,phone, email, date;


    public User() {}

    public User(String name, String surname, String national,String email, String phone,  String date) {
        this.name = name;
        this.surname = surname;
        this.national = national;
        this.email = email;
        this.phone = phone;
        this.date = date;



    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

