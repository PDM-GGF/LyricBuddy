package com.progettopdm.lyricbuddy.login;

//classe per gestione user in real time database
public class User {
    private String fullName, phoneNumber, email;

    public User() {
    }

    public User(String fullName, String phoneNumber, String email) {
        //this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

   // public String getId() { return id; }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}