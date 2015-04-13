package com.brice.muhamed.carsellingapp;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;
import com.brice.muhamed.carsellingapp.Database.ManufacturerContract;

import java.util.Locale;


public class HomePage extends ActionBarActivity {

    private String [] LastSearchParameters;
    public final static String EXTRA_MESSAGE = "com.brice.muhamed.EXTRA_MESSAGE";
    private   ArrayAdapter<String> adapterModel;
    private DatabaseHandler dbh;
    private ArrayAdapter<String> adapterManufacturer;
    private Spinner spinnerManufacturer;
    public      SharedPreferences sharedPref;
    private   Spinner spinnerOrder;


    private Locale locale = null;

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if (locale != null)
        {
            newConfig.locale = locale;
            Locale.setDefault(locale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }

private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        sharedPref =  this.getSharedPreferences("com.brice.muhamed.carsellingapp", Context.MODE_PRIVATE);

       dbh  = new DatabaseHandler(getBaseContext());

        // Spinner Manufacturer
        spinnerManufacturer = (Spinner) findViewById(R.id.spinnerManufacturer);
        adapterManufacturer = new ArrayAdapter<String>(this, R.layout.my_spinner_layout, dbh.getManufacturer());
        adapterManufacturer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerManufacturer.setAdapter(adapterManufacturer);
        spinnerManufacturer.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        updateModel(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        //Spinner Order
        spinnerOrder = (Spinner) findViewById(R.id.spinnerOrder);
        ArrayAdapter<CharSequence> adapterOrder = ArrayAdapter.createFromResource(this, R.array.ListOrder, R.layout.my_spinner_layout);
        adapterOrder.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrder.setAdapter(adapterOrder);

        //Spinner Model
        Spinner spinnerModel = (Spinner) findViewById(R.id.spinnerModel);
        adapterModel = new ArrayAdapter<String>(this, R.layout.my_spinner_layout, dbh.getModel(spinnerManufacturer.getItemAtPosition(0).toString()));
        adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModel.setAdapter(adapterModel);



        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = settings.getString(getString(R.string.pref_locale), "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang))
        {
            locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }



    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);

        this.menu= menu;


        int i = sharedPref.getInt("com.brice.muhamed.carsellingapp.Id",0);



        if( i >0){

            MenuItem insert =  menu.findItem(R.id.action_insert);
            MenuItem myCars =  menu.findItem(R.id.action_myCars);
            MenuItem login =  menu.findItem(R.id.action_login);
            MenuItem logout =  menu.findItem(R.id.action_logout);

            insert.setEnabled(true);
            myCars.setEnabled(true);
            login.setVisible(false);
            logout.setVisible(true);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
        invalidateOptionsMenu();
            return true;
        }
        if (id == R.id.action_login) {
            invalidateOptionsMenu();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateModel(int position){
        Spinner spinnerModel = (Spinner) findViewById(R.id.spinnerModel);
        adapterModel = new ArrayAdapter<String>(this, R.layout.my_spinner_layout, dbh.getModel(spinnerManufacturer.getItemAtPosition(position).toString()));
        adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModel.setAdapter(adapterModel);
        spinnerModel.setSelection(0);
        adapterModel.notifyDataSetChanged();
    }

    public void SearchCars(View view) {

        Intent intent = new Intent(this, CarsForSale.class);

        String[] SearchParameters = {
                ((Spinner)findViewById(R.id.spinnerManufacturer)).getSelectedItem().toString() ,
                ((Spinner)findViewById(R.id.spinnerModel)).getSelectedItem().toString(),
                ((EditText)findViewById(R.id.editTextYearFrom)).getText().toString(),
                ((EditText)findViewById(R.id.editTextYearTo)).getText().toString(),
                ((EditText)findViewById(R.id.editTextKmFrom)).getText().toString(),
                ((EditText)findViewById(R.id.editTextKmTo)).getText().toString(),
                ((EditText)findViewById(R.id.editTextPriceFrom)).getText().toString(),
                ((EditText)findViewById(R.id.editTextPriceTo)).getText().toString(),
                ((Spinner)findViewById(R.id.spinnerOrder)).getSelectedItem().toString()};

        LastSearchParameters = SearchParameters;
        intent.putExtra(EXTRA_MESSAGE, SearchParameters);
        startActivity(intent);
    }

    public void login(MenuItem item) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        menu.clear();
        onCreateOptionsMenu(menu);


    }

    public void insert(MenuItem item) {

        Intent intent = new Intent(this, InsertCarDetails.class);
        startActivity(intent);

    }

    public void myCars(MenuItem item) {
        Intent intent = new Intent(this, CarsForSale.class);
        intent.putExtra(EXTRA_MESSAGE, new String[]{"MyCars"});
        startActivity(intent);

    }

    public void settings(MenuItem item) {
        Intent intent = new Intent(this, Language.class);
        startActivity(intent);
    }

    public void about(MenuItem item) {

        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void logout(MenuItem item){

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.remove("com.brice.muhamed.carsellingapp.Id");
        editor.commit();

        sharedPref.edit().putInt("com.brice.muhamed.carsellingapp.Id", 0).commit();
       invalidateOptionsMenu();
        menu.clear();
        onCreateOptionsMenu(menu);


    }
}
