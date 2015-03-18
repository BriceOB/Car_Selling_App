package com.brice.muhamed.carsellingapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Brice on 16/03/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME =  "Car_Selling_Database.db";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



  /*      //Table `Car_Selling_Database`.`Manufacturer`
        String Create_Manufacturer_Table = "CREATE TABLE IF NOT EXISTS" + "Car_Selling_Database`.`Manufacturer` (" +
                "  `idManufacturer` INT NOT NULL," +
                "  `Brand` VARCHAR(45) NULL," +
                "  PRIMARY KEY (`idManufacturer`))";

        //Table `Car_Selling_Database`.`Seller`
        String Create_Seller_Table = "CREATE TABLE IF NOT EXISTS" + "Car_Selling_Database`.`Seller` (" +
                "  `idSeller` INT NOT NULL," +
                "  `Username` VARCHAR(45) NOT NULL," +
                "  `Password` VARCHAR(45) NOT NULL," +
                "  PRIMARY KEY (`idSeller`)))";

      */

        db.execSQL(SellerContract.EntrySeller.CREATE_SELLER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL(SellerContract.EntrySeller.DELETE_TABLE);

       onCreate(db);
    }
}