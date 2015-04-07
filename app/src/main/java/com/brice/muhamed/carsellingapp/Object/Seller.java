package com.brice.muhamed.carsellingapp.Object;

/**
 * Created by Brice on 18/03/2015.
 */
public class Seller {

    private int id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String postCode;
    private String email;

    public Seller(int id, String username, String password, String name, String address,String postCode, String email){

        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.postCode = postCode;
        this.email= email;
        this.address = address;

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

    public String getname(){
        return name;
    }

    public String getPostCode(){
        return postCode;
    }

    public String getEmail(){
        return email;
    }

    public String getAddress(){
        return address;
    }
}
