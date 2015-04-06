package com.brice.muhamed.carsellingapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;
import com.brice.muhamed.carsellingapp.Object.Car;


public class ShowCarDetails extends ActionBarActivity {

    String CarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car_details);

        DatabaseHandler dbh  = new DatabaseHandler(getBaseContext());

        //Get intent from search page
        Intent intent = getIntent();
        CarId = intent.getStringExtra(HomePage.EXTRA_MESSAGE);

        String[] CarDetails = dbh.getCarDetails(CarId);
        Car car = dbh.getCar(CarId);

        ((TextView)findViewById(R.id.TextViewInputManufacture)).setText(car.getManufacturer());
        ((TextView)findViewById(R.id.TextViewInputDescription)).setText(car.getDescription());
        ((TextView)findViewById(R.id.TextViewInputDoors)).setText(car.getDoors()+"");
        ((TextView)findViewById(R.id.TextViewInputFuel)).setText(car.getFuel());
        ((TextView)findViewById(R.id.TextViewInputGearbox)).setText("-");
        ((TextView)findViewById(R.id.TextViewInputKm)).setText(car.getKilometers()+"");
        ((TextView)findViewById(R.id.TextViewInputModel)).setText(car.getModel());
        ((TextView)findViewById(R.id.TextViewInputPrice)).setText(car.getPrice()+"");
        ((TextView)findViewById(R.id.TextViewInputSellerAddress)).setText("");
        ((TextView)findViewById(R.id.TextViewInputSellerName)).setText("");
        ((TextView)findViewById(R.id.TextViewInputYear)).setText("");

        Bitmap thumbnail = (BitmapFactory.decodeFile(car.getPhotoPath()));
        ImageView imageView = (ImageView)findViewById(R.id.imageViewInsertCarDetails);
        int width = 960;
        int height = thumbnail.getHeight() * width / thumbnail.getWidth();
        imageView.setImageBitmap(Bitmap.createScaledBitmap(thumbnail, width, height, false));
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

    public void modify(View view) {

        Intent intent = new Intent(this, InsertCarDetails.class);
        intent.putExtra(HomePage.EXTRA_MESSAGE, CarId);
        startActivity(intent);
        this.recreate();
    }

    public void delete(View view) {
        DatabaseHandler dbh = new DatabaseHandler(this.getBaseContext());
        dbh.deleteCar(CarId);
        this.finish();
    }
}
