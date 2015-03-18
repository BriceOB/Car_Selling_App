package com.brice.muhamed.carsellingapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;
import com.brice.muhamed.carsellingapp.Database.SellerContract;


public class InsertSeller extends ActionBarActivity {

    private DatabaseHandler Dbh = new DatabaseHandler(getBaseContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_seller);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insert_seller, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void register(View view) {



        String username = ((TextView)findViewById(R.id.EditTextName)).toString();

        SQLiteDatabase db = Dbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SellerContract.EntrySeller.COLUMN_NAME_USERNAME, username);
        values.put(SellerContract.EntrySeller.COLUMN_NAME_PASSWORD, "password");


        long RowId = db.insert(SellerContract.EntrySeller.TABLE_NAME, null, values);

        Intent intent = new Intent(this, InsertSeller.class);
        startActivity(intent);
    }

}
