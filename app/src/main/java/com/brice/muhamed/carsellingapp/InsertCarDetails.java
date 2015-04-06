package com.brice.muhamed.carsellingapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;
import com.brice.muhamed.carsellingapp.Object.Car;

import java.text.SimpleDateFormat;
import java.util.Date;


public class InsertCarDetails extends ActionBarActivity {

    private Spinner spinnerManufacture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_car_details);
        DatabaseHandler dbh = new DatabaseHandler(getBaseContext());

        //spinners to create
        ArrayAdapter<String> adapterManufacture = new ArrayAdapter<String>(this,  R.layout.my_spinner_layout,dbh.getManufacturer());
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



        DatabaseHandler dbh = new DatabaseHandler(this.getBaseContext());

        EditText model = (EditText)findViewById(R.id.EditTextModel);
        EditText km = (EditText)findViewById(R.id.EditTextKm);
        Spinner fuel = (Spinner)findViewById(R.id.spinnerFuel);
        EditText doors = (EditText)findViewById(R.id.EditTextDoors);
        EditText price = (EditText)findViewById(R.id.EditTextPrice);
        EditText desc = (EditText)findViewById(R.id.EditTextDescription);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd_HHmmss");
        String creationDate = sdf.format(new Date());
        EditText year = (EditText)findViewById(R.id.EditTextYear);
        RadioButton RadioButtonSell = (RadioButton)findViewById(R.id.radioButtonToSell);
        int toSell = 0;
        if(RadioButtonSell.isChecked()){
            toSell = 1;
        }
        Spinner manufacturer = (Spinner)findViewById(R.id.spinnerManufacture);
        Spinner gearbox = (Spinner)findViewById(R.id.spinnerGearbox);
        SharedPreferences sharedId = getPreferences(Context.MODE_PRIVATE);
        int id = sharedId.getInt("Id",0);

        Car car = new Car(model.getText().toString(),
                Integer.parseInt(km.getText().toString()),
                fuel.getSelectedItem().toString(),
                Integer.parseInt( doors.getText().toString()),
                Integer.parseInt(price.getText().toString()),
                desc.getText().toString(),
                creationDate,
                year.getText().toString(),
                toSell,
                dbh.GetManufacturerId(manufacturer.getSelectedItem().toString()),
                id
        );

        dbh.InsertCarInfos(car);

        Toast toast = Toast.makeText(getApplicationContext(), "Car successfully added", Toast.LENGTH_SHORT);
        toast.show();

        this.finish();
    }


    public void UploadCarImage(View view) {
    }
}

