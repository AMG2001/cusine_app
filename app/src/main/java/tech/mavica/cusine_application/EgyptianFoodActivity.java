package tech.mavica.cusine_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        foodImageList.add(R.drawable.eg_food_ma74y);

        foodNameList.add("محشي مشكل");
        foodComponentsList.add("ورق عنب , كرمب , بتنجان , فلفل , رز , خلطه");
        foodImageList.add(R.drawable.eg_food_ma74y);

        foodNameList.add("محشي مشكل");
        foodComponentsList.add("ورق عنب , كرمب , بتنجان , فلفل , رز , خلطه");
        foodImageList.add(R.drawable.eg_food_ma74y);

        foodNameList.add("محشي مشكل");
        foodComponentsList.add("ورق عنب , كرمب , بتنجان , فلفل , رز , خلطه");
        foodImageList.add(R.drawable.eg_food_ma74y);

        FoodAdapter foodAdapter=new FoodAdapter(foodNameList,foodComponentsList,foodImageList,context);
        lv_egyptianFood.setAdapter(foodAdapter);
    }

    /**
     * Menu create and inflate in AppBar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return true;
    }
    /**
     * Actioning appbar buttons .
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings_tab:
                startActivity(new Intent(this,SettingsActivity.class));
                return  true;
            default:
                return true;
        }
    }


}