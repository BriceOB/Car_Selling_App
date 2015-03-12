package com.brice.muhamed.carsellingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Brice on 07/03/2015.
 * source: http://www.learn2crack.com/2013/10/android-custom-listview-images-text-example.html
 */
public class ListCarsForSale extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] infos;
    private final Integer[] imageId;

    public ListCarsForSale(Activity context,
                           String[] infos, Integer[] imageId) {
        super(context, R.layout.list_searched_cars, infos);
        this.context = context;
        this.infos = infos;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_searched_cars, null, true);
        TextView textCarName = (TextView) rowView.findViewById(R.id.textViewCarName);
        ImageView imageViewCar = (ImageView) rowView.findViewById(R.id.imageViewCar);
        textCarName.setText(infos[position]);
        imageViewCar.setImageResource(imageId[position]);
        return rowView;
    }

}