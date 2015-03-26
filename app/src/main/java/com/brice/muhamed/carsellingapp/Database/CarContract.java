package com.brice.muhamed.carsellingapp.Database;

import android.provider.BaseColumns;

/**
 * Created by Brice on 12/03/2015.
 */
public final class CarContract {
    // To prevent someone from accidentally instantiating the contract class,
// give it an empty constructor.
    public CarContract() {
    }



/*  //Table `Car_Selling_Database`.`Car`
        String Create_Car_Table = "CREATE TABLE IF NOT EXISTS" + "Car_Selling_Database`.`Car` (\n" +


                "  `HP` INT NULL,\n" +
                "  `Weight` INT NULL,\n" +
                "  `Price` INT NULL,\n" +
                "  `Description` VARCHAR(200) NULL,\n" +
                "  `PhotoPath` VARCHAR(60) NULL,\n" +
                "  `CreationDate` DATETIME NOT NULL,\n" +
                "  `CarDate` DATETIME NOT NULL,\n" +
                "  `ToSell` TINYINT(1) NOT NULL,\n" +
                "  `Manufacturer_idManufacturer` INT NOT NULL,\n" +
                "  `Seller_idSeller` INT NOT NULL,\n" +
                "  PRIMARY KEY (`idCar`, `Manufacturer_idManufacturer`, `Seller_idSeller`),\n" +
                "  INDEX `fk_Car_Manufacturer_idx` (`Manufacturer_idManufacturer` ASC),\n" +
                "  INDEX `fk_Car_Seller1_idx` (`Seller_idSeller` ASC),\n" +
                "  CONSTRAINT `fk_Car_Manufacturer`\n" +
                "    FOREIGN KEY (`Manufacturer_idManufacturer`)\n" +
                "    REFERENCES `Car_Selling_Database`.`Manufacturer` (`idManufacturer`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT `fk_Car_Seller1`\n" +
                "    FOREIGN KEY (`Seller_idSeller`)\n" +
                "    REFERENCES `Car_Selling_Database`.`Seller` (`idSeller`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION)";
                */

    public static abstract class EntryCar implements BaseColumns {

        public static final String TABLE_NAME       = "Car";

        //Column name
        public static final String COLUMN_NAME_Model = "Model";
        public static final String COLUMN_NAME_Kilometers = "Kilometers";
        public static final String COLUMN_NAME_Fuel = "Fuel";
        public static final String COLUMN_NAME_Doors = "Doors";
        public static final String COLUMN_NAME_Hp = "Hp";
        public static final String COLUMN_NAME_Weight = "Weight";
        public static final String COLUMN_NAME_Price = "Price";
        public static final String COLUMN_NAME_Description = "Description";
        public static final String COLUMN_NAME_PhotoPath = "PhotoPath";
        public static final String COLUMN_NAME_CreationDate = "CreationDate";
        public static final String COLUMN_NAME_CarDate = "CarDate";
        public static final String COLUMN_NAME_ToSell = "ToSell";

        //Foreign Key
        public static final String COLUMN_NAME_ManufacturerId = "Manufacturer_idManufacturer";
        public static final String COLUMN_NAME_SellerId = "Seller_idSeller";

        //Type
        private static final String TEXT_TYPE          = " TEXT";
        private static final String INT_TYPE          = " INTEGER";
        private static final String COMMA_SEP          = ",";

        //Table creation
        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +

                COLUMN_NAME_SellerId + INT_TYPE + COMMA_SEP +
                COLUMN_NAME_ManufacturerId + INT_TYPE + COMMA_SEP +
                COLUMN_NAME_Model + TEXT_TYPE +  COMMA_SEP +
                COLUMN_NAME_Kilometers + INT_TYPE +  COMMA_SEP +
                COLUMN_NAME_Fuel + TEXT_TYPE +  COMMA_SEP +
                COLUMN_NAME_Doors + INT_TYPE +  COMMA_SEP +
                COLUMN_NAME_Hp + INT_TYPE +  COMMA_SEP +
                COLUMN_NAME_Weight + INT_TYPE +  COMMA_SEP +
                COLUMN_NAME_Price + INT_TYPE +  COMMA_SEP +
                COLUMN_NAME_Description + TEXT_TYPE +  COMMA_SEP +
                COLUMN_NAME_PhotoPath + TEXT_TYPE +  COMMA_SEP +
                COLUMN_NAME_CreationDate + TEXT_TYPE +  COMMA_SEP +
                COLUMN_NAME_CarDate + TEXT_TYPE +  COMMA_SEP +
                COLUMN_NAME_ToSell + TEXT_TYPE +  COMMA_SEP +

                " FOREIGN KEY(" + COLUMN_NAME_SellerId + ") REFERENCES " + SellerContract.EntrySeller.TABLE_NAME + "(" + SellerContract.EntrySeller._ID + ")" + COMMA_SEP +
                " FOREIGN KEY(" + COLUMN_NAME_ManufacturerId + ") REFERENCES " + ManufacturerContract.EntryManufacturer.TABLE_NAME + "(" + ManufacturerContract.EntryManufacturer._ID + ")"

                +" )";

        //Delete table
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
