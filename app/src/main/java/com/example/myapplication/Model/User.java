package com.example.myapplication.Model;

public class User {
    private String userName;
    private String password;
    private String email;
    private String type;
    private String plec;

    public User() {
    }

    public User(String userName, String password, String email,String type, String plec) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.type = type;
        this.plec = plec;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
