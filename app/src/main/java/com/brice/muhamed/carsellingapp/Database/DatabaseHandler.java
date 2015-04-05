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

import org.apache.http.MethodNotSupportedException;

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

    //Get list Model for a manufacturer
    public List<String> getModel(String Manufacturer) {

        List<String> Model = new ArrayList<String>();

        String querySelectAll = "SELECT * FROM " + CarContract.EntryCar.TABLE_NAME + ", " + ManufacturerContract.EntryManufacturer.TABLE_NAME+ " WHERE " + CarContract.EntryCar.COLUMN_NAME_ManufacturerId + " = (SELECT DISTINCT " + ManufacturerContract.EntryManufacturer._ID + " FROM "  + ManufacturerContract.EntryManufacturer.TABLE_NAME+  " WHERE "+ ManufacturerContract.EntryManufacturer.COLUMN_NAME_BRAND + " = '" + Manufacturer+ "' )";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(querySelectAll, null);

        if (c.moveToFirst()) {
           while (!c.isAfterLast()) {
                Model.add(c.getString(c.getColumnIndex(CarContract.EntryCar.COLUMN_NAME_Model)));
                c.move(1);
            }
        }
        return Model;
    }


    public void setDefaultDb(SQLiteDatabase db) {
        Log.i("Start:",""+  " added");
        String[] DefaultModel = {"Acura","Alfa Romeo","AMC","Ariel","Aston Martin","Audi","Austin Healey","Bentley","BMW","Bugatti","Buick","Cadillac","Caterham","Chevrolet","Chrysler","Citroen","Daewoo","Daihatsu","Datsun","De Tomaso","Dodge","Eagle","Ferrari","Fiat","Fisker","Ford","Geo","GMC","Holden","Honda","Hummer","Hyundai","Infiniti","Isuzu","Jaguar","Jeep","Kia","Koenigsegg","Lamborghini","Lancia","Land Rover","Lexus","Lincoln","Lotus","Maserati","Maybach","Mazda","McLaren","Mercedes","Mercury","MG","Mini","Mitsubishi","Morgan","Mosler Rossion","Nissan","Noble","Oldsmobile","Opel","Pagani","Peugeot","Plymouth","Pontiac","Porsche","Proton","Ram","Renault","Rolls-Royce","Saab","Saleen","Saturn","Scion","Seat","Shelby","Skoda","Smart","Subaru","Suzuki","Tesla","Toyota","Triumph","Vauxhall","VW","Volvo","Westfield","OTHER"};

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
        values.put(CarContract.EntryCar.COLUMN_NAME_CarDate,car.getCarDate().toString() );
        values.put(CarContract.EntryCar.COLUMN_NAME_CreationDate,car.getCreationDate().toString() );
        values.put(CarContract.EntryCar.COLUMN_NAME_Kilometers,car.getKilometers() );
        values.put(CarContract.EntryCar.COLUMN_NAME_Fuel,car.getFuel() );
       // values.put(CarContract.EntryCar.COLUMN_NAME_Gearbox,car.getGearBox) );
        values.put(CarContract.EntryCar.COLUMN_NAME_Doors,car.getDoors() );
        values.put(CarContract.EntryCar.COLUMN_NAME_Price,car.getPrice() );
        values.put(CarContract.EntryCar.COLUMN_NAME_Description,car.getDescription() );
        values.put(CarContract.EntryCar.COLUMN_NAME_ToSell,car.getToSell() );

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


    public String[][] getMyCars(int UserId){

        if(UserId==0)
            return null;

        SQLiteDatabase db = this.getWritableDatabase();



        String MY_QUERY = "SELECT DISTINCT "+ CarContract.EntryCar.COLUMN_NAME_Model+", " + ManufacturerContract.EntryManufacturer.COLUMN_NAME_BRAND +", "+ CarContract.EntryCar.COLUMN_NAME_Description + ", "+ CarContract.EntryCar.COLUMN_NAME_Price+ ", "+ CarContract.EntryCar.COLUMN_NAME_PhotoPath +"," + CarContract.EntryCar.TABLE_NAME + "."+CarContract.EntryCar._ID+" FROM "+
                CarContract.EntryCar.TABLE_NAME + " c , " + ManufacturerContract.EntryManufacturer.TABLE_NAME +" m , "+  SellerContract.EntrySeller.TABLE_NAME + " s WHERE c." + CarContract.EntryCar.COLUMN_NAME_ManufacturerId + " = m._id AND c." + CarContract.EntryCar.COLUMN_NAME_SellerId + " = " + UserId

;
        Cursor cur = db.rawQuery(MY_QUERY,new String [] {});




        if(cur.moveToFirst()) {
            String[][] Model = new String[cur.getCount()][6];

            for(int row = 0; row< Model.length;row++){
                for(int column = 0 ;column < Model[0].length;column++){

                    Model[row][column] = cur.getString(column);

                }
                cur.moveToNext();
            }

            cur.close();
            return Model;
        }
        return null;
    }


    public String[][] GetResultSearch(String Brand, String Model, String Year, String YearTo, String KmFrom, String KmTo, String PriceFrom, String PriceTo, String Order){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(ManufacturerContract.EntryManufacturer.TABLE_NAME + ", " + CarContract.EntryCar.TABLE_NAME,
                new String[] { CarContract.EntryCar.COLUMN_NAME_Model+", " + ManufacturerContract.EntryManufacturer.COLUMN_NAME_BRAND +", "+ CarContract.EntryCar.COLUMN_NAME_Description + ", "+ CarContract.EntryCar.COLUMN_NAME_Price+ ", "+ CarContract.EntryCar.COLUMN_NAME_PhotoPath + ", " + CarContract.EntryCar.TABLE_NAME + "."+CarContract.EntryCar._ID},
                ManufacturerContract.EntryManufacturer.COLUMN_NAME_BRAND + " = '" + Brand + "' AND " +
                      ManufacturerContract.EntryManufacturer.TABLE_NAME+"."+ ManufacturerContract.EntryManufacturer._ID + " = " + CarContract.EntryCar.TABLE_NAME+ "." +CarContract.EntryCar.COLUMN_NAME_ManufacturerId + " AND " +
                        CarContract.EntryCar.COLUMN_NAME_ToSell + " = '" + "1" + "' AND " +
                        CarContract.EntryCar.COLUMN_NAME_CarDate+ " >= '" + Year + "' AND " +
                        CarContract.EntryCar.COLUMN_NAME_CarDate + " <= '" + YearTo + "' AND " +
                        CarContract.EntryCar.COLUMN_NAME_Kilometers+ " >= '" + KmFrom + "' AND " +
                        CarContract.EntryCar.COLUMN_NAME_Kilometers+ " <= '" + KmTo + "' AND " +
                        CarContract.EntryCar.COLUMN_NAME_Price+ " >= '" + PriceFrom + "' AND " +
                        CarContract.EntryCar.COLUMN_NAME_Price+ " <= '" + PriceTo + "' AND " +
                   CarContract.EntryCar.COLUMN_NAME_Model + " = '" + Model + "'" ,
                null,
                null,
                null,
                null);




        if(c.moveToFirst()) {
            String[][] result = new String[c.getCount()][6];

            for(int row = 0; row< result.length;row++){
                for(int column = 0 ;column < result[0].length;column++){

                    result[row][column] = c.getString(column);

                }
                c.moveToNext();
            }

            c.close();
            return result;
        }
        return null;
    }

    public String[] getCarDetails(String CarId){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(ManufacturerContract.EntryManufacturer.TABLE_NAME + ", " + CarContract.EntryCar.TABLE_NAME + ", " + SellerContract.EntrySeller.TABLE_NAME,
                new String[] { CarContract.EntryCar.COLUMN_NAME_Model+", " + ManufacturerContract.EntryManufacturer.COLUMN_NAME_BRAND +", "+ CarContract.EntryCar.COLUMN_NAME_Description + ", "+ CarContract.EntryCar.COLUMN_NAME_Price+ ", "+ CarContract.EntryCar.COLUMN_NAME_PhotoPath },
                CarContract.EntryCar.TABLE_NAME+"."+CarContract.EntryCar._ID + " = '" + CarId + "' AND " +
                        CarContract.EntryCar.COLUMN_NAME_SellerId + " = " + SellerContract.EntrySeller.TABLE_NAME+"."+SellerContract.EntrySeller._ID + " AND " +
                        ManufacturerContract.EntryManufacturer.TABLE_NAME+"."+ ManufacturerContract.EntryManufacturer._ID + " = " + CarContract.EntryCar.COLUMN_NAME_ManufacturerId  ,
                null,
                null,
                null,
                null);


       c.moveToFirst();
            String[] CarDetails = new String[5];

            for(int row = 0; row< CarDetails.length;row++){

                CarDetails[row] = c.getString(row);

                c.moveToNext();
            }

            c.close();
            return CarDetails;


    }

}