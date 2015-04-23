package com.example.Brice.myapplication.cloud;

import com.googlecode.objectify.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

/**
 * Created by Brice on 23/04/2015.
 */
@Entity
public class Seller {

    @Id
    int id;
    String username;
    String password;
    String name;
    String address;
    String postCode;
    String email;


}
