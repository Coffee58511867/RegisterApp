package com.example.registerapp;

public class Register {
    public String email1;
    public String name;
    public String surname;
    public String license;
    public String userType;
    public String password1;


    public Register(){

    }
    public Register(String email1, String name, String surname,String userType,String license ,String password1){
        this.email1 = email1;
        this.name = name;
        this.surname  = surname;
        this.license = license;
        this.userType = userType;
        this.password1 = password1;

    }
    public String getEmail1()
    {
        return  email1;
    }
    public void setEmail1(String email1)
    {
        this.email1 = email1;
    }
    public String getName()
    {
        return  name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getSurname()
    {
        return surname;
    }
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    public String getUserType(){
        return  userType;
    }
    public void setUserType(String userType)
    {
        this.userType = userType;
    }
    public String getLicense(){
        return  license;
    }
    public void setLicense(String license)
    {
        this.license = license;
    }
    public String getPassword1(){
        return  password1;
    }
    public void setPassword1(String password1)
    {
        this.password1 = password1;
    }

}
