package com.brice.muhamed.carsellingapp.Object;

import java.util.Date;

/**
 * Created by Brice on 25/03/2015.
 */
public class Car {

    private int Id;
    private   String Model ;
    private   int Kilometers;
    private   String Fuel ;
    private   int Doors ;
    //  private   int Hp ;
    // private   int Weight ;
    private   int Price;
    private   String Description ;
    private   String PhotoPath ;
    private   String CreationDate ;
    private   String CarDate ;
    private   int ToSell ;
    public    int ManufacturerId ;
    public String Manufacturer;
    public    int SellerId;

    public Car( String model, int kilometers, String fuel, int doors,
                //  int hp,int weight,
                int price, String description,
                String creationDate, String carDate, int toSell, int manufacturerId,
                int sellerId, String PhotoPath) {


        Model = model;
        Kilometers = kilometers;
        Fuel = fuel;
        Doors = doors;
        //   Hp = hp;
        //    Weight = weight;
        Price = price;
        Description = description;

        CreationDate = creationDate;
        CarDate = carDate;
        ToSell = toSell;
        ManufacturerId = manufacturerId;
        SellerId = sellerId;
    }

    public Car( String model, int kilometers, String fuel, int doors,
                int price, String description,
                String creationDate, String carDate, int toSell, String manufacturer, String PhotoPath
                ) {

        Model = model;
        Kilometers = kilometers;
        Fuel = fuel;
        Doors = doors;
        Price = price;
        Description = description;
        CreationDate = creationDate;
        CarDate = carDate;
        ToSell = toSell;
        Manufacturer = manufacturer;
        this.PhotoPath = PhotoPath;

    }



    public void setPhotoPath(String photoPath){

        PhotoPath = photoPath;

    }

    public void setId(int id){

        Id = id;

    }



    /*GETTERS

     */

    public int getId() {
        return Id;
    }

    public String getModel() {
        return Model;
    }

    public String getManufacturer() {
        return Manufacturer;
    }


    public int getKilometers() {
        return Kilometers;
    }

    public String getFuel() {
        return Fuel;
    }

    public int getDoors() {
        return Doors;
    }

    /*
    public int getHp() {
        return Hp;
    }

    public int getWeight() {
        return Weight;
    }
*/
    public int getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    public String getPhotoPath() {
        return PhotoPath;
    }

        public String getCreationDate() {
            return CreationDate;
        }

        public String getCarDate() {
            return CarDate;
        }

    public int getToSell() {
        return ToSell;
    }

    public int getManufacturerId() {
        return ManufacturerId;
    }

    public int getSellerId() {
        return SellerId;
    }
}
