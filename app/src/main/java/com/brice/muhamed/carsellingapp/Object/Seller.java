package com.brice.muhamed.carsellingapp.Object;

/**
 * Created by Brice on 18/03/2015.
 */
public class Seller {

    private String username;
    private String password;

    public Seller(String username, String password){

        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
