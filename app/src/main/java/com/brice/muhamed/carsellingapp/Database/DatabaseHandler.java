package com.brice.muhamed.carsellingapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.brice.muhamed.carsellingapp.Object.Seller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brice on 16/03/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME =  "Car_Selling_Database.db";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ManufacturerContract.EntryManufacturer.CREATE_TABLE);

        db.execSQL(SellerContract.EntrySeller.CREATE_SELLER_TABLE);


        setDefaultDb(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed

        db.execSQL(ManufacturerContract.EntryManufacturer.DELETE_TABLE);

        db.execSQL(SellerContract.EntrySeller.DELETE_TABLE);

        onCreate(db);
    }

    public void addContact(Seller seller) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SellerContract.EntrySeller.COLUMN_NAME_USERNAME, seller.getUsername() );
        values.put(SellerContract.EntrySeller.COLUMN_NAME_PASSWORD, seller.getPassword());

        db.insert(SellerContract.EntrySeller.TABLE_NAME, null, values);
        db.close();


    }


    public List<String> getManufacturer() {

        List<String> manufacturers = new ArrayList<String>() ;

        String querySelectAll = "SELECT * FROM " + ManufacturerContract.EntryManufacturer.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(querySelectAll, null);

        if(c.moveToFirst()){

            manufacturers.add(c.getString(c.getColumnIndex(ManufacturerContract.EntryManufacturer.COLUMN_NAME_BRAND)));

        }

        return manufacturers;

    }

    public void setDefaultDb(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put(ManufacturerContract.EntryManufacturer.COLUMN_NAME_BRAND, "test" );
        values.put(ManufacturerContract.EntryManufacturer.COLUMN_NAME_IDMANUFACTURER, 1);

        db.insert(ManufacturerContract.EntryManufacturer.TABLE_NAME, null, values);

    }

    public Seller checkUserPassword(String Username, String Password){

        SQLiteDatabase db = this.getWritableDatabase();


        Cursor c = db.query(SellerContract.EntrySeller.TABLE_NAME,
                new String[] { SellerContract.EntrySeller.COLUMN_NAME_USERNAME, SellerContract.EntrySeller.COLUMN_NAME_PASSWORD },
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
            Seller seller = new Seller(Username,Password);
            return seller;




    }

}