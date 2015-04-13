package com.brice.muhamed.carsellingapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;
import com.brice.muhamed.carsellingapp.Database.SellerContract;
import com.brice.muhamed.carsellingapp.Object.Seller;

import java.lang.reflect.Array;


public class InsertSeller extends ActionBarActivity {


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

        DatabaseHandler dbh = new DatabaseHandler(getBaseContext());

        String username = ((TextView)findViewById(R.id.EditTextName)).getText().toString();
        String password = ((TextView)findViewById(R.id.editTextPassword)).getText().toString();
        String firstname = ((TextView)findViewById(R.id.EditTextFirstname)).getText().toString();
        String address = ((TextView)findViewById(R.id.EditTextAddress)).getText().toString();
        String place = ((TextView)findViewById(R.id.EditTextPlace)).getText().toString();
        String email = ((TextView)findViewById(R.id.EditTextEmail)).getText().toString();

        //Check if username is not already used
       if(dbh.checkUser(username)){

           dbh.addContact(username,password,firstname,address,place,email);

           Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.contactAdd), Toast.LENGTH_SHORT);
           toast.show();

           this.finish();
       }

        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.ContactUsed), Toast.LENGTH_SHORT);
        toast.show();
    }

}
