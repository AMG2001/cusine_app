package tech.mavica.cusine_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountriesCusinesAdapter extends BaseAdapter {
    Context context;
    LayoutInflater infalter;
    ArrayList<String> countriesNamesList=new ArrayList<>();
    ArrayList<String> cusineDescriptionList=new ArrayList<>();
    ArrayList<Integer> countriesFlagsList=new ArrayList<>();

    public CountriesCusinesAdapter(Context context, ArrayList<String> countriesNames, ArrayList<String> cusineDescription, ArrayList<Integer> countriesFlags) {
        this.context = context;
        infalter = LayoutInflater.from(context);
        this.countriesNamesList = countriesNames;
        this.cusineDescriptionList = cusineDescription;
        this.countriesFlagsList = countriesFlags;
    }

    @Override
    public int getCount() {
        return countriesNamesList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=infalter.inflate(R.layout.country_cusine_item,null);
        TextView cusineCountry=convertView.findViewById(R.id.cusine_counrty);
        TextView cusineDescription=convertView.findViewById(R.id.cusine_description);
        ImageView countryFlag=convertView.findViewById(R.id.country_flag);
        cusineCountry.setText(countriesNamesList.get(position));
        cusineDescription.setText(cusineDescriptionList.get(position));
        countryFlag.setImageResource(countriesFlagsList.get(position));
        return convertView ;
    }
}
