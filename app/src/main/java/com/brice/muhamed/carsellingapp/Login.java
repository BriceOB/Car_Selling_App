package com.brice.muhamed.carsellingapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.brice.muhamed.carsellingapp.Database.DatabaseHandler;
import com.brice.muhamed.carsellingapp.Database.SellerContract;
import com.brice.muhamed.carsellingapp.Object.Seller;


public class Login extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);

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

        Intent intent = new Intent(this, InsertSeller.class);
        startActivity(intent);
    }

    public void login(View view) {

        DatabaseHandler dbh = new DatabaseHandler(this.getBaseContext());

        EditText txtUsername = (EditText)findViewById(R.id.editTextUserNameLogin);
        EditText txtPassword = (EditText)findViewById(R.id.editTextPasswordLogin);
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        //checks fields are filled
        if(TextUtils.isEmpty(username)){
            txtUsername.setError(getString(R.string.UsernameNotempty));
            return;
        }
        if(TextUtils.isEmpty(password)){
            txtPassword.setError(getString(R.string.PasswordNotEmpty));
            return;
        }

        Seller seller = dbh.checkUserPassword(username,password);

        if(seller!=null){

            //Set shared preference to the user id
            SharedPreferences sharedPref = this.getSharedPreferences("com.brice.muhamed.carsellingapp", Context.MODE_PRIVATE);
            sharedPref.edit().putInt("com.brice.muhamed.carsellingapp.Id", seller.getId()).apply();

            ShowToast("Welcome " + seller.getUsername() );



            this.finish();
            return;
        }

        ShowToast(getString(R.string.WrongPassword));



    }

    public void ShowToast(CharSequence text){

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

}
