package tech.mavica.cusine_application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**
     * CountryCosine Arraylist .
     */
    static ArrayList<String> countriesCusinesList=new ArrayList<>();
    static ArrayList<Integer> countriesFlagsList=new ArrayList<>();
    static ArrayList<String> cusinesDescriptionList=new ArrayList<>();
    static ArrayList<Integer> ids=new ArrayList<Integer>();
    static CountriesCusinesAdapter countriesCusinesAdapter;
    static ListView listOfCusinesCountires;
    static Context context;
    static String first_time_created_key = "FIRST_TIME_CREATED";
    /**
     * First time shared preference to loud Default 6 countires in database .
     */
    static SharedPreferences firstTimeDBCreatedSharedPreferences;
    static SharedPreferences.Editor firstTimeDBCreatedSharedPreferencesEditor;
    /**
     * shared preference to check last user Dark/Light mode choice .
     */
    SharedPreferences isLightSharedPref;
    /**
     * Country Cosine DBHelper and DBManager .
     */
    static DBHelper_CountriesCusines dbHelper_countriesCusines;
    static DBManager_CountriesCusines dbManager_countriesCusines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * checking last Light/Dark mode user choice :
         */
        isLightSharedPref = getSharedPreferences("isLight",MODE_PRIVATE);
        if(isLightSharedPref.getBoolean("isLight",true)==true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        context = MainActivity.this;
        /**
         * create database and get SQLiteDatabase Object .
         */
        dbHelper_countriesCusines=new DBHelper_CountriesCusines(this);
        /**
         * get SQLIte database Object that will be used in the whole CRUD Operations .
         */
        dbManager_countriesCusines = new DBManager_CountriesCusines(MainActivity.this,dbHelper_countriesCusines);

        listOfCusinesCountires=findViewById(R.id.lv_countires_cusines_list);
        /**
         * Set title for Page .
         */
        this.setTitle("Cusine App");
        /**
         * here i used shared preferences to check , if this is first Time db created or dropped
         * and recreated , then add intialized cusines to Database ,
         * else , don't do any thing .
         */
        firstTimeDBCreatedSharedPreferences =getSharedPreferences(first_time_created_key,MODE_PRIVATE);
         // Creating an Editor object to edit(write to the file)
        firstTimeDBCreatedSharedPreferencesEditor = firstTimeDBCreatedSharedPreferences.edit();
         if(firstTimeDBCreatedSharedPreferences.getBoolean(first_time_created_key,true) == true){
             Toast.makeText(context, "value of Shared pref before change : "+firstTimeDBCreatedSharedPreferences.getBoolean(first_time_created_key,false), Toast.LENGTH_LONG).show();
             /**
              * Adding initialized cusines ti database ->
              */
             dbManager_countriesCusines.insertNewCusine("Egypt","Try and taste all Egyptian Food",R.drawable.egypt_flag);
             dbManager_countriesCusines.insertNewCusine("Italy","Try and taste all Italian Food",R.drawable.italy_flag);
             dbManager_countriesCusines.insertNewCusine("india","Try and taste all Indian Food",R.drawable.india_flag);
             dbManager_countriesCusines.insertNewCusine("Japan","Try and taste all Japanese Food",R.drawable.japan_flag);
             dbManager_countriesCusines.insertNewCusine("Saudi Arabia","Try and taste all Arab Food",R.drawable.arab_flag);
             dbManager_countriesCusines.insertNewCusine("France","Try and taste all French Food",R.drawable.france_flag);
         firstTimeDBCreatedSharedPreferencesEditor.putBoolean(first_time_created_key,false);
         firstTimeDBCreatedSharedPreferencesEditor.commit();
         Toast.makeText(context, "value of Shared pref After change : "+firstTimeDBCreatedSharedPreferences.getBoolean(first_time_created_key,false), Toast.LENGTH_LONG).show();
         }
       /**
         * it will rebuild UI Each time you open this page .
         * rebuild mean clear all data inside ArrayList , get all recored from database
         * and pass all these records to ArrayLists .
         */
        refreshUI();
        /**
         * pass ArrayLists to adapter
         */
        countriesCusinesAdapter=new CountriesCusinesAdapter(this,countriesCusinesList,cusinesDescriptionList,countriesFlagsList);
        /**
         * Add adapter to listView .
         */
        listOfCusinesCountires.setAdapter(countriesCusinesAdapter);
        /**
         * when you press on any item , it navigate to this country foods page :
         */
        listOfCusinesCountires.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long l) {
                 showPopupMenu(view,ids.get(index),index);
                return true;
            }
        });

        listOfCusinesCountires.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(MainActivity.this,EgyptianFoodActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    /**
     * this method Go to Settings fragment page to check on last user Dark/Light Application mode .
     */
    void getApplicatioTheme(){

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

    /**
     * it used to refresh Ui each time you open this page .
     */
    static void refreshUI(){
        Cursor cursor = dbManager_countriesCusines.display();
        /**
         * Clear all ArrayLists Data :
         */
        countriesCusinesList.clear();
        countriesFlagsList.clear();
        cusinesDescriptionList.clear();
        ids.clear();
        while(cursor.moveToNext()){
            /**
             * reloading all ArrayLists Data from database :
             */
            ids.add(cursor.getInt(0));
            countriesCusinesList.add(cursor.getString(1));
            cusinesDescriptionList.add(cursor.getString(2));
            countriesFlagsList.add(cursor.getInt(3));
        }
        // TODO , base new array lists to adapter .
        countriesCusinesAdapter = new CountriesCusinesAdapter(context,countriesCusinesList,cusinesDescriptionList,countriesFlagsList);
        listOfCusinesCountires.setAdapter(countriesCusinesAdapter);
    }

    /**
     * open dialog to insert new Cusine .
     * @param view
     */
    public void insert_new_cusine(View view){
        // TODO insert new cusine_country here .
        openDialog();
    }

    /**
     * Show Edit , Delete Popup Menu .
     * @param view
     */
    void showPopupMenu(View view,int idInDatabase,int indexInMenu){
        PopupMenu popup = new PopupMenu(MainActivity.this,view);
        popup.inflate(R.menu.edit_delete_menu);
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.edit:
                        /**
                         * Send data to change Cusine info page to Edit on it .
                         */
                        Intent i = new Intent(getApplicationContext() , ChangeCusineInfoActivity.class);
                        i.putExtra("id",idInDatabase);
                        i.putExtra("cusine_name",countriesCusinesList.get(indexInMenu));
                        i.putExtra("cusine_description",cusinesDescriptionList.get(indexInMenu));
                        i.putExtra("flag",countriesFlagsList.get(indexInMenu));
                        startActivity(i);
                        return true;
                    case R.id.Delete:
//                        Toast.makeText(MainActivity.this, "Delete Clicked id : "+idInDatabase, Toast.LENGTH_SHORT).show();
                        dbManager_countriesCusines.deleteCusine(idInDatabase);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public void openDialog(){
        NewCusineDialogClass dialog = new NewCusineDialogClass(dbManager_countriesCusines,this);
        dialog.show(getSupportFragmentManager(),null);
    }

}


