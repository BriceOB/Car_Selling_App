package com.brice.muhamed.carsellingapp.Database;

import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Brice on 12/03/2015.
 */
public final class ManufacturerContract {
    // To prevent someone from accidentally instantiating the contract class,
// give it    an empty constructor .
    public ManufacturerContract() {


    }

    public static abstract class EntryManufacturer implements BaseColumns {
        public static final String TABLE_NAME       = "Manufacturer";
        public static final String COLUMN_NAME_BRAND = "Brand";

        private static final String TEXT_TYPE          = " TEXT";
        private static final String COMMA_SEP          = ",";
        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_BRAND + TEXT_TYPE  + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }




}
