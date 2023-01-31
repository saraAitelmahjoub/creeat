package com.example.quiz;

public class User
{
    String Name, Pswd,Email;

    public User(String nom,String pwd,String email) {
        Name=nom;
        Pswd=pwd;
        Email=email;
    }

    public User() { }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



    public String getPswd() {
        return Pswd;
    }

    public void setPswd(String pswd) {
        Pswd = pswd;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
