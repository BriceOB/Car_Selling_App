package com.brice.muhamed.carsellingapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class CarsForSale extends ActionBarActivity {

    ListView list;

    String[] Cars = {
            "TEST"
    } ;
    Integer[] imageId = {
            R.mipmap.ic_launcher,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_for_sale);

        //enable the app icon as the up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Get intent from search page
        Intent intent = getIntent();
        String message = intent.getStringExtra(HomePage.EXTRA_MESSAGE);

        if(message.equals("MyCars")){

            //   Cars = dbh.getMyCars();

        }


        //Search



        //http://www.learn2crack.com/2013/10/android-custom-listview-images-text-example.html
        ListCarsForSale adapter = new
                ListCarsForSale(CarsForSale.this, Cars, imageId);
        list=(ListView)findViewById(R.id.listViewCarsSearched);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(CarsForSale.this, "Element: " + Cars[+position], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CarsForSale.this, ShowCarDetails.class);
                startActivity(intent);

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cars_for_sale, menu);
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
