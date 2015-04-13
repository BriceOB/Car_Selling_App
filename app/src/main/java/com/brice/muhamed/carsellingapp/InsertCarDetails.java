package com.brice.muhamed.carsellingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;
import com.brice.muhamed.carsellingapp.Object.Car;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InsertCarDetails extends ActionBarActivity {

    private Spinner spinnerManufacture;
    private String CarId;
    private String ImagePath="";
    private ImageView iv = null;
    private Bitmap bm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_car_details);
        DatabaseHandler dbh = new DatabaseHandler(getBaseContext());
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_insert_car_details));


        //Get intent
        Intent intent = getIntent();
        CarId = intent.getStringExtra(HomePage.EXTRA_MESSAGE);

        //spinners to create
        ArrayAdapter<String> adapterManufacture = new ArrayAdapter<String>(this, R.layout.my_spinner_layout, dbh.getManufacturer());
        adapterManufacture.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerManufacture = (Spinner) findViewById(R.id.spinnerManufacture);
        spinnerManufacture.setAdapter(adapterManufacture);

        ArrayAdapter<CharSequence> adapterFuel = ArrayAdapter.createFromResource(this, R.array.fuelList, R.layout.my_spinner_layout);
        adapterFuel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerFuel = (Spinner) findViewById(R.id.spinnerFuel);
        spinnerFuel.setAdapter(adapterFuel);

        ArrayAdapter<CharSequence> adapterGearbox = ArrayAdapter.createFromResource(this, R.array.gearboxList, R.layout.my_spinner_layout);
        adapterGearbox.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinnerGearbox = (Spinner) findViewById(R.id.spinnerGearbox);
        spinnerGearbox.setAdapter(adapterGearbox);

        if(CarId!=null){

            Car car = dbh.getCar(CarId);
            int Manufacturerposition = adapterManufacture.getPosition(car.getManufacturer());
            spinnerManufacture.setSelection(Manufacturerposition);

            int Fuelposition = adapterManufacture.getPosition(car.getFuel());
            spinnerManufacture.setSelection(Fuelposition);

            //   int GearBoxposition = adapterManufacture.getPosition();
            //    spinnerManufacture.setSelection(GearBoxposition);

            EditText model = (EditText)findViewById(R.id.EditTextModel);
            EditText km = (EditText)findViewById(R.id.EditTextKm);
            Spinner fuel = (Spinner)findViewById(R.id.spinnerFuel);
            EditText doors = (EditText)findViewById(R.id.EditTextDoors);
            EditText price = (EditText)findViewById(R.id.EditTextPrice);
            EditText desc = (EditText)findViewById(R.id.EditTextDescription);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd_HHmmss");
            String creationDate = sdf.format(new Date());
            EditText year = (EditText)findViewById(R.id.EditTextYear);
            CheckBox RadioButtonSell = (CheckBox)findViewById(R.id.radioButtonToSell);

            if(!car.getPhotoPath().equals("")){
                Bitmap thumbnail = (BitmapFactory.decodeFile(car.getPhotoPath()));
                ImageView imageView = (ImageView)findViewById(R.id.imageViewInsertCarDetails);
                int width = 200;
                int height = thumbnail.getHeight() * width / thumbnail.getWidth();
                imageView.setImageBitmap(Bitmap.createScaledBitmap(thumbnail, width, height, false));
                ImagePath = car.getPhotoPath();
            }
            else{
                /*ImageView imageView = (ImageView)findViewById(R.id.imageViewShowCarDetails);

                imageView.setImageResource(R.drawable.ic_no_picture);*/
            }


            model.setText(car.getModel());
            km.setText(car.getKilometers()+"");
            doors.setText(car.getDoors()+"");
            price.setText(car.getPrice()+"");
            desc.setText(car.getDescription());
            creationDate=car.getCreationDate();
            year.setText(car.getCarDate());
            if(car.getToSell()==1){
                RadioButtonSell.setChecked(true);
            }


        }


        ImageView imageView = (ImageView)findViewById(R.id.imageViewInsertCarDetails);
        imageView = iv;
        if(bm != null){
            imageView.setImageBitmap(bm);
        }


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
        CheckBox RadioButtonSell = (CheckBox)findViewById(R.id.radioButtonToSell);
        int toSell = 0;
        if(RadioButtonSell.isChecked()){
            toSell = 1;
        }
        Spinner manufacturer = (Spinner)findViewById(R.id.spinnerManufacture);
        Spinner gearbox = (Spinner)findViewById(R.id.spinnerGearbox);
        SharedPreferences sharedId = getPreferences(Context.MODE_PRIVATE);
        int id = sharedId.getInt("Id",0);

        if(km.getText().toString().equals("")){

            km.setText("0");
        }
        if(doors.getText().toString().equals("")){

            doors.setText("0");
        }
        if(price.getText().toString().equals("")){

            price.setText("0");
        }
        if(year.getText().toString().equals("")){

            year.setText("0");
        }


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
                id, ImagePath
        );

        if(CarId==null){
            SharedPreferences sharedPref = this.getSharedPreferences("com.brice.muhamed.carsellingapp", Context.MODE_PRIVATE);


            int UserId = sharedPref.getInt("com.brice.muhamed.carsellingapp.Id", 0);
            dbh.InsertCarInfos(car, UserId);

        }
        else{
            dbh.updateCarInfos(car, CarId,manufacturer.getSelectedItem().toString());
        }

        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.CarAdded), Toast.LENGTH_SHORT);
        toast.show();

        ImagePath = "";
        this.finish();
    }

    public void UploadCarImage(View view) {
        selectImage();
    }

    private void selectImage() {
        final CharSequence[] options = getResources().getStringArray(R.array.PopUpIMage);

        AlertDialog.Builder builder = new AlertDialog.Builder(InsertCarDetails.this);
        builder.setTitle(getResources().getString(R.string.AddPhoto));
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take a photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }else if(options[item].equals("Take from gallery") || options[item].equals("Von Gallerie auswählen") || options[item].equals("Choisir de la Gallerie")) {
//                    Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.setType("image/*");
//                    startActivityForResult(Intent.createChooser(intent, "Select File"),2);

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
//
//                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//                    photoPickerIntent.setType("image/*");
//                    startActivityForResult(photoPickerIntent, 2);

                } else {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);



                    File path = new File(android.os.Environment.getExternalStorageDirectory() + File.separator + "CarImages" + File.separator);
                    path.mkdirs();
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");


                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                        ImagePath = file.getAbsolutePath();
                        Bitmap thumbnail = (BitmapFactory.decodeFile(file.getAbsolutePath()));
                        ((ImageView)findViewById(R.id.imageViewInsertCarDetails)).setImageBitmap(thumbnail);
                        ImageView imageView = (ImageView)findViewById(R.id.imageViewInsertCarDetails);
                        int width = 960;
                        int height = thumbnail.getHeight() * width / thumbnail.getWidth();
                        imageView.setImageBitmap(Bitmap.createScaledBitmap(thumbnail, width, height, false));
                        iv = imageView;
                        bm = thumbnail;


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                    Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                ImagePath = picturePath;
                ImageView imageView = (ImageView)findViewById(R.id.imageViewInsertCarDetails);
                int width = 960;
                int height = thumbnail.getHeight() * width / thumbnail.getWidth();
                imageView.setImageBitmap(Bitmap.createScaledBitmap(thumbnail, width, height, false));
                iv = imageView;
                bm = thumbnail;


            }
        }
    }


}

