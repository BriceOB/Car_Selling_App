package com.example.Brice.myapplication.cloud;

import com.googlecode.objectify.annotation.*;


/**
 * Created by Brice on 23/04/2015.
 */
@Entity
public class Car {

    @Id
    int id;
    String model;
    int kilometers; String fuel;
    int doors;

    int price;
    String description;
    String creationDate;
    String carDate;
    int toSell;
    int manufacturerId;
    int sellerId;
    String PhotoPath;

}
