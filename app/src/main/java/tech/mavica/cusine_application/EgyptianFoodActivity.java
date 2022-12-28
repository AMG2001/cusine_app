package tech.mavica.cusine_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class EgyptianFoodActivity extends AppCompatActivity {
    static ListView lv_egyptianFood;
    SharedPreferences egyptianFoodSharedPref;
    SharedPreferences.Editor egyptianFoodSharedPrefEditor;
    static String egyptianFoodSharedPrefKey="egyptian_food";
    static ArrayList<String> foodNameList=new ArrayList<>();
    static ArrayList<String> foodDescriptionList=new ArrayList<>();
    static ArrayList<Integer> foodImageList=new ArrayList<>();
    static ArrayList<Integer> ids = new ArrayList<>();
    static Context context;
    static FoodAdapter egyptianFoodAdatper;
    static DBHelperEgyptianFood dbHelperEgyptianFood;
    static DBManagerEgyptianFood dbManagerEgyptianFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egyptian_food);
        lv_egyptianFood=findViewById(R.id.lv_egyptianFood);
        context=this;
        /**
         * Getting dbhelper and preparing dbmanager :
         */
        dbHelperEgyptianFood=new DBHelperEgyptianFood(context);
        dbManagerEgyptianFood=new DBManagerEgyptianFood(dbHelperEgyptianFood,context);
        egyptianFoodSharedPref=getSharedPreferences(egyptianFoodSharedPrefKey,MODE_PRIVATE);
        egyptianFoodSharedPrefEditor=egyptianFoodSharedPref.edit();
        if(egyptianFoodSharedPref.getBoolean(egyptianFoodSharedPrefKey,true)==true){
            // insert food name , food description , food preparing way , food image .
            dbManagerEgyptianFood.insertNewEgyptianFoodItem("ورق عنب","الاء محمد عبد الحميد احمد حماد","preparing way",R.drawable.eg_food_ma74y);


            egyptianFoodSharedPrefEditor.putBoolean(egyptianFoodSharedPrefKey,false);
        }
//        dbManagerEgyptianFood.insertNewEgyptianFoodItem("ورق عنب","الاء محمد عبد الحميد احمد حماد");
        /**
         * refreshing UI to load data from database in ArrayLists
         * assign ArrayLists to Adapter and link it with UI
         */
        refreshUI();
        egyptianFoodAdatper =new FoodAdapter(this,foodNameList,foodDescriptionList,foodImageList);
        lv_egyptianFood.setAdapter(egyptianFoodAdatper);










        lv_egyptianFood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {
                showPopupMenu(view,ids.get(index),index);
                return true;
            }
        });
    }



    /**
     * Menu create and inflate in AppBar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu,menu);
        return true;
    }
    /**
     * Actioning appbar buttons .
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


    public  void insertNewEgyptianFoodItem(View view){
        // TODO insert new cusine_country here .
        openDialog();
    }
    /**
     * it used to refresh Ui each time you open this page .
     */
    static void refreshUI(){
        Cursor cursor = dbManagerEgyptianFood.display();
        /**
         * Clear all ArrayLists Data :
         */
        ids.clear();
        foodNameList.clear();
        foodDescriptionList.clear();
        foodImageList.clear();
        while(cursor.moveToNext()){
            /**
             * reloading all ArrayLists Data from database :
             */
            ids.add(cursor.getInt(0));
            foodNameList.add(cursor.getString(1));
            foodDescriptionList.add(cursor.getString(2));
            foodImageList.add(cursor.getInt(3));
        }
        // TODO , base new array lists to adapter .
        egyptianFoodAdatper = new FoodAdapter(context,foodNameList,foodDescriptionList,foodImageList);
        lv_egyptianFood.setAdapter(egyptianFoodAdatper);
    }

    public void openDialog(){
        NewEgyptianFoodDialog dialog = new NewEgyptianFoodDialog(dbManagerEgyptianFood,this);
        dialog.show(getSupportFragmentManager(),null);
    }

    /**
     * Show Edit , Delete Popup Menu .
     * @param view
     */
    void showPopupMenu(View view,int idInDatabase,int indexInMenu){
        PopupMenu popup = new PopupMenu(context,view);
        popup.inflate(R.menu.edit_delete_menu);
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
//                    case R.id.edit:
//                        /**
//                         * Send data to change Cusine info page to Edit on it .
//                         */
//                        Intent i = new Intent(context , ChangeCusineInfoActivity.class);
//                        i.putExtra("id",idInDatabase);
//                        i.putExtra("cusine_name",.get(indexInMenu));
//                        i.putExtra("cusine_description",cusinesDescriptionList.get(indexInMenu));
//                        i.putExtra("flag",countriesFlagsList.get(indexInMenu));
//                        startActivity(i);
//                        return true;
                    case R.id.Delete:
                        Toast.makeText(context, "Delete Clicked id : "+idInDatabase, Toast.LENGTH_SHORT).show();
//                        dbManagerEgyptianFood.deleteCusine(idInDatabase);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}