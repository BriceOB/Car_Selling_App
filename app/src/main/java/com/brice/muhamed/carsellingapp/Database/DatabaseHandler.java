package com.brice.muhamed.carsellingapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.brice.muhamed.carsellingapp.Object.Car;
import com.brice.muhamed.carsellingapp.Object.Seller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brice on 16/03/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME =  "Car_Selling_Database.db";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ManufacturerContract.EntryManufacturer.CREATE_TABLE);

        db.execSQL(SellerContract.EntrySeller.CREATE_SELLER_TABLE);

        db.execSQL(CarContract.EntryCar.CREATE_TABLE);

        setDefaultDb(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed

        db.execSQL(ManufacturerContract.EntryManufacturer.DELETE_TABLE);

        db.execSQL(SellerContract.EntrySeller.DELETE_TABLE);

        db.execSQL(CarContract.EntryCar.DELETE_TABLE);


        onCreate(db);
    }

    public void addContact(String username, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SellerContract.EntrySeller.COLUMN_NAME_USERNAME, username );
        values.put(SellerContract.EntrySeller.COLUMN_NAME_PASSWORD, password);

        db.insert(SellerContract.EntrySeller.TABLE_NAME, null, values);
        db.close();


    }


    public List<String> getManufacturer() {

        List<String> manufacturers = new ArrayList<String>() ;

        String querySelectAll = "SELECT * FROM " + ManufacturerContract.EntryManufacturer.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(querySelectAll, null);

        if(c.moveToFirst()){
            for(int i=0; i< c.getCount();i++){
                manufacturers.add(c.getString(c.getColumnIndex(ManufacturerContract.EntryManufacturer.COLUMN_NAME_BRAND)));
                c.move(1);
            }

        }

        return manufacturers;

    }

    public List<String> getModel() {

        List<String> Model = new ArrayList<String>();

        String querySelectAll = "SELECT * FROM " + CarContract.EntryCar.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(querySelectAll, null);

        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                Model.add(c.getString(c.getColumnIndex(CarContract.EntryCar.COLUMN_NAME_Model)));
                c.move(1);
            }
        }
        return Model;
    }


    public void setDefaultDb(SQLiteDatabase db) {
        Log.i("Start:",""+  " added");
        String[] DefaultModel = {"Audi" , "Mercedes" , "BMW", "Ford"};

        ContentValues valuesManufacturer = new ContentValues();
        for(int i = 0;i<DefaultModel.length;i++){
            valuesManufacturer.put(ManufacturerContract.EntryManufacturer.COLUMN_NAME_BRAND, DefaultModel[i] );
            db.insert(ManufacturerContract.EntryManufacturer.TABLE_NAME, null, valuesManufacturer);
            Log.e("ELEMENT:",""+i + " added");
        }

        ContentValues valuesCar = new ContentValues();
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_Model,"A4");
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_ManufacturerId,1);
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_ToSell,1);
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_CarDate,"-");
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_CreationDate,"-");
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_Doors,5);
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_Fuel,"Diesel");
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_Kilometers,200000);
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_Hp,140);
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_Weight,2000);
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_ToSell,1);
        valuesCar.put(CarContract.EntryCar.COLUMN_NAME_Description,"This is a test");
        db.insert(CarContract.EntryCar.TABLE_NAME, null, valuesCar);

        Log.e("Default Car: ", "added ");

    }

    public Seller checkUserPassword(String Username, String Password){

        SQLiteDatabase db = this.getWritableDatabase();


        Cursor c = db.query(SellerContract.EntrySeller.TABLE_NAME,
                new String[] { SellerContract.EntrySeller._ID },
                SellerContract.EntrySeller.COLUMN_NAME_USERNAME + " = '" + Username + "' AND " +   SellerContract.EntrySeller.COLUMN_NAME_PASSWORD + " = '" + Password + "'" ,
                null,
                null,
                null,
                null);

        if ( c.getCount() ==0){
            return null;
        }

//return false if no users was found

        c.moveToFirst();
        Seller seller = new Seller(c.getInt(c.getColumnIndex(SellerContract.EntrySeller._ID)),Username,Password);
        return seller;

    }

    public void InsertCarInfos(Car car){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CarContract.EntryCar.COLUMN_NAME_Model,car.getModel() );
        values.put(CarContract.EntryCar.COLUMN_NAME_ManufacturerId,car.getManufacturerId());
        values.put(CarContract.EntryCar.COLUMN_NAME_SellerId,car.getSellerId() );


        // Inserting Row
        db.insert(CarContract.EntryCar.TABLE_NAME, null, values);
        db.close(); // Closing database connection

    }

    public int GetManufacturerId(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(ManufacturerContract.EntryManufacturer.TABLE_NAME,
                new String[] { ManufacturerContract.EntryManufacturer._ID},
                ManufacturerContract.EntryManufacturer.COLUMN_NAME_BRAND + " = '" + name + "'" ,
                null,
                null,
                null,
                null);

        if ( c.getCount() !=0){
            c.moveToFirst();
            int id = c.getInt(0);
            return id;
        }

        return 0;
    }



/*
    public String[] getMyCars(int UserId){

        SQLiteDatabase db = this.getWritableDatabase();

        String[] Model;

        String MY_QUERY = "SELECT * FROM "+
                CarContract.EntryCar.TABLE_NAME + " c INNER JOIN " + ManufacturerContract.EntryManufacturer.TABLE_NAME +
                " m ON c._id=m._id INNER JOIN " +  CarContract.EntryCar.TABLE_NAME + " "
        WHERE b.property_id=?";


        String MY_QUERY = "SELECT * FROM " + CarContract.EntryCar.TABLE_NAME + " AS c INNER JOIN " + ManufacturerContract.EntryManufacturer.TABLE_NAME + " AS m " +  "ON a.id=b.other_id WHERE b.property_id=?";

        db.rawQuery(MY_QUERY, new String[]{String.valueOf(propertyId)});
    }
*/

}