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
public class CountryAdapter extends ArrayAdapter<Country> {

    private final Context context;
    private final ArrayList<Country> countryList;

    public CountryAdapter(Context context, ArrayList<Country> countryList) {
        super(context, R.layout.list_item_country, countryList);
        this.context = context;
        this.countryList = countryList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_country, null);
        }

        Country country = countryList.get(position);

        if (country != null) {
            TextView countryName = view.findViewById(R.id.textViewCountryName);
            TextView countryCapital = view.findViewById(R.id.textViewCountryCapital);
            ImageView countryFlag = view.findViewById(R.id.imageViewCountryFlag);

            countryName.setText(country.getName());
            countryCapital.setText(country.getCapital());

            Picasso.get().load(country.getFlagURL()).into(countryFlag);
        }

        return view;
    }
}
