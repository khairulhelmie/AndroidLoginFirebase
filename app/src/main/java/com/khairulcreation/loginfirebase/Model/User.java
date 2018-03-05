package com.khairulcreation.loginfirebase.Model;

/**
 * Created by KhairulHelmie on 2/2/2018.
 */

public class User {

    private String Name;
    private String Email;
    private String Password;


    public User() {
    }

    public User(String name, String email, String password) {
        Name = name;
        Email = email;
        Password = password;
    }

    //===================================================

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    //===================================================

    public String getEmail() { return Email; }

    public void setEmail(String email){ Email = email; }

    //===================================================

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
