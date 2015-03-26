package com.brice.muhamed.carsellingapp.Object;

/**
 * Created by Brice on 18/03/2015.
 */
public class Seller {

    private int id;
    private String username;
    private String password;

    public Seller(int id, String username, String password){

        this.id = id;
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
