package com.example.paseseva;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class Paisadaptador extends ArrayAdapter<nombrepais> {

    private final Context context;
    private final ArrayList<nombrepais> countryList;
    public Paisadaptador(Context context, ArrayList<nombrepais> countryList) {
        super(context, R.layout.paisitem, countryList);
        this.context = context;
        this.countryList = countryList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.paisitem, null);
        }

        nombrepais country = countryList.get(position);

        if (country != null) {
            TextView countryName = view.findViewById(R.id.nombrepais);
            TextView countryCapital = view.findViewById(R.id.nombrecapital);
            ImageView countryFlag = view.findViewById(R.id.banderaimg);
            countryName.setText(country.getName());
            countryCapital.setText(country.getCapital());
            Picasso.get().load(country.getFlagURL()).into(countryFlag);
        }

        return view;
    }
}
