package com.brice.muhamed.carsellingapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;
import com.brice.muhamed.carsellingapp.Database.ManufacturerContract;


public class HomePage extends ActionBarActivity {

    private String [] LastSearchParameters;
    public final static String EXTRA_MESSAGE = "com.brice.muhamed.EXTRA_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        DatabaseHandler dbh = new DatabaseHandler(getBaseContext());

       // Spinner Manufacturer
        Spinner spinner = (Spinner) findViewById(R.id.spinnerManufacturer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_spinner_layout, dbh.getManufacturer());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Spinner Order
        Spinner spinnerOrder = (Spinner) findViewById(R.id.spinnerOrder);
        ArrayAdapter<CharSequence> adapterOrder = ArrayAdapter.createFromResource(this, R.array.ListOrder, R.layout.my_spinner_layout);
        adapterOrder.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrder.setAdapter(adapterOrder);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
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

    public void SearchCars(View view) {

        Intent intent = new Intent(this, CarsForSale.class);

        String[] SearchParameters = {"Manufacturer",
                //((Spinner)findViewById(R.id.spinnerManufacturer)).getSelectedItem()+"" ,
                // ((Spinner)findViewById(R.id.spinnerModel)).getSelectedItem().toString(),
                "Model",
                ((EditText)findViewById(R.id.editTextYearFrom)).getText().toString(),
                ((EditText)findViewById(R.id.editTextYearTo)).getText().toString(),
                ((EditText)findViewById(R.id.editTextKmFrom)).getText().toString(),
                ((EditText)findViewById(R.id.editTextKmTo)).getText().toString(),
                ((EditText)findViewById(R.id.editTextPriceFrom)).getText().toString(),
                ((EditText)findViewById(R.id.editTextPriceTo)).getText().toString(),
                "Order"};
        // ((Spinner)findViewById(R.id.spinnerOrder)).getSelectedItem().toString()};


        LastSearchParameters = SearchParameters;
        intent.putExtra(EXTRA_MESSAGE, SearchParameters);
        startActivity(intent);
    }

    public void login(MenuItem item) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void insert(MenuItem item) {
    }

    public void myCars(MenuItem item) {
    }

    public void settings(MenuItem item) {
    }

    public void about(MenuItem item) {
    }
}
