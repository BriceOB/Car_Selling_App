package com.brice.muhamed.carsellingapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Brice on 07/03/2015.
 * source: http://www.learn2crack.com/2013/10/android-custom-listview-images-text-example.html
 */
public class ListCarsForSale extends ArrayAdapter<String> {

    private final Activity context;
    private final String[][] Cars;

    public ListCarsForSale(Activity context, String[][] Cars) {
        super(context, R.layout.list_searched_cars, new String[Cars.length]);
        this.context = context;
        this.Cars = Cars;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_searched_cars, null, true);

        TextView textCarName = (TextView) rowView.findViewById(R.id.textViewCarName);
        textCarName.setText(Cars[position][0]);

        TextView textCarModel = (TextView) rowView.findViewById(R.id.textViewCarModel);
        textCarModel.setText(Cars[position][1]);

        TextView textCarDescription = (TextView) rowView.findViewById(R.id.textViewCarDescription);
        textCarDescription.setText(Cars[position][2]);

        ImageView imageViewCar = (ImageView) rowView.findViewById(R.id.imageViewCar);

        //Check if an image exists
        if(!Cars[position][4].equals("")){
            Bitmap thumbnail = (BitmapFactory.decodeFile(Cars[position][4]));
            ImageView imageView = (ImageView)rowView.findViewById(R.id.imageViewCar);
            int width = 960;
            int height = thumbnail.getHeight() * width / thumbnail.getWidth();
            imageView.setImageBitmap(Bitmap.createScaledBitmap(thumbnail, width, height, false));        }
        else{
            imageViewCar.setImageResource(R.drawable.ic_no_picture);

        }

        return rowView;
    }

}