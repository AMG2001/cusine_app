package tech.mavica.cusine_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class EgyptianFoodActivity extends AppCompatActivity {
    static ListView lv_egyptianFood;
    static ArrayList<String> foodNameList=new ArrayList<>();
    static ArrayList<String> foodComponentsList=new ArrayList<>();
    static ArrayList<Integer> foodImageList=new ArrayList<>();
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egyptian_food);
        lv_egyptianFood=findViewById(R.id.lv_egyptianFood);
        context=this;
        foodNameList.add("محشي مشكل");
        foodComponentsList.add("ورق عنب , كرمب , بتنجان , فلفل , رز , خلطه");
        foodImageList.add(R.drawable.ma74y);

        foodNameList.add("محشي مشكل");
        foodComponentsList.add("ورق عنب , كرمب , بتنجان , فلفل , رز , خلطه");
        foodImageList.add(R.drawable.ma74y);

        foodNameList.add("محشي مشكل");
        foodComponentsList.add("ورق عنب , كرمب , بتنجان , فلفل , رز , خلطه");
        foodImageList.add(R.drawable.ma74y);

        foodNameList.add("محشي مشكل");
        foodComponentsList.add("ورق عنب , كرمب , بتنجان , فلفل , رز , خلطه");
        foodImageList.add(R.drawable.ma74y);

        FoodAdapter foodAdapter=new FoodAdapter(foodNameList,foodComponentsList,foodImageList,context);
        lv_egyptianFood.setAdapter(foodAdapter);
    }
}