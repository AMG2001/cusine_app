package tech.mavica.cusine_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    ArrayList<String> foodNameList=new ArrayList<>();
    ArrayList<String> foodComponentsList=new ArrayList<>();
    ArrayList<Integer> foodImageList=new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public FoodAdapter(ArrayList<String> foodNameList, ArrayList<String> foodComponentsList, ArrayList<Integer> foodImage, Context context) {
        this.foodNameList = foodNameList;
        this.foodComponentsList = foodComponentsList;
        this.foodImageList = foodImage;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return foodNameList.size();
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
        convertView=inflater.inflate(R.layout.food_item,null);
        TextView tv_foodName = convertView.findViewById(R.id.tv_foodActivity_foodName);
        TextView tv_foodComponents = convertView.findViewById(R.id.tv_foodActivity_foodComponents);
        ImageView food_image = convertView.findViewById(R.id.image_food_image);
        tv_foodName.setText(foodNameList.get(position));
        tv_foodComponents.setText(foodComponentsList.get(position));
        food_image.setImageResource(foodImageList.get(position));
        return convertView;
    }
}
