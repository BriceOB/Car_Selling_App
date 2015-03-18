package com.brice.muhamed.carsellingapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.brice.muhamed.carsellingapp.Object.Seller;

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



  /*



      */

        db.execSQL(SellerContract.EntrySeller.CREATE_SELLER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed


        db.execSQL(SellerContract.EntrySeller.DELETE_TABLE);

       onCreate(db);
    }

    public void addContact(Seller seller) {
        Log.d("Add: ", "Add a new seller");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SellerContract.EntrySeller.COLUMN_NAME_USERNAME, seller.getUsername() );
        values.put(SellerContract.EntrySeller.COLUMN_NAME_PASSWORD, seller.getPassword());

        db.insert(SellerContract.EntrySeller.TABLE_NAME, null, values);
        db.close();
    }


}