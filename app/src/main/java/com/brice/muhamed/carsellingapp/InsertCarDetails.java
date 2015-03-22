package com.brice.muhamed.carsellingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;


public class InsertCarDetails extends ActionBarActivity {

    private Spinner spinnerManufacture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_car_details);

        //spinners to create
        ArrayAdapter<CharSequence> adapterManufacture = ArrayAdapter.createFromResource(this, R.array.manufacturerList, R.layout.my_spinner_layout);
        adapterManufacture.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerManufacture = (Spinner)findViewById(R.id.spinnerManufacture);
        spinnerManufacture.setAdapter(adapterManufacture);

        ArrayAdapter<CharSequence> adapterFuel = ArrayAdapter.createFromResource(this, R.array.fuelList, R.layout.my_spinner_layout);
        adapterFuel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerFuel = (Spinner)findViewById(R.id.spinnerFuel);
        spinnerFuel.setAdapter(adapterFuel);

        ArrayAdapter<CharSequence> adapterGearbox = ArrayAdapter.createFromResource(this, R.array.gearboxList, R.layout.my_spinner_layout);
        adapterGearbox.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerGearbox = (Spinner)findViewById(R.id.spinnerGearbox);
        spinnerGearbox.setAdapter(adapterGearbox);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insert_car_details, menu);
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

    public void SubmitCarDetails(View view){

      String Manufacturer = spinnerManufacture.getSelectedItem().toString();

        DatabaseHandler dbh = new DatabaseHandler(this.getBaseContext());

        dbh.InsertCarInfos("ModelTest", 2, 1);


        Toast toast = Toast.makeText(getApplicationContext(), "Car successfully added", Toast.LENGTH_SHORT);
        toast.show();

        this.finish();
    }
}

