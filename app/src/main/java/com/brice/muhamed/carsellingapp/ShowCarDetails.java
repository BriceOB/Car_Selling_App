package com.brice.muhamed.carsellingapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;


public class ShowCarDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car_details);

        DatabaseHandler dbh  = new DatabaseHandler(getBaseContext());

        //Get intent from search page
        Intent intent = getIntent();
        String CarId = intent.getStringExtra(HomePage.EXTRA_MESSAGE);

        String[] CarDetails = dbh.getCarDetails(CarId);

      ((TextView)findViewById(R.id.TextViewInputManufacture)).setText(CarDetails[0]);
        ((TextView)findViewById(R.id.TextViewInputDescription)).setText(CarDetails[1]);
        ((TextView)findViewById(R.id.TextViewInputDoors)).setText(CarDetails[2]);
        ((TextView)findViewById(R.id.TextViewInputFuel)).setText(CarDetails[3]);
        ((TextView)findViewById(R.id.TextViewInputGearbox)).setText(CarDetails[4]);
        ((TextView)findViewById(R.id.TextViewInputKm)).setText(CarDetails[0]);
        ((TextView)findViewById(R.id.TextViewInputModel)).setText(CarDetails[0]);
        ((TextView)findViewById(R.id.TextViewInputPrice)).setText(CarDetails[0]);
        ((TextView)findViewById(R.id.TextViewInputSellerAddress)).setText(CarDetails[0]);
        ((TextView)findViewById(R.id.TextViewInputSellerName)).setText(CarDetails[0]);
        ((TextView)findViewById(R.id.TextViewInputYear)).setText(CarDetails[0]);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_car_details, menu);
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
}
