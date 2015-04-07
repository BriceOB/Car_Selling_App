package com.brice.muhamed.carsellingapp.Database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Brice on 12/03/2015.
 */
public final class SellerContract {

    public SellerContract() {
    }


    public static abstract class EntrySeller implements BaseColumns {
        public static final String TABLE_NAME = "Seller";

        public static final String COLUMN_NAME_USERNAME = "Username";
        public static final String COLUMN_NAME_PASSWORD = "Password";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_ADDRESS = "Address";
        public static final String COLUMN_NAME_POSTCODE = "Postcode";
        public static final String COLUMN_NAME_EMAIL = "Email";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";

        //Table `Car_Selling_Database`.`Seller`
        public static final String CREATE_SELLER_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_POSTCODE + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_EMAIL + TEXT_TYPE +
                " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


}


