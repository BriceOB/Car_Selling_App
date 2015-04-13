package com.brice.muhamed.carsellingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;


public class CarsForSale extends ActionBarActivity {

    ListView list;
    public final static String EXTRA_MESSAGE = "com.brice.muhamed.EXTRA_MESSAGE";


    String[][] Cars ;
    Integer[] imageId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_for_sale);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_cars_for_sale));


        DatabaseHandler dbh = new DatabaseHandler(getBaseContext());

        //enable the app icon as the up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get intent from search page
        Intent intent = getIntent();
        String[] message = intent.getStringArrayExtra(HomePage.EXTRA_MESSAGE);

        //Show all cars from the user
        if(message[0].equals("MyCars")){

            SharedPreferences sharedPref = this.getSharedPreferences("com.brice.muhamed.carsellingapp", Context.MODE_PRIVATE);
            int UserId = sharedPref.getInt("com.brice.muhamed.carsellingapp.Id", 0);
            Cars = dbh.getMyCars(UserId);

        }
        else{
           Cars = dbh.GetResultSearch(message[0],message[1],message[2],message[3],message[4],message[5],message[6],message[7],message[8]);
        }

        if(Cars!=null){

            //source itemlistener: http://www.learn2crack.com/2013/10/android-custom-listview-images-text-example.html

            ListCarsForSale adapter = new
                    ListCarsForSale(CarsForSale.this,Cars);
            list=(ListView)findViewById(R.id.listViewCarsSearched);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view,
                                                                    int position, long id) {

                                                Intent intent = new Intent(CarsForSale.this, ShowCarDetails.class);
                                                intent.putExtra(EXTRA_MESSAGE, Cars[position][5]);
                                                startActivity(intent);

                                            }
                                        }

            );
        }
        else{
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, getString(R.string.NoCar), duration);
            toast.show();

            finish();
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
