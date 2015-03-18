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

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "Car_Selling_Database.db";



    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.


/*  //Table `Car_Selling_Database`.`Car`
        String Create_Car_Table = "CREATE TABLE IF NOT EXISTS" + "Car_Selling_Database`.`Car` (\n" +
                "  `idCar` INT NOT NULL,\n" +
                "  `Model` VARCHAR(45) NULL,\n" +
                "  `Kilometers` INT NULL,\n" +
                "  `Fuel` VARCHAR(45) NULL,\n" +
                "  `Doors` INT NULL,\n" +
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
        public static final String COLUMN_NAME_Username = "Username";
        public static final String COLUMN_NAME_Password = "Password";
        public static final String COLUMN_NAME_idSeller = "idSeller";

        private static final String TEXT_TYPE          = " TEXT";
        private static final String COMMA_SEP          = ",";
        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_Username + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_Password + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_idSeller + TEXT_TYPE + COMMA_SEP + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
